package com.centum.service;

import com.centum.entity.Artist;
import com.centum.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist criar(Artist artist) {
        return artistRepository.save(artist);
    }

    public Artist buscarPorId(Integer id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artista não encontrado"));
    }

    public List<Artist> listarTodos() {
        return artistRepository.findAll();
    }

    public void deletar(Integer id) {
        buscarPorId(id);
        artistRepository.deleteById(id); // aqui acredito que teria que colocar uma condição pra que os usuarios nao
                                         // consigam deletar artistas do sistema em si, somente do seu perfil
                                         // (whisilist, favorites etc.)
    }
    // tem muitos metodos que se repetem, como criar, buscarPorId, listarTodos e
    // deletar. Talvez seja interessante criar uma classe abstrata para esses
    // metodos que se repetem, e fazer com que as classes de serviço extendam essa
    // classe abstrata.

}