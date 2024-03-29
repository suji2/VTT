CREATE TABLE voice ( //음성 테이블
	voice_id	INT		NOT NULL,
	video_id	INT		NOT NULL,
    conversion	VARCHAR(500)		NULL,
	voice_Url	VARCHAR(500)		NULL
);

CREATE TABLE text ( //텍스트 테이블
	text_Num	INT		NOT NULL,
	voice_id	INT		NOT NULL,
	video_id	INT		NOT NULL,
	text_Summary	VARCHAR(100)		NULL
);

CREATE TABLE yt_comment ( //유튜브 댓글 테이블
	user_id	INT		NOT NULL,
	video_id	INT		NOT NULL,
	comment_Save	VARCHAR(100)		NULL,
	comment_Num	INT		NULL,
	comment_Summary	VARCHAR(100)		NULL
);

CREATE TABLE video ( //유튜브 동영상 테이블
	video_id	INT		NOT NULL,
	video_Url	VARCHAR(500)		NULL,
	title	VARCHAR(100)		NULL,
	description	VARCHAR(500)		NULL,
	channel_Title	VARCHAR(500)		NULL,
	publisherd_At	DATE		NULL,
	thumbnail	VARCHAR(255)		NULL
);

CREATE TABLE question ( //q&a 질문 테이블
	question_id	INT		NOT NULL,
	answer	INT		NOT NULL,
	question_text	VARCHAR(500)		NULL,
	upload_date	DATE		NULL,
	modified_date	DATE		NULL,
	pw	VARCHAR(100)		NULL
);

CREATE TABLE answer ( //q&a 답변 테이블
	answer	INT		NOT NULL,
	answer_text	VARCHAR(500)		NULL,
	answer_date	DATE		NULL,
	modified_date	DATE		NULL
);

CREATE TABLE answer_comment ( //q&a 답변 댓글 테이블
	question_id	INT		NOT NULL,
	comment_Save	VARCHAR(500)		NULL,
	comment_Num	INT		NULL,
	comment_Summary	VARCHAR(300)		NULL,
	gm_info BOOLEAN NULL
);

ALTER TABLE voice ADD CONSTRAINT PK_VOICE PRIMARY KEY (
	voice_id,
	video_id
);

ALTER TABLE text ADD CONSTRAINT PK_TEXT PRIMARY KEY (
	text_Num,
	voice_id,
	video_id
);

ALTER TABLE yt_comment ADD CONSTRAINT PK_YT_COMMENT PRIMARY KEY (
	user_id,
	video_id
);

ALTER TABLE video ADD CONSTRAINT PK_VIDEO PRIMARY KEY (
	video_id
);

ALTER TABLE question ADD CONSTRAINT PK_QUESTION PRIMARY KEY (
	question_id,
	answer
);

ALTER TABLE answer ADD CONSTRAINT PK_ANSWER PRIMARY KEY (
	answer
);

ALTER TABLE answer_comment ADD CONSTRAINT PK_ANSWER_COMMENT PRIMARY KEY (
	question_id,
	answer
);

ALTER TABLE voice ADD CONSTRAINT FK_video_TO_voice_1 FOREIGN KEY (
	video_id
)
REFERENCES video (
	video_id
);

ALTER TABLE text ADD CONSTRAINT FK_voice_TO_text_1 FOREIGN KEY (
	voice_id
)
REFERENCES voice (
	voice_id
);

ALTER TABLE text ADD CONSTRAINT FK_voice_TO_text_2 FOREIGN KEY (
	video_id
)
REFERENCES voice (
	video_id
);

ALTER TABLE yt_comment ADD CONSTRAINT FK_video_TO_yt_comment_1 FOREIGN KEY (
	video_id
)
REFERENCES video (
	video_id
);

ALTER TABLE question ADD CONSTRAINT FK_answer_TO_question_1 FOREIGN KEY (
	answer
)
REFERENCES answer (
	answer
);

ALTER TABLE answer_comment ADD CONSTRAINT FK_question_TO_answer_comment_1 FOREIGN KEY (
	question_id
)
REFERENCES question (
	question_id
);

ALTER TABLE answer_comment ADD CONSTRAINT FK_question_TO_answer_comment_2 FOREIGN KEY (
	answer
)
REFERENCES question (
	answer
);