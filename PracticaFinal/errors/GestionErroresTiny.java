package errors;

import alex.UnidadLexica;

public class GestionErroresTiny {
	public void errorLexico(int fila, int columna, String lexema) {
		System.err.println("FILA "+fila+", COLUMNA "+columna+": Caracter inesperado \""+lexema+"\"."); 
	}  
	public void errorSintactico(UnidadLexica unidadLexica) {
		if (unidadLexica.lexema() != null) {
			System.err.println("FILA "+unidadLexica.fila()+", COLUMNA "+unidadLexica.columna()+": Elemento inesperado \""+unidadLexica.lexema()+"\".");
		} else {
			System.err.println("FILA "+unidadLexica.fila()+", COLUMNA "+unidadLexica.columna()+": Elemento inesperado.");
		}
   }
}
