package tayna.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tayna.domain.Comentarios;
import tayna.domain.Post;
import tayna.dto.ComentarioDTO;
import tayna.services.ComentarioService;

@RestController
@RequestMapping(value="/comentarios")
public class ComentarioResource {
	
	@Autowired
	private ComentarioService service;
	Post post = new Post();

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Comentarios obj = service.find(id);
		return ResponseEntity.ok().body(obj);

}
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ComentarioDTO>> findAll(Integer id) {
		List<Comentarios> list = service.findAll();
		List<ComentarioDTO> listDto = list.stream().map(obj -> new ComentarioDTO(
				obj.getId(), obj.getConteudo(), obj.getPost().getId()) ).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listDto);
		// retornando nullo no id, arrumar
}
	
	/*@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ComentarioDTO objDTO, Comentarios obj){
		obj = service.fromDTO(objDTO, obj);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}*/
}
