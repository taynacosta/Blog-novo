package tayna.services;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import tayna.domain.Perfil;
import tayna.domain.Usuario;
import tayna.domain.enun.Genero;
import tayna.domain.enun.StatusCivil;
import tayna.dto.UsuarioDTO;
import tayna.repositories.UsuarioRepository;
import tayna.resources.UsuarioResource;

class UsuarioServiceTest {

	@Mock
	UsuarioService service;
	
	@Mock
	UsuarioResource resource;
	
	@Mock
	UsuarioRepository repository;
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	
	Date date;
	
	Perfil pf1;
	
	UsuarioDTO us1;
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		@BeforeEach
		private void start() throws ParseException{
			
		pf1 = new Perfil(StatusCivil.NAMORANDO, Genero.FEMININO, sdf.parse("18-11-1997"), "resumo sobre o meu perfil", 10.0, 10.0,10.0, "Tayna", "Moreira Costa");
		us1 = new UsuarioDTO(1, "Tayna", 123, "tayna@gmail.com", pf1);
		
		MockitoAnnotations.openMocks(this);
		this.service = new UsuarioService(resource, repository);
	}
		
		@Test
		@Order(1)
		public void deveriaCadastrarUsuario() {
			service.insert(us1);
			Usuario usuario = us1.to();
			repository.save(usuario);
			UsuarioDTO ehverdade = new UsuarioDTO(1, "Tayna", 123, "tayna@gmail.com", pf1);
			
			assertEquals(ehverdade, us1);
			
		}
		
		@Test
		@Order(2)
		public void deveriaAtualizarUsuario() {
			Usuario novoUsuario = new Usuario(1, "Taynx", 123564, "taynx@gmail.com", pf1);
			//Mockito.when(repository.findById(id)).thenReturn(novoUsuario);
			Mockito.when(service.update(novoUsuario)).thenReturn(service.update(novoUsuario));
			novoUsuario.setId(us1.getId());

			service.update(novoUsuario);
			
			assertEquals(novoUsuario, us1);
		}
}
