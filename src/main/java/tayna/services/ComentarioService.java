package tayna.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import tayna.domain.Comentarios;
import tayna.dto.ComentarioDTO;
import tayna.repositories.ComentarioRepository;
import tayna.services.exceptions.ObjectNotFoundException;


@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository repo;

	public Page<Comentarios> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return repo.findAll(pageRequest);
		}

	public List<Comentarios> findAll() {
		return repo.findAll();
	}
	
	public Comentarios find(Integer id) {
		Optional<Comentarios> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Comentarios.class.getName()));
	}

	public Comentarios fromDTO(@Valid ComentarioDTO comentarioDTO, Comentarios comentario) {
		return new Comentarios(null, comentarioDTO.getConteudo(), comentarioDTO.getPostId());
	}

}
