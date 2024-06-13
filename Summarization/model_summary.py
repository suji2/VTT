# 유튜브 영상 : RMnVSr7KC3I

import torch
from transformers import PreTrainedTokenizerFast
from transformers.models.bart import BartForConditionalGeneration 

# 모델 바이너리 파일 경로
model_binary_path = 'C:/Users/user/Documents/VTT/AI_Model'

# KoBART 모델 및 토크나이저 로드
tokenizer = PreTrainedTokenizerFast.from_pretrained('gogamza/kobart-base-v1')
model = BartForConditionalGeneration.from_pretrained(model_binary_path)

# 입력 텍스트
input_text =  """
오는 7월 3일 새 집회를 도입하는
일본 각도에 따라 얼굴 방향이 바뀌는
3D 홀로그램을 접목한 첨단
집회입니다
대중교통과 은행 atm 기기 등 새
집회를 인식하기 위한 준비가 진행
중입니다 그런데 소규모 식당들은
울상입니다 일본은 이런 자판기에서
메뉴를 고르고 식권을 뽑는 형태의
가게가 흔한데요 친권이 도입되는만큼
이런 자판기를 바꿔야 할 처지에 놓인
겁니다 도쿄에서 라 장를 하는 아마씨
집회를 인식하는 자판기 값은 대당
200만엔 우리돈 1750만 원
정도입니다 보조금을 받아도 네개
매장에 자판기를 교체하는데 들어간
돈은 600만엔 정도 이때문에 라멘도
50 정도를 올렸다고
말합니다 근 전자결제가 가한 자판기도
많다보니 당 집를 인식하지 못해도
급 음료수라고 합니다
전국에 해야 하는데 대부분의
자판기들이 새 집회를 인식하기는 대략
2년 정도는 걸릴 거라고
합니다 일본 자판기 기계 협회는 다음
달 신권이 발행되면 식당 자동
판매기는 50% 음료 자동 판매기는
2에서 3% 새 집회 인식이 가능할
거라고
내다봤습니다 l"""
# 입력 텍스트를 토큰화하여 인코딩
input_ids = tokenizer.encode(input_text, return_tensors="pt", max_length=1024, truncation=True)

# 모델을 사용하여 요약 생성
summary_ids = model.generate(input_ids, max_length=300, min_length=150, length_penalty=2.0, num_beams=4, early_stopping=True)

# 요약 결과 디코딩
summary_text = tokenizer.decode(summary_ids[0], skip_special_tokens=True)

# 요약 출력
print("요약 텍스트:", summary_text)