package tayna.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tayna.domain.Comentarios;
import tayna.repositories.ComentarioRepository;


@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository repo;

	public Comentarios find(Integer id) {
		Optional<Comentarios> obj = repo.findById(id);
		return obj.orElse(null);
	}
}
