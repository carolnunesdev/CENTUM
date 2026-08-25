package com.centum.service;

import com.centum.entity.Usuario;
import com.centum.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criar(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("Usuário com esse email já existe");
        }
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new RuntimeException("Usuário com esse username já existe");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public void deletar(Integer id) {
        buscarPorId(id);
        usuarioRepository.deleteById(id);
    }

    public void deletarPorEmail(String email) {
        Usuario usuario = buscarPorEmail(email);
        usuarioRepository.deleteById(usuario.getId());
    }
    // adicionar um método para atualizar o usuário, caso seja necessário.
    // adicionar um script de segurança na senha. nao deixar em texto puro. usar
    // bcrypt para criptografar a senha.
}