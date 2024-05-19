package constructorast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

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
			boolean b = programa.bind(new Pila());
			System.out.println("\nBinding");
			System.out.println(b + " , bien vinculado");
			if (!b)
				System.out.println("Error, la vinculacion falla. Revise las declaraciones de variables.");
			else {
				try {
					System.out.println("\nTipado");
					programa.type();
					System.out.println("bien tipado");
					System.out.println("\n\nAST\n\n");
					System.out.println(programa);
					System.out.println("\n\nGeneracion de codigo\n\n");
					programa.setDelta(0);
					Files.write(Paths.get("main.wat"), (programa.generateCode(0)).getBytes());
				} catch (TypeException e) {
					e.printErrors();
					System.out.println("Error, el tipado falla.");
				}
			}
		} else {
			System.err.println("No se ha podido completar el analisis lexico-sintactico.");
			throw new Exception("Ejecucion abortada");
		}

	}
}