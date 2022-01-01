package tayna.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tayna.domain.Post;
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
		var postagem = service.buscarPostagemPeloId(id);
		if(!postagem.getLikes().stream().anyMatch(like-> like.getUsuario().getNomeUsuario().equals(logado.getUsername()))) {
				service.curtir(id, logado);
		return ResponseEntity.status(HttpStatus.OK).body("Curtido");
		}
		else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Voce ja curtiu isso");
	}
	
	@PutMapping("/descurtir/{id}")
	public ResponseEntity<?> putDescurtirPostagem(@PathVariable Integer id, @AuthenticationPrincipal UserDetails logado){
		service.descurtir(id, logado);
		return ResponseEntity.status(HttpStatus.OK).body("Descurtido");
	}

}
