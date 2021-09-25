package tayna.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import tayna.domain.Usuario;
import tayna.dto.ComentarioDTO;
import tayna.dto.UsuarioDTO;
import tayna.repositories.UsuarioRepository;
import tayna.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario find(Integer id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public Usuario fromDTO(@Valid UsuarioDTO objDTO) {
		return new Usuario(objDTO.getId(), objDTO.getNomeUsuario(), objDTO.getSenha(), objDTO.getEmail());
	}

	public UsuarioDTO insert(UsuarioDTO usuarioDTO) {
		//usuarioDTO.setId(null);
		//Usuario usuario = new Usuario();
		Usuario usuario = usuarioDTO.to();
		usuarioRepository.save(usuario);
		return UsuarioDTO.from(usuario);
	}

	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	private void updateDate(Usuario usuario, Usuario novoUsuario) {
		usuario.setId(novoUsuario.getId());
		usuario.setNomeUsuario(novoUsuario.getNomeUsuario());
		usuario.setEmail(novoUsuario.getEmail());
		usuario.setSenha(novoUsuario.getSenha());
	}

	public Usuario update(Usuario usuario) {
		Usuario novoUsuario = find(usuario.getId());
		updateDate(novoUsuario, usuario);
		return usuarioRepository.save(novoUsuario);
	}

	public void delete(Integer id) {
		find(id);
		usuarioRepository.deleteById(id);
	}

	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return usuarioRepository.findAll(pageRequest);
	}

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
}