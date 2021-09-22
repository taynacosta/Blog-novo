package tayna.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

	public Usuario save(Usuario usuario) {
		return repo.save(usuario);
		
	}/*private void updateDate(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateDate(newObj, obj);
		return repo.save(newObj);
	}*/
	private void updateDate(Usuario usuario, Usuario novoUsuario) {
			usuario.setId(novoUsuario.getId());
		    usuario.setNomeUsuario(novoUsuario.getNomeUsuario());
		    usuario.setEmail(novoUsuario.getEmail());
		    usuario.setSenha(novoUsuario.getSenha());
	}
	public Usuario update(Usuario usuario) {
		Usuario novoUsuario = find(usuario.getId());
		updateDate(novoUsuario, usuario);
		return repo.save(novoUsuario);
	}

		public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
		public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return repo.findAll(pageRequest);
		}
}