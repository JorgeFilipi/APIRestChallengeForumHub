package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.domain.autores.Autor;
import br.com.alura.ForumHub.domain.autores.AutorRepository;
import br.com.alura.ForumHub.domain.autores.DadosDetalhamentoAutor;
import br.com.alura.ForumHub.domain.autores.DadosRegistroAutor;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository repository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity registerAutor(@RequestBody @Valid DadosRegistroAutor dados) {
        if (repository.existsByEmail(dados.email())) {
            return ResponseEntity.badRequest().body("❌ Já existe um autor com esse e-mail!");
        }
        String encrypterPassword = new BCryptPasswordEncoder().encode(dados.senha());
        var autor = new Autor(dados.nome(), dados.email(), encrypterPassword, dados.telefone(), dados.curso());
        this.repository.save(autor);
        return ResponseEntity.ok(new DadosDetalhamentoAutor(autor));
    }

    @GetMapping
    public ResponseEntity<List<Autor>> listAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhaAutor(@PathVariable Long id) {
        var autor = this.repository.findById(id).get();
        return ResponseEntity.ok(autor);
    }
}
