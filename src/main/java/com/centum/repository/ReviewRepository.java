package com.centum.repository;

import com.centum.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByAlbumId(Long albumId);

    List<Review> findByUsuarioId(Long usuarioId);

    Optional<Review> findByUsuarioIdAndAlbumId(Long usuarioId, Long albumId);
}