
DROP TABLE IF EXISTS wishlist  CASCADE;
DROP TABLE IF EXISTS favorite  CASCADE;
DROP TABLE IF EXISTS review    CASCADE;
DROP TABLE IF EXISTS album     CASCADE;
DROP TABLE IF EXISTS artist    CASCADE;
DROP TABLE IF EXISTS usuario   CASCADE;

-- ============================================================
-- USUARIO
CREATE TABLE usuario (
    id          SERIAL PRIMARY KEY,
    username    VARCHAR(100)        NOT NULL,
    email       VARCHAR(150)        NOT NULL UNIQUE,
    password_hash VARCHAR(255)      NOT NULL,          
    avatar_url  TEXT,
    created_at  TIMESTAMPTZ         NOT NULL DEFAULT now()
);

-- ============================================================
-- ARTIST
-- ============================================================
CREATE TABLE artist (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    slug VARCHAR(170) UNIQUE       
);

-- ============================================================
-- ALBUM
-- ============================================================
CREATE TABLE album (
    id           SERIAL PRIMARY KEY,
    title        VARCHAR(150)  NOT NULL,
    release_date DATE,
    cover_url    TEXT,
    spotify_id   VARCHAR(100)  UNIQUE,    
    artist_id    INT           NOT NULL REFERENCES artist(id) ON DELETE CASCADE,
    genre        VARCHAR(100)
);

CREATE INDEX idx_album_artist ON album(artist_id);

-- ============================================================
-- REVIEW
-- ============================================================
CREATE TABLE review (
    id         SERIAL PRIMARY KEY,
    rating     SMALLINT      NOT NULL CHECK (rating BETWEEN 0 AND 5),
    comment    TEXT,
    created_at TIMESTAMPTZ   NOT NULL DEFAULT now(),
    user_id    INT           NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    album_id   INT           NOT NULL REFERENCES album(id)  ON DELETE CASCADE,
    UNIQUE (user_id, album_id)          -- 1 review por usuário por álbum
);

CREATE INDEX idx_review_album ON review(album_id);

-- ============================================================
-- FAVORITE
-- ============================================================
CREATE TABLE favorite (
    id         SERIAL PRIMARY KEY,
    user_id    INT         NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    album_id   INT         NOT NULL REFERENCES album(id)  ON DELETE CASCADE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    UNIQUE (user_id, album_id)
);

-- ============================================================
-- WISHLIST
-- ============================================================
CREATE TABLE wishlist (
    id         SERIAL PRIMARY KEY,
    user_id    INT         NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    album_id   INT         NOT NULL REFERENCES album(id)  ON DELETE CASCADE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    UNIQUE (user_id, album_id)
);

