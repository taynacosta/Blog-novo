package tayna.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import tayna.domain.Post;
import tayna.domain.enun.TipoDePost;

public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {

	Post findByTipo(TipoDePost tipo);
	
	Page<Post> findAll(Pageable pageable);
	
}
