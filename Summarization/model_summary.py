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
제 회. 현충일인 오늘, 부산의 한 아파트에 일본 군국주의 상징인 우 일기 내 걸려 논란 일고 있습니다. 오늘로 전 온라인 커뮤니티에는 부산 수영구, 남천동에 한 층 주상 복합 아파트, 고층 창문에 우 일기 걸렸다는 글이 다수 올라왔습니다. 게시 글과 함께 올라온 사진에는 우 일기 두 개가 대칭을 이루듯 걸려 있습니다. 주민들에 따르면, 우 일기 개항한 주민은 지난 일 절 그래도 일장기를 창문에 걸었던 것으로 전해졌습니다. 지난 달부터 한 달 가량 창문의 일장기를 개하고 내리기를 반복하다가 오늘, 현충일에 맞춰 우 일기 내걸었다는 겁니다. 한국 사람이 왜 저러나 하고 있어요. 관리 사무소에서도 이야기하고, 주민 회도 이야기하고, 그렇게 하려고하니까 전화도 잘 안 받으시고, 경찰 신 거 해 가지고, 경찰이 나왔어도 뭐 문을 안 열어 주고. 그러니까 인터넷 커뮤니티에는 진짜 선을 넘었다, 현충일에 전범기를 걸다니, 너무 충아, 부들부들 떨린다, 분노가 치인다는 등의 반응이 이어지고 있습니다. 아파트 관리 사무소에는 우 일기 내려 달라는 항의가 빗발치고 있지만. 관리 사무소 측은 내부 방송으로 우 일기 내려 달라고 요청하는 등 할 수 있는 방법은 다 했는데도 답이 없다며 난감해하는 상황으로 전해졌습니다. 일장기와 우 일기 내건 입주민은 일본인이 아닌 한국인으로 알려졌습니다.
"""
# 입력 텍스트를 토큰화하여 인코딩
input_ids = tokenizer.encode(input_text, return_tensors="pt", max_length=1024, truncation=True)

# 모델을 사용하여 요약 생성
summary_ids = model.generate(input_ids, max_length=250, min_length=40, length_penalty=2.0, num_beams=4, early_stopping=True)

# 요약 결과 디코딩
summary_text = tokenizer.decode(summary_ids[0], skip_special_tokens=True)

# 요약 출력
print("요약 텍스트:", summary_text)