package tayna.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tayna.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Usuario findByNomeUsuario(String nomeUsuario);

	Usuario findByEmail(String email);


}
