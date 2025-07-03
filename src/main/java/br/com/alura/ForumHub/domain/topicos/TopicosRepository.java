package br.com.alura.ForumHub.domain.topicos;

import aj.org.objectweb.asm.commons.Remapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicosRepository extends JpaRepository<Topicos, Long> {

    Page<Topicos> findAllByStatusTrue(Pageable paginacao);

    Optional<Topicos> findByIdAndStatusTrue(Long id);
}
