package tayna.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import tayna.domain.Comentario;
import tayna.dto.ComentarioDTO;
import tayna.repositories.ComentarioRepository;
import tayna.repositories.PostRepository;
import tayna.services.exceptions.ObjectNotFoundException;


@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private PostRepository postRepository;

	public Page<Comentario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return comentarioRepository.findAll(pageRequest);
		}

	public List<Comentario> findAll() {
		return comentarioRepository.findAll();
	}
	
	public Comentario find(Integer id) {
		Optional<Comentario> obj = comentarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Comentario.class.getName()));
	}

	public Comentario fromDTO(ComentarioDTO comentarioDTO) {
		Comentario comentario = new Comentario();
		return new Comentario(null, comentarioDTO.getConteudo(), comentario.getPost(), comentario.getUsuario());
	}

	public ComentarioDTO insert(ComentarioDTO comentarioDto) {
		var postOptional = postRepository.findById(comentarioDto.getPostId());
		var post = postOptional.orElseThrow(()->new IllegalArgumentException("Post invalido"));
		var comentario = comentarioDto.to(post);
		comentarioRepository.save(comentario);
		return ComentarioDTO.from(comentario);
	}

	public Comentario update(Comentario comentario) {
		Comentario novoComentario = find(comentario.getId());
		comentario.setId(novoComentario.getId());
		novoComentario.setConteudo(comentario.getConteudo());
		comentario.setPost(comentario.getPost());
		comentario.setUsuario(comentario.getUsuario());
		return comentarioRepository.save(novoComentario);
	}

	public void delete(Integer id) {
		find(id);
		comentarioRepository.deleteById(id);
	}
}
