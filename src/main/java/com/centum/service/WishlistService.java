package com.centum.service;

import com.centum.entity.Album;
import com.centum.entity.Usuario;
import com.centum.entity.Wishlist;
import com.centum.repository.AlbumRepository;
import com.centum.repository.UsuarioRepository;
import com.centum.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UsuarioRepository usuarioRepository;
    private final AlbumRepository albumRepository;

    public WishlistService(WishlistRepository wishlistRepository,
            UsuarioRepository usuarioRepository,
            AlbumRepository albumRepository) {
        this.wishlistRepository = wishlistRepository;
        this.usuarioRepository = usuarioRepository;
        this.albumRepository = albumRepository;
    }

    public Wishlist criar(Integer usuarioId, Integer albumId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Álbum não encontrado"));
        if (wishlistRepository.findByUsuarioIdAndAlbumId(usuarioId, albumId).isPresent()) {
            throw new RuntimeException("Esse álbum já está na wishlist desse usuário");
        }
        return wishlistRepository.save(new Wishlist(usuario, album));
    }

    public List<Wishlist> listarPorUsuario(Integer usuarioId) {
        return wishlistRepository.findByUsuarioId(usuarioId);
    }

    public void remover(Integer usuarioId, Integer albumId) {
        Wishlist wishlist = wishlistRepository.findByUsuarioIdAndAlbumId(usuarioId, albumId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado na wishlist"));
        wishlistRepository.deleteById(wishlist.getId());
    }
}
