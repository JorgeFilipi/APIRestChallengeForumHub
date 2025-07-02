package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.domain.autores.AutorRepository;
import br.com.alura.ForumHub.domain.forum.DadosCadastroForum;
import br.com.alura.ForumHub.domain.forum.Forum;
import br.com.alura.ForumHub.domain.forum.ForumRepository;
import br.com.alura.ForumHub.domain.forum.DadosListagemForum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/forum")
public class ForumController {

    @Autowired
    private ForumRepository forumRepository;

    @Autowired
    private AutorRepository autorRepository;

    @GetMapping
    public ResponseEntity<Page<DadosListagemForum>> lista(@PageableDefault(size = 10) Pageable paginacao) {
        var forums = forumRepository.findAll(paginacao).map(DadosListagemForum::new);
        return ResponseEntity.ok(forums);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroForum dados, UriComponentsBuilder uriBuilder) {
        var autor = autorRepository.getReferenceById(dados.autorId()); // busca eficiente com proxy
        var forum = new Forum(
                null,
                dados.titulo(),
                dados.mensagem(),
                LocalDateTime.now(),
                true, // status ativo
                autor,
                dados.curso()
        );

        forumRepository.save(forum);

        var uri = uriBuilder.path("/forum/{id}").buildAndExpand(forum.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosListagemForum(forum));
    }

}
