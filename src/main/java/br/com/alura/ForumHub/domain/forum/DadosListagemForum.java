package br.com.alura.ForumHub.domain.forum;

import br.com.alura.ForumHub.domain.autores.Curso;

import java.time.LocalDateTime;

public record DadosListagemForum(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCadastro,
        Boolean status,
        String nomeAutor,
        Curso curso
) {
    public DadosListagemForum(Forum forum) {
        this(
                forum.getId(),
                forum.getTitulo(),
                forum.getMensagem(),
                forum.getDataCadastro(),
                forum.getStatus(),
                forum.getAutor().getNome(),
                forum.getCurso()
        );
    }
}
