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

		Usuario aux = usuarioRepository.findByNomeUsuario(objDto.getNomeUsuario());
		if (aux != null) {
			list.add(new FieldMessage("nomeUsuario", "nome de usuario já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
