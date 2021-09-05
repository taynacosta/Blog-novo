package tayna.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ComentarioDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="nomeUsuario", defaultValue="id") String nomeUsuario, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Comentarios> list = service.findPage(page, linesPerPage, nomeUsuario, direction);
		Page<ComentarioDTO> listDto = list.map(obj -> new ComentarioDTO(obj.getId(), obj.getConteudo(), obj.getPost().getId()));  
		return ResponseEntity.ok().body(listDto);
		//http://localhost:8080/comentarios/page?linesPerPage=3&page=0&direction=DESC
	}

	//fazer o post comentario aqui

}
