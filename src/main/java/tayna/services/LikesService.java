package tayna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import tayna.domain.Likes;
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
		var postagem = postRepository.findById(id).get();
		var usuario = usuarioRepository.findByNomeUsuario(logado.getUsername());
		postagem.getLikes().add(new Likes(usuario, postagem));
		postRepository.save(postagem);
	}

	public void descurtir(Integer id, UserDetails logado) {
		 var postagem = postRepository.findById(id).get();
		Likes likeDoUsuario =  likesRepository.findByPost(postagem).get();
		likesRepository.delete(likeDoUsuario);
		postRepository.save(postagem);
	}

	public List<Likes> find(Integer id) {
		var postagem = postRepository.findById(id).get();
		List<Likes> optionalLikes = likesRepository.findAllByPost(postagem);
		if (optionalLikes == null) {
			throw new IllegalArgumentException("Objeto não encontrado! Id: " + id);
		}
		return optionalLikes;
	}

}
