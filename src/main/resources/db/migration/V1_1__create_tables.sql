CREATE TABLE public."timed_metric"
(
    id                   bigserial              NOT NULL,
    metric_type          character varying(512) NOT NULL,
    class_name           character varying(512) NOT NULL,
    method_name          character varying(512) NOT NULL,
    execution_time       bigint,
    execution_time_start bigint,
    execution_time_end   bigint,
    started_date         timestamp with time zone,
    ended_date           timestamp with time zone,
    PRIMARY KEY (id)
);

CREATE TABLE public."garbage_collector_metric"
(
    id               bigserial              NOT NULL,
    metric_type      character varying(512) NOT NULL,
    execution_time   bigint,
    count_of_garbage bigint,
    started_date     timestamp with time zone,
    ended_date       timestamp with time zone,
    PRIMARY KEY (id)
);

CREATE TABLE public."disk_metric"
(
    id           bigserial              NOT NULL,
    metric_type  character varying(512) NOT NULL,
    disk_name    character varying(512) NOT NULL,
    disk_path    character varying(512) NOT NULL,
    total_space  bigint,
    used_space   bigint,
    free_space   bigint,
    started_date timestamp with time zone,
    ended_date   timestamp with time zone,
    PRIMARY KEY (id)
);

CREATE TABLE public."jvm_heap_metric"
(
    id             bigserial              NOT NULL,
    metric_type    character varying(512) NOT NULL,
    available_heap bigint,
    used_heap      bigint,
    max_heap       bigint,
    free_memory    bigint,
    started_date   timestamp with time zone,
    ended_date     timestamp with time zone,
    PRIMARY KEY (id)
);

CREATE TABLE public."listener_settings"
(
    id                  bigserial              NOT NULL,
    name                character varying(512) NOT NULL,
    bootstrap_server    character varying(512) NOT NULL,
    key_deserializer    character varying(512) NOT NULL,
    value_deserializer  character varying(512) NOT NULL,
    session_timeout     bigint                 NOT NULL,
    heart_beat_interval bigint                 NOT NULL,
    group_id            character varying(512) NOT NULL,
    create_at           timestamp with time zone,
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE public."listener_type"
(
    id        bigserial              NOT NULL,
    name      character varying(512) NOT NULL,
    create_at timestamp with time zone,
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE public."listener"
(
    id                   bigserial              NOT NULL,
    name                 character varying(512) NOT NULL,
    topic_name           character varying(512) NOT NULL,
    create_at            timestamp with time zone,
    enabled              int,
    listener_settings_id bigint,
    listener_type_id     bigint,
    PRIMARY KEY (id),
    UNIQUE (name),
    FOREIGN KEY (listener_settings_id) REFERENCES public."listener_settings" (id),
    FOREIGN KEY (listener_type_id) REFERENCES public."listener_type" (id)
);

CREATE TABLE public."listener_thread"
(
    id          bigserial              NOT NULL,
    name        character varying(512) NOT NULL,
    create_at   timestamp with time zone,
    enabled     int,
    listener_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (listener_id) REFERENCES public."listener" (id)
);

CREATE TABLE public."role"
(
    id        bigserial               NOT NULL,
    name      character varying(512)  NOT NULL,
    password  character varying(1024) NOT NULL,
    create_at timestamp with time zone,
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE public."permission"
(
    id        bigserial              NOT NULL,
    name      character varying(512) NOT NULL,
    create_at timestamp with time zone,
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE public."roles_permissions"
(
    id            bigserial NOT NULL,
    role_id       bigint,
    permission_id bigint,
    PRIMARY KEY (id),
);

CREATE TABLE public."user"
(
    id          bigserial               NOT NULL,
    user_name   character varying(512)  NOT NULL,
    password    character varying(1024) NOT NULL,
    email       character varying(1024) NOT NULL,
    create_at   timestamp with time zone,
    listener_id bigint,
    role_id     bigint,
    PRIMARY KEY (id),
    UNIQUE (user_name),
    FOREIGN KEY (listener_id) REFERENCES public."listener" (id)
);


