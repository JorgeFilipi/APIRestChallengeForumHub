package br.com.alura.ForumHub.domain.autores;

public record DadosDetalhamentoAutor(Long id,
                                     String nome,
                                     String email,
                                     String telefone,
                                     Curso curso) {
    public DadosDetalhamentoAutor(Autor autor) {
        this(autor.getId(), autor.getNome(), autor.getEmail(), autor.getTelefone(), autor.getCurso());
    }
}
