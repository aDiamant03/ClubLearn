CREATE TABLE answers
(
    id              BIGSERIAL PRIMARY KEY,
    task_id         BIGINT                                  NOT NULL,
    student_id      BIGINT                                  NOT NULL,
    text            TEXT                                    NOT NULL,
    status          VARCHAR(50) DEFAULT 'SENT'              NOT NULL,
    teacher_comment TEXT,
    score           INT,
    created_at      TIMESTAMP   DEFAULT CURRENT_TIMESTAMP   NOT NULL,
    checked_at      TIMESTAMP,

    CONSTRAINT fk_answers_tasks
        FOREIGN KEY (task_id)
            REFERENCES tasks (id)
            ON DELETE CASCADE
);
