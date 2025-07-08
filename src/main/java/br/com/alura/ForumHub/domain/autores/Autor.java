package br.com.alura.ForumHub.domain.autores;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "autores")
@Entity(name = "Autor")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Autor implements UserDetails {

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

    public Autor(String nome, String email, String encrypterPassword, String telefone, Curso curso) {
        this.nome = nome;
        this.email = email;
        this.senha = encrypterPassword;
        this.telefone = telefone;
        this.curso = curso;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
