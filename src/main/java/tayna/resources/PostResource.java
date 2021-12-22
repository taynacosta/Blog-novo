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

import tayna.domain.Post;
import tayna.dto.PostDTO;
import tayna.repositories.PostRepository;
import tayna.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {
	
	@Autowired
	PostRepository repository;
	
	@Autowired
	private PostService service;

	@GetMapping("{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		var postDto= service.find(id);
		return ResponseEntity.ok().body(postDto);
	}
	
	@GetMapping
	public ResponseEntity<Page<Post>> list(Pageable pageable){
		return ResponseEntity.ok(repository.findAll(pageable));
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> insert(@Valid @RequestBody PostDTO postDTO, @AuthenticationPrincipal UserDetails logado){
		//logado = postDTO.getUsuario();
		//if(logado.equals(postDTO.getUsuarioId())) {
		//logado = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(postDTO.getNomeUsuario().equals(logado.getUsername())) {
		var postDto = service.insert(postDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(postDto.getId()).toUri();
		return ResponseEntity.created(uri).build();
		}
		else {
			return ResponseEntity.badRequest().body("Usuario nao tem permissao de postar em outro perfil " + "postDTO.getNomeUsuario() " + postDTO.getNomeUsuario() + " (logado).getUsername() " + (logado).getUsername() );
		}
		//validacao do tipo de post
		/*{ "legenda": "sobre o ano novo", "tipo": "TEXTO", "usuarioId" : 1 }*/
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("{id}")
	public ResponseEntity<Object> putPost(@PathVariable Integer id, @RequestBody PostDTO postDTO) {
		Post post = service.fromDTO(postDTO);
	    post.setId(id);
	    service.update(post);
	    return ResponseEntity.noContent().build();
	 // por mensagem de erro se tentar editar o tipo do texto
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		 service.delete(id);
		return ResponseEntity.noContent().build();
		//so apaga quando n tem post, arrumar a parte de cascata com comentarios
	}
	
}
