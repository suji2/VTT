import speech_recognition as sr
import sys

r = sr.Recognizer()

audio_file = sr.AudioFile('C:/Users/user/Downloads/news.wav')

with audio_file as source:
    audio = r.record(source)

sys.stdout = open('stdout.txt', 'w')

print(r.recognize_google(audio, language='ko-KR'))