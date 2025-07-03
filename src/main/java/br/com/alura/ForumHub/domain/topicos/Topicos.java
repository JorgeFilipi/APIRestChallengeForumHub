package br.com.alura.ForumHub.domain.topicos;


import br.com.alura.ForumHub.domain.autores.Autor;
import br.com.alura.ForumHub.domain.autores.Curso;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "forum")
@Entity(name = "Forum")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCadastro;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @Enumerated(EnumType.STRING)
    private Curso curso;


    public Topicos(@Valid DadosCadastroTopicos dados, Autor autor) {
       this.titulo = dados.titulo();
       this.mensagem = dados.mensagem();
       this.dataCadastro = LocalDateTime.now();
       this.status = true;
       this.autor = autor;
       this.curso = dados.curso();
    }

    public void atualizarInformacoes(@Valid DadosCadastroTopicos dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if (dados.curso() != null) {
            this.curso = dados.curso();
        }
    }

    public record DadosDetalhamentoTopicos(Topicos topico) {
    }

    public void excluir() {
        this.status = false;
    }
}
