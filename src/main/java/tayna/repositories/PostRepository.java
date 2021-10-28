package tayna.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tayna.domain.Post;
import tayna.domain.enun.TipoDePost;

public interface PostRepository extends JpaRepository<Post, Integer> {

	Post findByTipo(TipoDePost tipo);

}
