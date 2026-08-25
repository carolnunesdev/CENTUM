package com.centum.repository;

import com.centum.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUsuarioId(Integer usuarioId);

    Optional<Favorite> findByUsuarioIdAndAlbumId(Integer usuarioId, Integer albumId);
}