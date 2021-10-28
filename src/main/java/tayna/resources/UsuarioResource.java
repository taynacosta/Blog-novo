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
import org.springframework.web.bind.annotation.PostMapping;
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
		//http://localhost:8080/usuarios?page=1 ver como está funcionando
	}
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll(Integer id) {
		List<Usuario> list = service.findAll();
		List <UsuarioDTO> listDto = list.stream().map(obj -> new UsuarioDTO(obj.getId(),obj.getNomeUsuario(), obj.getEmail(), obj.getPerfil() 
				)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listDto);
		//funcionando ok
}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioDTO usuarioDTO){
		usuarioDTO = service.insert(usuarioDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDTO.getId()).toUri();
		return ResponseEntity.created(uri).build();
		//{ "nomeUsuario": "sobre2","email":"joaozinho@gmail.com"} mensagem de erro funcionando
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> putUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
	    Usuario usuario = service.fromDTO(usuarioDTO);
	    usuario.setId(id);
	    service.update(usuario);
	    return ResponseEntity.noContent().build();
	    //funcionando ok
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		 service.delete(id);
		return ResponseEntity.noContent().build();
		//funcionando ok
	}
	
}
