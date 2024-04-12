const fs = require('fs');
const oracledb = require('oracledb');

// 이미지 파일을 바이너리로 읽기
const binaryData = fs.readFileSync('C:/Users/user/Documents/VTT/DB/thumbnail.jpg');

// 오라클 데이터베이스에 연결
oracledb.getConnection({
  user: 'C##suji',
  password: '1234',
  connectString: '192.168.34.4:1521/orcl'
})
.then(connection => {
  // 이미지 데이터 삽입
  connection.execute(
    `INSERT INTO YOUTUBE (ID, VOICE_ID, PW, SUB_CHANNEL, THUMBNAIL, DESCRIPTION, SEARCH, YT_COMMENT) VALUES 
    ('tnwl3109', 1, 1234, '자바공장', :blobData, '이것이 SQL Server다 01. DBMS 개요와 SQL Server 2016 소개(1)', 
    '자바', '자바 어렵다')`,
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
