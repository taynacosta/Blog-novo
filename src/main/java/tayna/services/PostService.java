package tayna.services;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tayna.domain.Post;
import tayna.domain.Usuario;
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
		Post post = postRepository.findById(id).get();
		post.defineQtdLikes();
		return post;
	}

	public PostDTO insert(PostDTO postDto) {
		var usuarioOptional = usuarioRepository.findById(postDto.getUsuarioId());
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
		var usuarioOptional = usuarioRepository.findById(postDTO.getUsuarioId());
		var usuario = usuarioOptional.orElseThrow(() -> new IllegalArgumentException("Usuario invalido"));

		return new Post(postDTO.getId(), postDTO.getLegenda(), postDTO.getTipo(), usuario.getId());
	}

	public Post update(Post post) {
		Post postAntigo = find(post.getId());
		postAntigo.setLegenda(post.getLegenda());
		postAntigo.setTipo(postAntigo.getTipo());
		return postRepository.save(postAntigo);
	}

	public Stream<Usuario> achaUsuarioPorId(Post postagem, Usuario usuario) {
		Stream<Usuario> stream = Stream.of(usuario);
		
		stream.filter((s) -> s.getId().equals(postagem.getIdUsuario()));
		return stream;
	}
}
