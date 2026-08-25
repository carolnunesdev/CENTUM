package com.centum.service;

import com.centum.entity.Album;
import com.centum.entity.Review;
import com.centum.entity.Usuario;
import com.centum.repository.AlbumRepository;
import com.centum.repository.ReviewRepository;
import com.centum.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UsuarioRepository usuarioRepository;
    private final AlbumRepository albumRepository;

    public ReviewService(ReviewRepository reviewRepository,
            UsuarioRepository usuarioRepository,
            AlbumRepository albumRepository) {
        this.reviewRepository = reviewRepository;
        this.usuarioRepository = usuarioRepository;
        this.albumRepository = albumRepository;
    }

    public Review criar(Integer usuarioId, Integer albumId, Short rating, String comment) {
        if (rating < 0 || rating > 5) {
            throw new RuntimeException("A nota precisa estar entre 0 e 5");
        }
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Álbum não encontrado"));
        if (reviewRepository.findByUsuarioIdAndAlbumId(usuarioId, albumId).isPresent()) {
            throw new RuntimeException("Esse usuário já avaliou esse álbum");
        }
        return reviewRepository.save(new Review(rating, comment, usuario, album));
    }

    public Review buscarPorId(Integer id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review não encontrada"));
    }

    public List<Review> listarPorAlbum(Integer albumId) {
        return reviewRepository.findByAlbumId(albumId);
    }

    public List<Review> listarPorUsuario(Integer usuarioId) {
        return reviewRepository.findByUsuarioId(usuarioId);
    }

    public void deletar(Integer id) {
        buscarPorId(id);
        reviewRepository.deleteById(id);
    }
    // tem uma coisa que eu quero colocar aqui é a meidia de avaliaçãoes e
    // principalmente as estrelinhas, esse é um dos marcos do letterboxd e quero
    // reprodduzir aqui!!!!
}