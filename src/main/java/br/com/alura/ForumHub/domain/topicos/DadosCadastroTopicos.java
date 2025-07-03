package br.com.alura.ForumHub.domain.topicos;

import br.com.alura.ForumHub.domain.autores.Curso;

public record DadosCadastroTopicos(String titulo, String mensagem, Curso curso, Long autorId) {
}
