package tayna.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<PostDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="legenda") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Post> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<PostDTO> listDto = list.map(obj -> new PostDTO(
				obj.getId(), obj.getLegenda(), obj.getComentarios(), obj.getTipo(), obj.getUsuario().getId())); 
		// TODO DESAFIO
		return ResponseEntity.ok().body(listDto);
		//http://localhost:8080/posts/page?linesPerPage=3&page=0&direction=DESC
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
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		 service.delete(id);
		return ResponseEntity.noContent().build();
		//so apaga quando n tem post, arrumar a parte de cascata com comentarios
	}
	
}
