package constructorast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoTiny;
import ast.Pila;
import ast.Program;
import errors.*;

public class Main {
	public static void main(String[] args) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(args[0]));
		AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
		ConstructorASTExp constructorast = new ConstructorASTExp(alex);
		Program programa = (Program) constructorast.parse().value;

		if (programa != null) {
			if (!programa.bind(new Pila()))
				System.out.println("Error, la vinculacion falla. Revise las declaraciones de variables.");
			else {
				try {
					System.out.println(constructorast.parse().value);
					programa.type();
				} catch (Exception e) {
					System.out.println("Error, el tipado falla.");
				}
			}
		} else {
			System.err.println("No se ha podido completar el analisis lexico-sintactico.");
			throw new Exception("Ejecucion abortada");
		}

	}
}
