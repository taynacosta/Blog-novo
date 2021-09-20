package tayna;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tayna.domain.Comentario;
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

		Usuario us1 = new Usuario(null, "Tayna", 123, "tayna@gmail.com");
		Usuario us2 = new Usuario(null, "Maria", 555, "maria@gmail.com");
		Usuario us3 = new Usuario(null, "Jose", 555, "jose@gmail.com");
		Usuario us4 = new Usuario(null, "Mariana", 555, "mariana@gmail.com");
		Usuario us5 = new Usuario(null, "Joao", 555, "joao@gmail.com");
		
		usuarioRepository.saveAll(Arrays.asList(us1, us2, us3, us4, us5));
		
		Post post1 = new Post(null, "linda casa", TipoDePost.FOTO, us1);
		Post post2 = new Post(null, "viagem no final de semana", TipoDePost.FOTO, us2);
		Post post3 = new Post(null, "eu e minha avó", TipoDePost.FOTO, us3);
		Post post4 = new Post(null, "sobre o ano novo", TipoDePost.TEXTO, us1);
		Post post5 = new Post(null, "atualizando ", TipoDePost.VIDEO, us2);
	
		postRepository.saveAll((Arrays.asList(post1, post2, post3, post4, post5)));
		
		Comentario com1 = new Comentario(null, "teste", null);
		Comentario com2 = new Comentario(null, "teste", null);
		Comentario com3 = new Comentario(null, "teste", null);
		Comentario com4 = new Comentario(null, "teste", null);
		Comentario com5 = new Comentario(null, "teste", null);
		Comentario com6 = new Comentario(null, "teste", null);
		Comentario com7 = new Comentario(null, "teste", null);
		Comentario com8 = new Comentario(null, "teste", null);
		
		com1.setPost(post1);
		com2.setPost(post2);
		com3.setPost(post2);
		com4.setPost(post1);
		com5.setPost(post3);
		com6.setPost(post3);
		com7.setPost(post3);
		com8.setPost(post3);
		comentarioRepository.saveAll((Arrays.asList(com1, com2, com3, com4, com5, com6, com7, com8)));
	
	}
}
