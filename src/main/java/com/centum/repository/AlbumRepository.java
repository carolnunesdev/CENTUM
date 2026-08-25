package com.centum.repository;

import com.centum.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByArtistId(Long artistId);

    Optional<Album> findBySpotifyId(String spotifyId);
}