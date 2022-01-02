package tayna.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tayna.domain.Likes;
import tayna.domain.Post;
import tayna.repositories.LikesRepository;
import tayna.repositories.PostRepository;
import tayna.repositories.UsuarioRepository;

@Service
public class LikesService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private LikesRepository likesRepository;
	
	public void curtir(Integer id, UserDetails logado) {
		 var postagem = buscarPostagemPeloId(id);
		 var usuario = usuarioRepository.findByNomeUsuario(logado.getUsername());
		 postagem.getLikes().add(new Likes(usuario, postagem));
		 postRepository.save(postagem);
	}
		  
	public void descurtir(Integer id, UserDetails logado) {
		 var postagem = buscarPostagemPeloId(id);
		 var usuario = usuarioRepository.findByNomeUsuario(logado.getUsername());
		 //ver pq n ta funcionando
		 List<Likes> likesPost = likesRepository.findAllByPost(postagem);
		 for (Likes likes : likesPost) {
				postagem.getLikes().remove(likes);
		}
		 postRepository.save(postagem);
	}
	
	public Post buscarPostagemPeloId(Integer id) {
		Post postagemSalva = postRepository.findById(id).orElse(null);
		if (postagemSalva == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postagem não encontrada!", null);
		}
		return postagemSalva;
	}

	public List<Likes> find(Integer id) {
		 var postagem = buscarPostagemPeloId(id);

		List<Likes> optionalLikes = likesRepository.findAllByPost(postagem);
		if(optionalLikes == null) {
			 throw new IllegalArgumentException("Objeto não encontrado! Id: " + id);
		}
		return optionalLikes;
	}

}
