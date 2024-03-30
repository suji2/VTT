const fs = require('fs');
const oracledb = require('oracledb');

// 이미지 파일을 바이너리로 읽기
const binaryData = fs.readFileSync('C:/Users/user/Desktop/마이멜로디.png');

// 오라클 데이터베이스에 연결
oracledb.getConnection({
  user: 'C##suji',
  password: '1234',
  connectString: '192.168.34.4:1521/orcl'
})
.then(connection => {
  // 이미지 데이터 삽입
  connection.execute(
    `INSERT INTO TEST (ANSWER_TEXT, MODIFIED) VALUES (2, :blobData)`,
    [binaryData],
    { autoCommit: true },
    (err, result) => {
      if (err) {
        console.error(err);
        return;
      }
      console.log('Image inserted successfully');
      // 작업 완료 후 연결 종료
      connection.close();
    }
  );
})
.catch(err => {
  // 연결 실패 시 에러 처리
  console.error(err);
});
