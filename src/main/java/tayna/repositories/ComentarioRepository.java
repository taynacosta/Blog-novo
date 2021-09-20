package tayna.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tayna.domain.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer>{

}
