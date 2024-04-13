const fs = require('fs');
const getSubtitles = require('youtube-captions-scraper').getSubtitles;

getSubtitles({
  videoID: 'VxLF9cixOcE', // youtube video id
  lang: 'ko' // default: `en`
}).then(function(captions: any[]) {
  // captions 배열을 텍스트로 변환하여 파일에 저장
  const text = captions.map((caption: any) => caption.text).join('\n');
  fs.writeFileSync('captions.txt', text);
  console.log('전체 자막이 captions.txt 파일에 저장되었습니다.');
}).catch(function(error: any) {
  console.error('자막을 가져오는 중 오류가 발생했습니다:', error);
});
 