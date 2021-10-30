package tayna;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tayna.domain.Comentario;
import tayna.domain.Perfil;
import tayna.domain.Post;
import tayna.domain.Usuario;
import tayna.domain.enun.Genero;
import tayna.domain.enun.StatusCivil;
import tayna.domain.enun.TipoDePost;
import tayna.repositories.ComentarioRepository;
import tayna.repositories.PerfilRepository;
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
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);

	}
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		Perfil pf1 = new Perfil(StatusCivil.NAMORANDO, Genero.FEMININO, sdf.parse("18-11-1997"), "resumo sobre o meu perfil", 10.0, 10.0,10.0);
		Perfil pf2 = new Perfil(StatusCivil.SOLTEIRO, Genero.FEMININO, sdf.parse("20-07-2004"), "resumo sobre o meu perfil", 10.0, 10.0,10.0);
		Perfil pf3 = new Perfil(StatusCivil.CASADO, Genero.GENERO_NEUTRO, sdf.parse("20-09-2000"), "resumo sobre o meu perfil", 10.0, 10.0,10.0);
		Perfil pf4 = new Perfil(StatusCivil.SOLTEIRO, Genero.FEMININO, sdf.parse("07-12-1993"), "resumo sobre o meu perfil", 10.0, 10.0,10.0);
		Perfil pf5 = new Perfil(StatusCivil.CASADO, Genero.FEMININO, sdf.parse("01-08-1994"), "resumo sobre o meu perfil", 10.0, 10.0,10.0);
		
		perfilRepository.saveAll(Arrays.asList(pf1, pf2, pf3, pf4, pf5));
		
		Usuario us1 = new Usuario(null, "Tayna", 123, "tayna@gmail.com", pf1);
		Usuario us2 = new Usuario(null, "Maria", 555, "maria@gmail.com", pf2);
		Usuario us3 = new Usuario(null, "Jose", 555, "jose@gmail.com", pf3);
		Usuario us4 = new Usuario(null, "Mariana", 555, "mariana@gmail.com", pf4);
		Usuario us5 = new Usuario(null, "Joao", 555, "joao@gmail.com", pf5);
		
		usuarioRepository.saveAll(Arrays.asList(us1, us2, us3, us4, us5));
		
		Post post1 = new Post(null, "linda casa", TipoDePost.FOTO, us1);
		Post post2 = new Post(null, "viagem no final de semana", TipoDePost.FOTO, us2);
		Post post3 = new Post(null, "eu e minha avó", TipoDePost.FOTO, us3);
		Post post4 = new Post(null, "sobre o ano novo", TipoDePost.TEXTO, us1);
		Post post5 = new Post(null, "atualizando ", TipoDePost.VIDEO, us2);
	
		postRepository.saveAll((Arrays.asList(post1, post2, post3, post4, post5)));
		
		Comentario com1 = new Comentario(null, "teste", null, us1);
		Comentario com2 = new Comentario(null, "teste", null, us2);
		Comentario com3 = new Comentario(null, "teste", null, us3);
		Comentario com4 = new Comentario(null, "teste", null, us4);
		Comentario com5 = new Comentario(null, "teste", null, us1);
		Comentario com6 = new Comentario(null, "teste", null, us1);
		Comentario com7 = new Comentario(null, "teste", null, us1);
		Comentario com8 = new Comentario(null, "teste", null, us1);
		
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
