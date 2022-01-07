package tayna.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import tayna.domain.Usuario;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Integer> {

	@Transactional(readOnly=true)
	Usuario findByNomeUsuario(String nomeUsuario);

	@Transactional(readOnly=true)
	Usuario findByEmail(String email);
	
	Page<Usuario> findAll(Pageable pageable);

}
