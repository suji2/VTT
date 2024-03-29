//음성 테이블
CREATE TABLE voice ( 
	voice_id	INT		NOT NULL,
	video_id	INT		NOT NULL,
    conversion	VARCHAR(500)		NULL,
	voice_Url	VARCHAR(500)		NULL
);

//텍스트 테이블
CREATE TABLE text (
	text_Num	INT		NOT NULL,
	voice_id	INT		NOT NULL,
	video_id	INT		NOT NULL,
	text_Summary	VARCHAR(100)		NULL
);

//유튜브 댓글 테이블
CREATE TABLE yt_comment ( 
	user_id	INT		NOT NULL,
	video_id	INT		NOT NULL,
	comment_Save	VARCHAR(100)		NULL,
	comment_Num	INT		NULL,
	comment_Summary	VARCHAR(100)		NULL
);

//유튜브 동영상 테이블
CREATE TABLE video ( 
	video_id	INT		NOT NULL,
	video_Url	VARCHAR(500)		NULL,
	title	VARCHAR(100)		NULL,
	description	VARCHAR(500)		NULL,
	channel_Title	VARCHAR(500)		NULL,
	publisherd_At	DATE		NULL,
	thumbnail	VARCHAR(255)		NULL
);

//q&a 질문 테이블
CREATE TABLE question ( 
	question_id	INT		NOT NULL,
	answer	INT		NOT NULL,
	question_text	VARCHAR(500)		NULL,
	upload_date	DATE		NULL,
	modified_date	DATE		NULL,
	pw	VARCHAR(100)		NULL
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
	comment_Save	VARCHAR(500)		NULL,
	comment_Num	INT		NULL,
	comment_Summary	VARCHAR(300)		NULL,
	gm_info CHAR(1) CONSTRAINT booleantest_verified_CK
        CHECK(gm_info IN ('Y', 'N')) NULL
);