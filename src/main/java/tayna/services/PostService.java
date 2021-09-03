package tayna.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tayna.domain.Post;
import tayna.dto.PostDTO;
import tayna.repositories.PostRepository;


@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public Post find(Integer id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElse(null);
	}

	public List<Post> findAll() {
		return repo.findAll();
	}
	
	@Transactional
	public Post insert(Post obj) {
		obj.setId(null);
		if(obj.isNullOrNotEmpty(obj.getLegenda())) {
			throw new IllegalArgumentException ("faltou a legenda");
		}
		// validacao de tipo de post e usuario aqui
		obj = repo.save(obj);
		return obj;	
		
	}

	public Post fromDTO(@Valid PostDTO objDTO , Post obj) {
			return new Post(null, objDTO.getLegenda(), obj.getTipo(), null);
	}

	public Post save(Post post) {
		return repo.save(post);
		
	}

	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}

}
