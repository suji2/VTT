from moviepy.editor import VideoFileClip

def extract_audio_from_video(youtube_video_url, audio_file_path):
    # 유튜브 비디오 URL을 사용하여 비디오 파일 로드
    video = VideoFileClip(youtube_video_url)
    
    # 오디오를 추출하여 WAV 파일로 저장
    video.audio.write_audiofile(audio_file_path, codec='pcm_s16le')

# 가져올 유튜브 비디오의 URL과 저장할 오디오 파일의 경로
youtube_video_url = 'https://youtu.be/yYd4eDXG5Vo?si=K24B8wsaZv5BYmeB'
audio_file_path = 'output_audio.wav'

extract_audio_from_video(youtube_video_url, audio_file_path)
