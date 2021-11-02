package tayna.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import tayna.domain.Usuario;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Integer> {

	Usuario findByNomeUsuario(String nomeUsuario);

	Usuario findByEmail(String email);
	
	Page<Usuario> findAll(Pageable pageable);


}
