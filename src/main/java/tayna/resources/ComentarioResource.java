package tayna.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tayna.domain.Comentarios;
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
		Page<Comentarios> result = repository.findAll(pageable);
		return result.map(x -> new ComentarioDTO(x));
		//http://localhost:8080/comentarios?page=1
	}

	//fazer o post e comentario aqui

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ComentarioDTO>> findAll(Integer id) {
		List<Comentarios> list = service.findAll();
		List <ComentarioDTO> listDto = list.stream().map(obj -> new ComentarioDTO(obj.getId(), obj.getConteudo(),
				obj.getPost().getId())).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listDto);
		
}
}
