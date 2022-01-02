package tayna.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import tayna.domain.Likes;
import tayna.domain.Post;

public interface LikesRepository extends PagingAndSortingRepository<Likes, Long> {

	Optional<Likes> findByPost(Post postagem);

	List<Likes> findAllByPost(Post postagem);

}
