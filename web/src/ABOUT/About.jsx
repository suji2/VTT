import React from 'react';
import './About.css';

const About = () => {
  return (
    <div className="about-container">
      <h1>Video To Text 소개</h1>
      <p>Video To Text (VTT)는 YouTube 동영상의 내용을 추출하고 요약하기 위해 설계된 도구입니다.<br />
      저희 시스템은 고급 머신 러닝 모델을 활용하여 동영상 대본을 처리하고 이해하여 사용자가 빠르게 동영상의 요점을 파악할 수 있도록 도와줍니다.</p>
      
      <h2>작동 방식</h2>
      <p>다음 다이어그램은 저희 시스템의 전체 워크플로우를 보여줍니다:</p>
      <img src="/sequence_diagram.png" alt="순서도" className="about-image" style={{ width: '80%' }} />
      <p>저희 시스템은 다음 단계들을 거쳐 요약본을 생성합니다:</p>
      <img src="/summary_stepx.png" alt="요약 단계" className="about-image" style={{ width: '80%' }} />
      
      <h3>단계별 과정</h3>
      <ol className="about-steps">
        <li><strong>1. 스크립트 추출:</strong> 맞춤형 스크립트 추출 알고리즘을 사용하여 동영상의 스크립트를 추출합니다.</li>
        <li><strong>2. 텍스트 전처리:</strong> 추출된 스크립트는 여러 전처리 단계를 거칩니다:
          <ul>
            <li><strong>토큰화:</strong> 텍스트를 개별 단어 또는 구로 나눕니다.</li>
            <li><strong>불용어 제거:</strong> 흔하지만 정보량이 적은 단어들을 필터링합니다.</li>
            <li><strong>특수문자 제거:</strong> 불필요한 특수 문자를 제거하여 텍스트를 정리합니다.</li>
            <li><strong>구두점 복원:</strong> 가독성을 높이기 위해 구두점을 추가합니다.</li>
          </ul>
        </li>
        <li><strong>3. 특징 추출:</strong> koBERT 모델을 사용하여 텍스트에서 의미 있는 특징을 추출합니다.</li>
        <li><strong>4. 텍스트 요약 생성:</strong> 추출된 특징을 바탕으로 동영상 내용의 간결한 요약본을 생성합니다.</li>
      </ol>
    </div>
  );
};

export default About;
