import cx_Oracle

# Oracle 데이터베이스에 연결
connection = cx_Oracle.connect('사용자명/비밀번호@호스트:포트번호/서비스이름')

# 바이너리 데이터 읽기
with open('C:/Users/user/Desktop/마이멜로디.png', 'rb') as file:
    binary_data = file.read()

# SQL 문 실행
cursor = connection.cursor()
sql = "INSERT INTO YOUR_TABLE_NAME (IMAGE_COLUMN_NAME) VALUES (:1)"
cursor.execute(sql, [binary_data])
connection.commit()

# 연결 종료
cursor.close()
connection.close()
