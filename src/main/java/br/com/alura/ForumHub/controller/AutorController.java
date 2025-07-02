package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.domain.autores.Autor;
import br.com.alura.ForumHub.domain.autores.AutorRepository;
import br.com.alura.ForumHub.domain.autores.DadosDetalhamentoAutor;
import br.com.alura.ForumHub.domain.autores.DadosRegistroAutor;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository repository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<?> registerAutor(@RequestBody @Valid DadosRegistroAutor dados) {
        if (repository.existsByEmail(dados.email())) {
            return ResponseEntity.badRequest().body("❌ Já existe um autor com esse e-mail!");
        }
        var autor = new Autor(dados);
        repository.save(autor);
        return ResponseEntity.ok(new DadosDetalhamentoAutor(autor));
    }


}
