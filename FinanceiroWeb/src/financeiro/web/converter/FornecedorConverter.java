package financeiro.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import financeiro.fornecedor.Fornecedor;
import financeiro.fornecedor.FornecedorRN;

@FacesConverter(forClass = Fornecedor.class)
public class FornecedorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		if (value != null && value.trim().length() > 0) {

			Integer codigo = Integer.valueOf(value);

			try {

				FornecedorRN fornecedorRN = new FornecedorRN();
				return fornecedorRN.carregar(codigo);

			} catch (Exception e) {
				throw new ConverterException(
						"Não foi possível encontrar o fornecedor com o código "
								+ value + ".\n" + e.getMessage());
			}
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		// Fornecedor fornecedor = null;

		if (value != null) {
			Fornecedor fornecedor = (Fornecedor) value;
			return fornecedor.getCodigo().toString();
		}

		// return (fornecedor != null && fornecedor.getCodigo() != null ?
		// fornecedor
		// .getCodigo().toString() : "");

		return "";
	}
}