package tayna.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tayna.domain.Usuario;
import tayna.dto.UsuarioDTO;
import tayna.repositories.UsuarioRepository;
import tayna.services.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public ResponseEntity<Page<Usuario>> list(Pageable pageable){
		return ResponseEntity.ok(usuarioRepository.findAll(pageable));
	}//http://localhost:8083/usuarios?page=0&size=2
	
	@GetMapping("{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		var usuarioDto= service.find(id);
		return ResponseEntity.ok().body(usuarioDto);
	}//http://localhost:8083/usuarios/1
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioDTO usuarioDTO){
		usuarioDTO = service.insert(usuarioDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDTO.getId()).toUri();
		return ResponseEntity.created(uri).build();
		//{ "nomeUsuario": "sobre2","email" :"joaozinho@gmail.com"} mensagem de erro funcionando
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("{id}")
	public ResponseEntity<Object> putUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
	    Usuario usuario = service.fromDTO(usuarioDTO);
	    usuario.setId(id);
	    service.update(usuario);
	    return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		 service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
