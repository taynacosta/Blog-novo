package tayna.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	ComentarioRepository repository;

	//metodo dando erro, arrumar depois do put
	@GetMapping
	public ResponseEntity<List<ComentarioDTO>> findAll(Integer id) {
		List<Comentario> list = service.findAll();
		List <ComentarioDTO> listDto = list.stream().map(obj -> new ComentarioDTO(obj.getId(), obj.getConteudo(),
				obj.getPost().getId(), obj.getUsuario(),obj.getUsuario().getNomeUsuario()/*, obj.getUsuario().getId()*/)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listDto);
}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> insert(@Valid @RequestBody ComentarioDTO comentarioDTO, @AuthenticationPrincipal UserDetails logado){
		if(comentarioDTO.getNomeUsuario().equals(logado.getUsername())) {
		var comentarioDto = service.insert(comentarioDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comentarioDto.getId()).toUri();
		return ResponseEntity.created(uri).build();
		}
		else {
			return ResponseEntity.badRequest().body("Usuario nao tem permissao fazer um comentario em outro perfil " );
		}
	}
	/*{"conteudo": "alterando comentario", "nomeUsuario" : "Maria", "usuarioId": 2, "postId": 1, "usuario": {
            "id": 2,
            "email": "maria@gmail.com",
            "nomeUsuario": "Maria",
            "senha": "$2a$10$Nw32LLLGkM5PhdbO7Km78O1zt60z00zwg3agFvXFPGTfYcKP0tnGu"
        }}*/
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("{id}")
	public ResponseEntity<Object> put(@PathVariable Integer id, @RequestBody ComentarioDTO comentarioDTO , @AuthenticationPrincipal UserDetails logado) {
		Optional<Comentario> comentarioId = repository.findById(id);
		var comentarioNovo = comentarioId.orElseThrow(() -> new IllegalArgumentException("Id do comentario invalido"));
		if(comentarioNovo.getUsuario().getNomeUsuario().equals(logado.getUsername())) {
	    Comentario comentario = service.fromDTO(comentarioDTO);
	    comentario.setId(id);
	    service.update(comentario);
	    return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.badRequest().body("Usuario nao tem permissao de editar um post que não é seu " );
		}
	    //{"conteudo": "alterando comentario","postId": 1}
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		 service.delete(id);
		return ResponseEntity.noContent().build();
		//ok
	}
}
