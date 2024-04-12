//음성 테이블
CREATE TABLE voice ( 
	voice_id	INT		NOT NULL,
	conversion	VARCHAR(100)		NULL,
	voice_Url	VARCHAR(100)		NULL
);

//텍스트 테이블
CREATE TABLE text (
	text_Num	INT		NOT NULL,
	voice_id	INT		NOT NULL,
	text_Summary	VARCHAR(250)		NULL
);

//유튜브 댓글 요약 테이블
CREATE TABLE comment_summary (
	video_id	VARCHAR(50)		NOT NULL
);

//사용자 정보 테이블
CREATE TABLE user_info (
	user_id	VARCHAR(50)		NOT NULL,
	pw	VARCHAR(50)		NULL
);

//q&a 질문 테이블
CREATE TABLE question (
	question_id	INT		NOT NULL,
	question_text	VARCHAR(500)		NULL,
	upload_date	DATE		NULL,
	modified_date	DATE		NULL,
	subject	VARCHAR(50)		NULL
);

//q&a 답변 테이블
CREATE TABLE answer (
	answer_id	INT		NOT NULL,
	question_id	INT		NOT NULL,
	answer_text	VARCHAR(500)		NULL,
	answer_date	DATE		NULL,
	modified_date	DATE		NULL
);

//q&a 답변 댓글 테이블
CREATE TABLE answer_comment ( 
	question_id	INT		NOT NULL,
	qna_comment	VARCHAR(500)		NULL,
	comment_Num	INT		NULL,
	manager	CHAR(1)	CONSTRAINT booleantest_verified_CK
        CHECK(gm_info IN ('Y', 'N')) NULL
);