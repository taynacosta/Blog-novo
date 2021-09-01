package tayna.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tayna.domain.Usuario;
import tayna.dto.UsuarioDTO;
import tayna.services.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	
	@Autowired
	private UsuarioService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> findAll(Integer id) {
		List<Usuario> list = service.findAll();
		List<UsuarioDTO> listDto = list.stream().map(obj -> new UsuarioDTO(
				obj.getId(), obj.getNomeUsuario(), obj.getSenha(), obj.getEmail())).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Usuario obj = service.find(id);
		return ResponseEntity.ok().body(obj);
}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioDTO objDTO){
		Usuario obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> putUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
	    Usuario usuario = new Usuario();
	    usuario.setId(id);
	    usuario.setNomeUsuario(usuarioDTO.getNomeUsuario());
	    usuario.setEmail(usuarioDTO.getEmail());
	    usuario.setSenha(usuarioDTO.getSenha());
	    service.save(usuario);
	    return ResponseEntity.noContent().build();
	}

}
