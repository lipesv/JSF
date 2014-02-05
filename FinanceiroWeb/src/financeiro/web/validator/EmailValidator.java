package financeiro.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

	private static final String EMAIL_PATTERN = "[a-z0-9!#$%&amp;'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&amp;'*+/=?^_`{|}~-]+)*"
			+ "@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+"
			+ "(?:[A-Z]{2}|com|br|org|net|edu|gov|mil|biz|info|mobi|name|aero|asia|jobs|museum)\\b";

	private Pattern pattern;
	private Matcher matcher;

	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		matcher = pattern.matcher(value.toString());

		if (!matcher.matches()) {

			FacesMessage msg = new FacesMessage("E-mail inv�lido.",
					"O e-mail digitado n�o est� em um formato v�lido.");

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);

		}

	}
}
