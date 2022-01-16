package tayna.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tayna.domain.Usuario;
import tayna.dto.UsuarioDTO;
import tayna.repositories.PerfilRepository;
import tayna.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	/*public UsuarioService(UsuarioResource resource, UsuarioRepository repository) {
		this.usuarioResource = resource;
		this.usuarioRepository = repository;
	}*/

	public Usuario find(Integer id) {
		Usuario obj = usuarioRepository.findById(id).get();
		//return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
		return obj;
	}

	public Usuario fromDTO(@Valid UsuarioDTO objDTO) {
		return new Usuario(objDTO.getId(), objDTO.getNomeUsuario(), objDTO.getSenha(), objDTO.getEmail(), objDTO.getPerfil());
	}

	public UsuarioDTO insert(UsuarioDTO usuarioDTO) {
		var usuario = usuarioDTO.to();
		perfilRepository.save(usuario.getPerfil());
		var usuarioSalvo = usuarioRepository.save(usuario);
		return UsuarioDTO.from(usuarioSalvo);
	}

	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public Usuario update(Usuario usuario) {
		Usuario usuarioAntigo = find(usuario.getId());
		usuarioAntigo.setId(usuario.getId());
		usuario.setNomeUsuario(usuarioAntigo.getNomeUsuario());
		usuarioAntigo.setEmail(usuario.getEmail());
		usuarioAntigo.setSenha(pe.encode(usuario.getSenha()));
		perfilRepository.save(usuario.getPerfil());
		return usuarioRepository.save(usuario);
	}

	public void delete(Integer id) {
		find(id);
		usuarioRepository.deleteById(id);
	}

	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return usuarioRepository.findAll(pageRequest);
	}

	public Page<Usuario> encontrarPorPagina(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

}