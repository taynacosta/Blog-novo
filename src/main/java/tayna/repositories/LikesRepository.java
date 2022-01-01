package tayna.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import tayna.domain.Likes;
import tayna.domain.Post;

public interface LikesRepository extends PagingAndSortingRepository<Likes, Long> {

	List<Likes> findAllByPost(Post postagem);

}
