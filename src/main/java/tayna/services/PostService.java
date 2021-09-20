package tayna.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import tayna.domain.Post;
import tayna.dto.PostDTO;
import tayna.repositories.PostRepository;
import tayna.repositories.UsuarioRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	public PostDTO find(Integer id) {
		var postOptional = postRepository.findById(id);
		var post = postOptional.orElseThrow(() -> new IllegalArgumentException("O post nao foi encontrado"));
		return PostDTO.from(post);
	}

	public PostDTO insert(PostDTO postDto) {
		var usuarioOptional = usuarioRepository.findById(postDto.getUsuarioId());
		var usuario = usuarioOptional.orElseThrow(() -> new IllegalArgumentException("Usuario invalido"));
		var post = postDto.to(usuario);
		postRepository.save(post);
		return PostDTO.from(post);
	}

	public void delete(Integer id) {
		find(id);
		postRepository.deleteById(id);
	}

	public Page<Post> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return postRepository.findAll(pageRequest);
	}
}
