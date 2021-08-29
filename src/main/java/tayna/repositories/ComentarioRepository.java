package tayna.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tayna.domain.Comentarios;

public interface ComentarioRepository extends JpaRepository<Comentarios, Integer> {

}
