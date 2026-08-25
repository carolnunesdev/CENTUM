package com.centum.service;

import com.centum.entity.Album;
import com.centum.entity.Artist;
import com.centum.repository.AlbumRepository;
import com.centum.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public AlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    public Album criar(Album album, Integer artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artista não encontrado"));
        album.setArtist(artist);
        return albumRepository.save(album);
    }

    public Album buscarPorId(Integer id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Álbum não encontrado"));
    }

    public List<Album> listarPorArtista(Integer artistId) {
        return albumRepository.findByArtistId(artistId);
    }

    public List<Album> listarTodos() {
        return albumRepository.findAll();
    }

    public void deletar(Integer id) {
        buscarPorId(id);
        albumRepository.deleteById(id);

    }

    // ultima atalização 25/08
    // tem umas coisas que eu queria colocar em album ja que é meio que uma das
    // classes principais do sistema, aqui em servie nao deveriamos colocar metodos
    // de busca por nome, genero, ano, etc? ou isso seria feito em albumrepository?
    // alem disso tem muita info qur podemos colocar futuramente.
}