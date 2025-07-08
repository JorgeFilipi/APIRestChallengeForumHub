package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.domain.autores.AutorRepository;
import br.com.alura.ForumHub.domain.topicos.DadosCadastroTopicos;
import br.com.alura.ForumHub.domain.topicos.DadosListagemTopicos;
import br.com.alura.ForumHub.domain.topicos.Topicos;
import br.com.alura.ForumHub.domain.topicos.TopicosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class ForumController {

    @Autowired
    private TopicosRepository topicosRepository;

    @Autowired
    private AutorRepository autorRepository;

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopicos>> lista(@PageableDefault(size = 10, sort = "dataCadastro", direction = Sort.Direction.ASC) Pageable paginacao) {
        var forums = topicosRepository.findAllByStatusTrue(paginacao).map(DadosListagemTopicos::new);
        return ResponseEntity.ok(forums);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroTopicos dados, UriComponentsBuilder uriBuilder) {
        var autor = autorRepository.getReferenceById(dados.autorId());
        var forum = new Topicos(dados, autor);
        topicosRepository.save(forum);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(forum.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosListagemTopicos(forum));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var topico = topicosRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(new Topicos.DadosDetalhamentoTopicos(topico));
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid DadosCadastroTopicos dados) {
        var topico = topicosRepository.findById(id).orElseThrow(() -> new RuntimeException("Tópico não encontrado!"));
        topico.atualizarInformacoes(dados);
        topicosRepository.save(topico);
        return ResponseEntity.ok(new Topicos.DadosDetalhamentoTopicos(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
       var topicoOptional = topicosRepository.findByIdAndStatusTrue(id);
        if (topicoOptional.isPresent()) {
           var topico = topicosRepository.findById(id).orElseThrow();
           topico.excluir();
       } else {
            return ResponseEntity.ok("Tópico não encontrado!");
        }
        return ResponseEntity.noContent().build();
    }

}
