package tayna.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	

	public PostDTO findDTO(Integer id) {
		var postOptional = postRepository.findById(id);
		var post = postOptional.orElseThrow(() -> new IllegalArgumentException("O post nao foi encontrado"));
		return PostDTO.from(post);
	}

	public Post find(Integer id) {
		Optional<Post> obj = postRepository.findById(id);
		return obj.orElseThrow(() -> new IllegalArgumentException("Objeto não encontrado! Id: " + id));
	}

	public PostDTO insert(PostDTO postDto) {
		var usuarioOptional = usuarioRepository.findById(postDto.getUsuarioId());
		//verificar usuario autenticado aqui
		var usuario = usuarioOptional.orElseThrow(() -> new IllegalArgumentException("Usuario invalido"));
		var post = postDto.to(usuario);
		postRepository.save(post);
		return PostDTO.from(post);
	}

	public void delete(Integer id) {
		findDTO(id);
		postRepository.deleteById(id);
	}

	/*public Page<Post> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return postRepository.findAll(pageRequest);
	}*/

	public Post fromDTO(PostDTO postDTO) {
		Post post = new Post();
		var usuarioOptional = usuarioRepository.findById(postDTO.getUsuarioId());
		var usuario = usuarioOptional.orElseThrow(() -> new IllegalArgumentException("Usuario invalido"));

		return new Post(postDTO.getId(), postDTO.getLegenda(), postDTO.getTipo(), usuario);
	}

	public void updateDate(Post post, Post novoPost) {
		post.setLegenda(novoPost.getLegenda());
		post.setTipo(post.getTipo());
		post.setUsuario(post.getUsuario());
	}

	public Post update(Post post) {
		Post novoPost = find(post.getId());
		updateDate(novoPost, post);
		return postRepository.save(novoPost);
	}

	/*public List<Post> findAll() {
		return postRepository.findAll();
	}*/ // arrumar isso depois
}
