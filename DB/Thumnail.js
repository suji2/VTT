const fs = require('fs');
const axios = require('axios'); // 혹은 다른 HTTP 요청 라이브러리 사용

async function saveYoutubeThumbnail(videoId, filename) {
    try {
        // 유튜브 썸네일 이미지 URL 생성
        const thumbnailUrl = 'https://img.youtube.com/vi/Heh80xExdq0/0.jpg';

        // HTTP GET 요청 보내서 썸네일 이미지 가져오기
        const response = await axios.get(thumbnailUrl, {
            responseType: 'arraybuffer' // 데이터를 ArrayBuffer로 받음
        });

        // 가져온 이미지를 로컬 파일로 저장
        fs.writeFileSync(filename, Buffer.from(response.data));

        console.log('썸네일 이미지 저장 완료');
    } catch (error) {
        console.error('오류 발생:', error);
    }
}

// 함수 호출 시 유튜브 비디오의 고유 ID와 저장할 파일의 경로를 전달합니다.
saveYoutubeThumbnail('Heh80xExdq0', 'C:/Users/user/Documents/VTT/DB/thumbnail.jpg');
