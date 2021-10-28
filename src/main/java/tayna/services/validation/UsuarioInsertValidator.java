package tayna.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import tayna.domain.Usuario;
import tayna.dto.UsuarioDTO;
import tayna.repositories.UsuarioRepository;
import tayna.resources.exception.FieldMessage;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioDTO> {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public void initialize(UsuarioInsert ann) {
	}

	@Override
	public boolean isValid(UsuarioDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		Usuario usuarioFindNome = usuarioRepository.findByNomeUsuario(objDto.getNomeUsuario());
		if (usuarioFindNome != null) {
			list.add(new FieldMessage("nomeUsuario", "nome de usuario já existente!"));
		}
		Usuario usuarioFindEmail = usuarioRepository.findByEmail(objDto.getEmail());
		if (usuarioFindEmail != null) {
			list.add(new FieldMessage("email", "Email já foi cadastrado anteriormente!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
