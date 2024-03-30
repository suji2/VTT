//���� ���̺�
CREATE TABLE voice ( 
	voice_id	INT		NOT NULL,
	video_id	INT		NOT NULL,
    conversion	VARCHAR(500)		NULL,
	voice_Url	VARCHAR(500)		NULL
);

//�ؽ�Ʈ ���̺�
CREATE TABLE text (
	text_Num	INT		NOT NULL,
	voice_id	INT		NOT NULL,
	video_id	INT		NOT NULL,
	text_Summary	VARCHAR(100)		NULL
);

//��Ʃ�� ��� ���̺�
CREATE TABLE yt_comment ( 
	user_id	INT		NOT NULL,
	video_id	INT		NOT NULL,
	comment_Save	VARCHAR(100)		NULL,
	comment_Num	INT		NULL,
	comment_Summary	VARCHAR(100)		NULL
);

//��Ʃ�� ������ ���̺�
CREATE TABLE video ( 
	video_id	INT		NOT NULL,
	video_Url	VARCHAR(500)		NULL,
	title	VARCHAR(100)		NULL,
	description	VARCHAR(500)		NULL,
	channel_Title	VARCHAR(500)		NULL,
	publisherd_At	DATE		NULL,
	thumbnail	VARCHAR(255)		NULL
);

//q&a ���� ���̺�
CREATE TABLE question ( 
	question_id	INT		NOT NULL,
	answer	INT		NOT NULL,
	question_text	VARCHAR(500)		NULL,
	upload_date	DATE		NULL,
	modified_date	DATE		NULL,
	pw	VARCHAR(100)		NULL
);

//q&a �亯 ���̺�
CREATE TABLE answer ( 
    answer_id	INT		NOT NULL,
	question_id	INT		NOT NULL,
	answer_text	VARCHAR(500)		NULL,
	answer_date	DATE		NULL,
	modified_date	DATE		NULL
);

//q&a �亯 ��� ���̺�
CREATE TABLE answer_comment ( 
	question_id	INT		NOT NULL,
	comment_Save	VARCHAR(500)		NULL,
	comment_Num	INT		NULL,
	comment_Summary	VARCHAR(300)		NULL,
	gm_info CHAR(1) CONSTRAINT booleantest_verified_CK
        CHECK(gm_info IN ('Y', 'N')) NULL
);