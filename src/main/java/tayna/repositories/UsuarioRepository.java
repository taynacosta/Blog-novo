package tayna.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tayna.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
