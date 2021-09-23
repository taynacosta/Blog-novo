package tayna.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tayna.domain.Usuario;
import tayna.dto.UsuarioDTO;
import tayna.repositories.UsuarioRepository;
import tayna.services.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService service;
	
	@Transactional(readOnly = true)
	public Page<UsuarioDTO> findAll(Pageable pageable){
		usuarioRepository.findAll();
		Page<Usuario> result = usuarioRepository.findAll(pageable);
		return result.map(x -> new UsuarioDTO(x));
		//http://localhost:8080/usuarios?page=1
	}
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll(Integer id) {
		List<Usuario> list = service.findAll();
		List <UsuarioDTO> listDto = list.stream().map(obj -> new UsuarioDTO(obj.getId(),obj.getNomeUsuario(), obj.getSenha(), obj.getEmail() 
				)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listDto);
}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioDTO objDTO){
		Usuario obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> putUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
	    Usuario usuario = service.fromDTO(usuarioDTO);
	    usuario.setId(id);
	    service.update(usuario);
	    return ResponseEntity.noContent().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		 service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
