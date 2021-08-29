package tayna.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tayna.domain.Usuario;
import tayna.services.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	/*public List<Usuario> listar(){
		
		Usuario us1 = new Usuario("tayna", 123);
		Usuario us2 = new Usuario("maria", 555);
		
		List<Usuario> lista = new ArrayList<>();
		lista.add(us1);
		lista.add(us2);

		return lista;
		
	}*/
	
	@Autowired
	private UsuarioService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Usuario obj = service.find(id);
		return ResponseEntity.ok().body(obj);

}
}
