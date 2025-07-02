package br.com.alura.ForumHub.domain.autores;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Table(name = "autores")
@Entity(name = "Autor")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    public String nome;

    @NotNull
    @Column(unique = true)
    public String email;

    public String senha;

    public String telefone;

    Curso curso;

    public Autor() {}

    public Autor(DadosRegistroAutor dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.telefone = dados.telefone();
        this.curso = dados.curso();
    }
}
