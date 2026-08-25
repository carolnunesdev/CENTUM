package com.centum.repository;

import com.centum.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByUsuarioId(Long usuarioId);

    Optional<Wishlist> findByUsuarioIdAndAlbumId(Long usuarioId, Long albumId);
}