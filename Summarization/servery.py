import torch
from flask import Flask, request, jsonify
from transformers import PreTrainedTokenizerFast
from transformers.models.bart import BartForConditionalGeneration 

# 모델 바이너리 파일 경로
model_binary_path = 'C:/Users/user/Documents/VTT/AI_Model'

# KoBART 모델 및 토크나이저 로드
tokenizer = PreTrainedTokenizerFast.from_pretrained('gogamza/kobart-base-v1')
model = BartForConditionalGeneration.from_pretrained(model_binary_path)

app = Flask(__name__)

@app.route('/')
def home():
    return 'Hello, World!'

@app.route('/summary', methods=['POST'])
def summary():
    data = request.json
    input_text = data.get('text', '')

    if not input_text:
        return jsonify({'error': 'No text provided'}), 400

    # 입력 텍스트를 토큰화하여 인코딩
    input_ids = tokenizer.encode(input_text, return_tensors="pt", max_length=1024, truncation=True)

    # 모델을 사용하여 요약 생성
    summary_ids = model.generate(input_ids, max_length=250, min_length=40, length_penalty=2.0, num_beams=4, early_stopping=True)

    # 요약 결과 디코딩
    summary_text = tokenizer.decode(summary_ids[0], skip_special_tokens=True)

    # 요약 결과 반환
    return jsonify({'summary': summary_text})

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8080, debug=True)
