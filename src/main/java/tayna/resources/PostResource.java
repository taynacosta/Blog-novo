package tayna.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tayna.domain.Post;
import tayna.dto.PostDTO;
import tayna.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {
	
	@Autowired
	private PostService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Post obj = service.find(id);
		PostDTO objVolta = new PostDTO(
				obj.getId(), obj.getLegenda(), obj.getComentarios(null));
		return ResponseEntity.ok().body(objVolta);
}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PostDTO>> findAll(Integer id) {
		List<Post> list = service.findAll();
		List<PostDTO> listDto = list.stream().map(obj -> new PostDTO(
				obj.getId(), obj.getLegenda(), obj.getComentarios(null))).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listDto);
}
}
