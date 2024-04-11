const fs = require('fs');
const oracledb = require('oracledb');

async function saveBlobAsFile() {
  try {
    // Oracle 데이터베이스 연결
    const connection = await oracledb.getConnection({
      user: 'C##suji',
      password: '1234',
      connectString: '192.168.34.4:1521/orcl'
    });

    // 이미지 데이터를 쿼리하여 가져오기
    const result = await connection.execute(
      'SELECT MODIFIED FROM TEST WHERE ANSWER_TEXT = :id', 
      [2],
      {
        fetchInfo: {
          MODIFIED: { type: oracledb.BUFFER }
        }
      }
    );

    // 결과가 있는지 확인
    if (result.rows.length > 0) {
      // 이미지 데이터 가져오기
      const bufferData = result.rows[0][0];

      // 이미지 데이터를 파일로 저장
      fs.writeFileSync('저장된 이미지.png', bufferData);

      console.log('이미지 파일로 저장 완료');
    } else {
      console.log('해당하는 데이터가 없습니다.');
    }

    // 연결 종료
    await connection.close();
  } catch (error) {
    console.error('오류 발생:', error);
  }
}

saveBlobAsFile();
