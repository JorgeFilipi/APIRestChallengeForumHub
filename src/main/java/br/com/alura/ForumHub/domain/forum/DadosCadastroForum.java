package br.com.alura.ForumHub.domain.forum;

import br.com.alura.ForumHub.domain.autores.Curso;

public record DadosCadastroForum(String titulo, String mensagem, Curso curso, Long autorId) {
}
