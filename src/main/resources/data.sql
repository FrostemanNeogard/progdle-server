CREATE TABLE IF NOT EXISTS languages (
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    release_year BIGINT,
    paradigm VARCHAR(50),
    typing VARCHAR(50),
    domain VARCHAR(50),
    memory_safe BOOLEAN,
    os VARCHAR(50)
);

INSERT INTO languages (id, name, release_year, paradigm, typing, domain, memory_safe, os)
VALUES
    ('a4d9c260-9c4f-4f3c-9e4b-2cb1ab7c474b', 'Python', 1991, 'Multi-paradigm', 'Dynamic, strong', 'Web, data science', true, 'Cross-platform'),
    ('b5e3d845-0b2c-4c97-8f54-60c7c55e8e5f', 'Java', 1995, 'Object-oriented', 'Static, strong', 'Enterprise, Android', true, 'Cross-platform');
