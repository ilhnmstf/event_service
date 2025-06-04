CREATE TABLE event (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    owner_id bigint NOT NULL,
    description varchar(4096) NOT NULL,
    address varchar(256) NOT NULL,
    city varchar(128) NOT NULL,
    country_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL,
    deleted boolean DEFAULT false NOT NULL,
    deleted_at timestamptz,
    start_at timestamptz NOT NULL,
    end_at timestamptz NOT NULL,
    created_at timestamptz DEFAULT current_timestamp,
    updated_at timestamptz DEFAULT current_timestamp
);

CREATE TABLE participant (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    event_id bigint NOT NULL,
    user_id bigint NOT NULL,
    created_at timestamptz DEFAULT current_timestamp,

    CONSTRAINT fk_event_id FOREIGN KEY (event_id) REFERENCES event (id) ON DELETE CASCADE
);