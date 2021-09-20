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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tayna.domain.Comentario;
import tayna.domain.Post;
import tayna.dto.ComentarioDTO;
import tayna.repositories.ComentarioRepository;
import tayna.services.ComentarioService;

@RestController
@RequestMapping(value="/comentarios")
public class ComentarioResource {
	
	@Autowired
	private ComentarioService service;
	
	Post post = new Post();
	
	ComentarioRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ComentarioDTO> findAll(Pageable pageable){
		repository.findAll();
		Page<Comentario> result = repository.findAll(pageable);
		return result.map(x -> new ComentarioDTO(x));
		//http://localhost:8080/comentarios?page=1
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ComentarioDTO>> findAll(Integer id) {
		List<Comentario> list = service.findAll();
		List <ComentarioDTO> listDto = list.stream().map(obj -> new ComentarioDTO(obj.getId(), obj.getConteudo(),
				obj.getPost().getId())).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listDto);
}
	
	@PostMapping
	public ResponseEntity<?> insert(@Valid @RequestBody ComentarioDTO comentarioDTO){
		var comentarioDto = service.insert(comentarioDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comentarioDto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
