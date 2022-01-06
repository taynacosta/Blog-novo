package tayna.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tayna.domain.Likes;
import tayna.domain.Post;
import tayna.domain.Usuario;
import tayna.repositories.LikesRepository;
import tayna.repositories.PostRepository;
import tayna.repositories.UsuarioRepository;
import tayna.services.LikesService;

@RestController
@RequestMapping(value="/likes")
public class LikesResource {
	
	@Autowired
	LikesRepository likesRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	LikesService service;

	@PutMapping("/curtir/{id}")
	public ResponseEntity<?> putCurtirPostagem(@PathVariable Integer id, @AuthenticationPrincipal UserDetails logado) {
		var postagem = postRepository.findById(id).get();
		if(!postagem.getLikes().stream().anyMatch(like-> like.getUsuario().getNomeUsuario().equals(logado.getUsername()))) {
				service.curtir(id, logado);
		return ResponseEntity.status(HttpStatus.OK).body("Curtido");
		}
		else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Voce ja curtiu isso");
	}
	
	@PutMapping("/descurtir/{id}")
	public ResponseEntity<?> putDescurtirPostagem(@PathVariable Integer id, @AuthenticationPrincipal UserDetails logado){
		try {
			service.descurtir(id, logado);
			return ResponseEntity.status(HttpStatus.OK).body("Descurtido");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Voce nao curtiu esse post");
		}
		
	}
	
	@GetMapping("posts/{id}")
	public ResponseEntity<?> curtidasDoPost(@PathVariable Integer id, @AuthenticationPrincipal UserDetails logado){
		var curtidas = service.find(id);
		Post post = new Post();
		post.defineQtdLikes();
		return ResponseEntity.ok().body(curtidas);
	}

}
