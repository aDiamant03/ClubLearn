CREATE TABLE tasks
(
    id             BIGSERIAL PRIMARY KEY,
    teacher_id     BIGINT,
    title          VARCHAR(255)                           NOT NULL,
    description    TEXT,
    correct_answer TEXT,
    difficulty     VARCHAR(50)  DEFAULT 'EASY'             NOT NULL,
    subject        VARCHAR(255),
    topic          VARCHAR(255),
    points         INT          DEFAULT 0                  NOT NULL,
    hint           TEXT,
    created_at     TIMESTAMP    DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    updated_at     TIMESTAMP
);
