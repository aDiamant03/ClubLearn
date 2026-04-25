CREATE TABLE attempts
(
    id               UUID PRIMARY KEY                       NOT NULL,
    user_id          UUID                                   NOT NULL,
    task_id          UUID                                   NOT NULL,
    attempt_time     TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
    submitted_answer TEXT                                   NOT NULL,
    is_correct       BOOLEAN                                NOT NULL
);

ALTER TABLE attempts
    ADD CONSTRAINT fk_attempts_users
        FOREIGN KEY (user_id)
            REFERENCES users (id);

ALTER TABLE attempts
    ADD CONSTRAINT fk_attempts_tasks
        FOREIGN KEY (task_id)
            REFERENCES tasks (id);