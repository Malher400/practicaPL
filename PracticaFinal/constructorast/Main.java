package constructorast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoTiny;
import ast.Pila;
import ast.Program;

public class Main {
	public static void main(String[] args) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(args[0]));
		AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
		ConstructorASTExp constructorast = new ConstructorASTExp(alex);
		Program programa = (Program) constructorast.parse().value;

		if (!programa.bind(new Pila()))
			System.out.println("Error, la vinculacion falla. Revise las declaraciones de variables");
		else{
			//if (!programa.type()) System.out.println("Error, el tipado falla.");
			//else{
				try {
					System.out.println(constructorast.parse().value);
				} catch (Exception e) {
					System.out.println("Something went wrong with the parsing...");
				}
			//}
		}
	}
}
