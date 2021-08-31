package tayna.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tayna.domain.Usuario;
import tayna.dto.UsuarioDTO;
import tayna.repositories.UsuarioRepository;
import tayna.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	public Usuario find(Integer id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id ));
	}

	public Usuario fromDTO(@Valid UsuarioDTO objDTO) {
		return new Usuario(objDTO.getId(), objDTO.getNomeUsuario(), objDTO.getSenha(), objDTO.getEmail());
	}

	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	
	}

	public List<Usuario> findAll() {
		return repo.findAll();
	}
}
