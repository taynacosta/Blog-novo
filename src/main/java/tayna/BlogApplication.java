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

	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);

	}
	@Override
	public void run(String... args) throws Exception {
		
		
	
	}
}
