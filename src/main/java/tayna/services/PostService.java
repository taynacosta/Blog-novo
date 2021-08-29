package tayna.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tayna.domain.Post;
import tayna.repositories.PostRepository;


@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public Post find(Integer id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElse(null);
	}
}
