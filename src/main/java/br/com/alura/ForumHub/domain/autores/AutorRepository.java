package br.com.alura.ForumHub.domain.autores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    boolean existsByEmail(String email);

    UserDetails findByEmail(String email);
}
