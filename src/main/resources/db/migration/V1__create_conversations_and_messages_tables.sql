CREATE TABLE conversations (
    id         UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE conversation_participants (
    conversation_id UUID NOT NULL REFERENCES conversations (id) ON DELETE CASCADE,
    user_id         UUID NOT NULL,
    PRIMARY KEY (conversation_id, user_id)
);

CREATE TABLE messages (
    id              UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
    conversation_id UUID        NOT NULL REFERENCES conversations (id) ON DELETE CASCADE,
    sender_id       UUID        NOT NULL,
    content         TEXT        NOT NULL,
    sent_at         TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    is_read         BOOLEAN     NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_conversation_participants_user ON conversation_participants (user_id);
CREATE INDEX idx_messages_conversation ON messages (conversation_id, sent_at ASC);
