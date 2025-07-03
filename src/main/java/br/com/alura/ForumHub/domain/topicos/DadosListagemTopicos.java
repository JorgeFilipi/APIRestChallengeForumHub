package br.com.alura.ForumHub.domain.topicos;

import br.com.alura.ForumHub.domain.autores.Curso;

import java.time.format.DateTimeFormatter;

public record DadosListagemTopicos(
        Long id,
        String titulo,
        String mensagem,
        String dataCadastro,
        String nomeAutor,
        Curso curso
) {
    public DadosListagemTopicos(Topicos topicos) {
        this(
                topicos.getId(),
                topicos.getTitulo(),
                topicos.getMensagem(),
                topicos.getDataCadastro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                topicos.getAutor().getNome(),
                topicos.getCurso()
        );
    }
}
