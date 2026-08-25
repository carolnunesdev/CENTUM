package com.centum.repository;

import com.centum.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByAlbumId(Integer albumId);

    List<Review> findByUsuarioId(Integer usuarioId);

    Optional<Review> findByUsuarioIdAndAlbumId(Integer usuarioId, Integer albumId);
}