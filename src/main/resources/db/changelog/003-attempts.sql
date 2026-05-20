CREATE TABLE tasks
(
    id             UUID PRIMARY KEY                       NOT NULL,
    user_id        UUID                                   NOT NULL,
    creation_time  TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
    correct_answer TEXT                                   NOT NULL
);

ALTER TABLE tasks
ADD CONSTRAINT fk_tasks_users
FOREIGN KEY (user_id)
REFERENCES users (id);
