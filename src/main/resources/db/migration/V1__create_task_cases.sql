CREATE TABLE IF NOT EXISTS task_cases (
  id            SERIAL PRIMARY KEY,
  case_number   VARCHAR(64),
  title         VARCHAR(255) NOT NULL,
  description   TEXT,
  status       VARCHAR(32)  NOT NULL,
  created_date  TIMESTAMP   NOT NULL,
  due_date      TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_task_cases_status   ON task_cases(status);
CREATE INDEX IF NOT EXISTS idx_task_cases_due_date ON task_cases(due_date);