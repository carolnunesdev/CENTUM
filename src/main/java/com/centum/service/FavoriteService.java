package com.centum.service;

import com.centum.entity.Album;
import com.centum.entity.Favorite;
import com.centum.entity.Usuario;
import com.centum.repository.AlbumRepository;
import com.centum.repository.FavoriteRepository;
import com.centum.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UsuarioRepository usuarioRepository;
    private final AlbumRepository albumRepository;

    public FavoriteService(FavoriteRepository favoriteRepository,
            UsuarioRepository usuarioRepository,
            AlbumRepository albumRepository) {
        this.favoriteRepository = favoriteRepository;
        this.usuarioRepository = usuarioRepository;
        this.albumRepository = albumRepository;
    }

    public Favorite criar(Integer usuarioId, Integer albumId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Álbum não encontrado"));
        if (favoriteRepository.findByUsuarioIdAndAlbumId(usuarioId, albumId).isPresent()) {
            throw new RuntimeException("Esse álbum já está nos favoritos desse usuário");
        }
        return favoriteRepository.save(new Favorite(usuario, album));
    }

    public List<Favorite> listarPorUsuario(Integer usuarioId) {
        return favoriteRepository.findByUsuarioId(usuarioId);
    }

    public void remover(Integer usuarioId, Integer albumId) {
        Favorite favorite = favoriteRepository.findByUsuarioIdAndAlbumId(usuarioId, albumId)
                .orElseThrow(() -> new RuntimeException("Favorito não encontrado"));
        favoriteRepository.deleteById(favorite.getId());
    }
}