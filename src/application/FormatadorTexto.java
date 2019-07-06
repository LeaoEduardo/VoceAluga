package application;

import java.util.function.UnaryOperator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

public class FormatadorTexto {

	public TextFormatter<String> CpfPassaporte(int index, TextField campo) {

		campo.setText("");
		UnaryOperator<Change> filtro;

		if (index == 0) { // CPF selecionado
			campo.setPromptText("12345678900");
			filtro = change -> {
				String text = change.getText();

				if (text.matches("[0-9]*")) {
					int newLength = change.getControlNewText().length();
					if (newLength > 11)
						return null;
					return change;
				}
				return null;
			};
		} else { // Passaporte selecionado
			campo.setPromptText("AB123456");
			filtro = change -> {
				String text = change.getText();

				if (text.matches("[a-zA-Z]*[0-9]*")) {
					int newLength = change.getControlNewText().length();
					if (newLength > 8)
						return null;
					return change;
				}
				return null;
			};
		}
		TextFormatter<String> textFormatter = new TextFormatter<>(filtro);
		return textFormatter;
	}

	public TextFormatter<String> CNH() {

		UnaryOperator<Change> filtro = change -> {
			String text = change.getText();

			if (text.matches("[0-9]*")) {
				int newLength = change.getControlNewText().length();
				if (newLength > 11)
					return null;
				return change;
			}
			return null;
		};
		TextFormatter<String> textFormatter = new TextFormatter<>(filtro);
		return textFormatter;
	}

	public TextFormatter<String> Quilometragem() {

		UnaryOperator<Change> filtro = change -> {
			String text = change.getText();

			if (text.matches("[0-9]*")) {
				return change;
			}
			return null;
		};
		TextFormatter<String> textFormatter = new TextFormatter<>(filtro);
		return textFormatter;
	}
}