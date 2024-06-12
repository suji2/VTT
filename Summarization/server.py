from flask import Flask, request, jsonify
from flask_cors import CORS
from youtube_transcript_api import YouTubeTranscriptApi
import torch
from transformers import PreTrainedTokenizerFast, BartForConditionalGeneration
import re
from deepmultilingualpunctuation import PunctuationModel

app = Flask(__name__)
CORS(app)

# KoBART 모델 및 토크나이저 로드
tokenizer = PreTrainedTokenizerFast.from_pretrained('gogamza/kobart-base-v1')
model = BartForConditionalGeneration.from_pretrained('C:/Users/skyKim/Documents/VTT/AI_Model')

# 구두점 모델 불러오기
punctuation_model = PunctuationModel()

# 텍스트 전처리 및 구두점 복원 함수
def add_punctuation(text):
    text = re.sub(r'\[.*?\]', '', text) # 괄호 안의 텍스트 제거
    text = re.sub(r'[^\w\s가-힣\d%,]', '', text) # 특수문자 제거 (숫자 및 문장 경계는 유지)
    text = re.sub(r'(\d)\s+(\d)', r'\1\2', text) # 숫자 사이의 공백 제거

    punctuated_text = punctuation_model.restore_punctuation(text) # 구두점 복원
    punctuated_text = re.sub(r'([.!?])(\S)', r'\1 \2', punctuated_text) # 모든 문장 끝에 구두점 추가 후 공백 추가
    punctuated_text = punctuated_text.replace(':', '.') # ':' 구두점 대체
    # punctuated_text = re.sub(r'(?<![.!?])\s*\n', '.\n', punctuated_text) # 문장 끝에 구두점이 없는 경우 마침표 추가
    # punctuated_text = re.sub(r'(?<![.!?])$', '.', punctuated_text)
    punctuated_text = re.sub(r'(\d+)\s*%', r'\1%', punctuated_text) # % 기호와 숫자 사이의 공백 제거
    return punctuated_text

# 영상 스크립트 추출 함수
def extract_video_text(video_id):
    transcript_list = YouTubeTranscriptApi.list_transcripts(video_id)
    transcript = transcript_list.find_transcript(['ko'])
    script = transcript.fetch()

    sentences = []
    for sentence in script:
        text = sentence['text'].replace('\n', ' ')
        sentences.append(text)

    video_text = ' '.join(sentences)
    return video_text

# 요약된 텍스트 함수
def summarize_text(input_text):
    input_ids = tokenizer.encode(input_text, return_tensors="pt", max_length=1024, truncation=True)
    summary_ids = model.generate(input_ids, max_length=300, min_length=150, length_penalty=2.0, num_beams=4, early_stopping=True)
    summary_text = tokenizer.decode(summary_ids[0], skip_special_tokens=True)
    return summary_text

@app.route('/summarize', methods=['POST'])
def summarize_video():
    data = request.get_json()
    video_id = data.get('video_id')

    if not video_id:
        return jsonify({'error': 'No video ID provided'}), 400

    try:
        full_script = extract_video_text(video_id)
    except Exception as e:
        return jsonify({'error': str(e)}), 500

    try:
        punctuated_script = add_punctuation(full_script)
        summary_text = summarize_text(punctuated_script)
    except Exception as e:
        return jsonify({'error': 'Error during summarization: ' + str(e)}), 500

    return jsonify({'summary': summary_text})

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=5001, debug=True)
