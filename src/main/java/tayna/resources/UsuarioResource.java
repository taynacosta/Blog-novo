package tayna.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
import tayna.services.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public ResponseEntity<Page<Usuario>> list(Pageable pageable){
		Page<Usuario> usuario = service.encontrarPorPagina(pageable);
		return ResponseEntity.ok(usuario);
	}//http://localhost:8083/usuarios?page=0&size=2
	
	@GetMapping("{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		var usuarioDto= service.find(id);
		return ResponseEntity.ok().body(usuarioDto);
	}//http://localhost:8083/usuarios/1
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioDTO usuarioDTO){
		var usuarioDto = service.insert(usuarioDTO);
		//service.insert(usuarioDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDto.getId()).toUri();
		return ResponseEntity.created(uri).build();
		/*{ "nomeUsuario": "Tayna", "senha" :"123dgfdg", "email": "taynazinha@gmail.com.br", "perfil": {
	        "statusCivil": "NAMORANDO", "genero": "FEMININO", "idade": "35", "resumo": "resumlkjljiljo sobre o meu perfil",  "legal": 9.0,   "confiavel": 10.0,     "sexy": 10.0
	    }}*/
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("{id}")
	public ResponseEntity<Object> putUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO, @AuthenticationPrincipal UserDetails logado) {
		Usuario usuarioLogado = service.find(id);
		if(usuarioLogado.getNomeUsuario().equals(logado.getUsername())) {
		    Usuario usuario = service.fromDTO(usuarioDTO);
		    usuario.setId(id);
		    service.update(usuario);
		    return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.badRequest().body("Usuario só pode editar o seu perfil" );
		}
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		 service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
