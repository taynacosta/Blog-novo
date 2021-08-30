package tayna;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tayna.domain.Comentarios;
import tayna.domain.Post;
import tayna.domain.Usuario;
import tayna.domain.enun.TipoDePost;
import tayna.repositories.ComentarioRepository;
import tayna.repositories.PostRepository;
import tayna.repositories.UsuarioRepository;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);

	}
	@Override
	public void run(String... args) throws Exception {
	
		//post id 
		Usuario us1 = new Usuario(null, "tayna", 123);
		Usuario us2 = new Usuario(null, "maria", 555);
	
		Post post1 = new Post(null, "linda casa", TipoDePost.FOTO, us1, null);
		Post post2 = new Post(null, "lidgds", TipoDePost.FOTO, us2, null);
		
		us1.getPost(Arrays.asList(post1));
		us2.getPost(Arrays.asList(post2));
		
		usuarioRepository.saveAll(Arrays.asList(us1, us2));
	
		Comentarios com1 = new Comentarios(null, "teste", post1);
		post1.getComentarios((Arrays.asList(com1)));
		com1.getPost(post1);
		
		
		postRepository.saveAll((Arrays.asList(post1, post2)));
		comentarioRepository.save(com1);
		
		
	}
}
