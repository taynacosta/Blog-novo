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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tayna.domain.Post;
import tayna.dto.PostDTO;
import tayna.repositories.PostRepository;
import tayna.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {
	
	PostRepository repository;
	
	@Autowired
	private PostService service;

	@GetMapping("{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		var postDto= service.find(id);
		return ResponseEntity.ok().body(postDto);
		//funcionando ok porem so puxa o primeiro comentario
}
	
	@Transactional(readOnly = true)
	public Page<PostDTO> findAll(Pageable pageable){
		repository.findAll();
		Page<Post> result = repository.findAll(pageable);
		return result.map(x -> new PostDTO(x));
		//http://localhost:8080/posts?page=1 ver como funciona
	}
	@GetMapping
	public ResponseEntity<List<PostDTO>> findAll(Integer id) {
		List<Post> list = service.findAll();
		List <PostDTO> listDto = list.stream().map(obj -> new PostDTO(obj.getId(),obj.getLegenda(), obj.getComentarios(), obj.getTipo(),
				obj.getUsuario().getId() )).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listDto);
}
	
	@PostMapping
	public ResponseEntity<?> insert(@Valid @RequestBody PostDTO postDTO){
		var postDto = service.insert(postDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(postDto.getId()).toUri();
		return ResponseEntity.created(uri).build();
		//validacao do tipo de post
		/*{ "legenda": "sobre o ano novo", "tipo": "TEXTO", "usuarioId": 1 }*/
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> putPost(@PathVariable Integer id, @RequestBody PostDTO postDTO) {
		Post post = service.fromDTO(postDTO);
	    post.setId(id);
	    service.update(post);
	    return ResponseEntity.noContent().build();
	 // por mensagem de erro se tentar editar o tipo do texto
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		 service.delete(id);
		return ResponseEntity.noContent().build();
		//so apaga quando n tem post, arrumar a parte de cascata com comentarios
	}
	
}
