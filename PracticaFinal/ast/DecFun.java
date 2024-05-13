package ast;

import java.util.ArrayList;
import errors.TypeException;

public class DecFun extends Dec {
	private Type returnType;
	private ArrayList<Dec> args;
	private Ins bloque;
	private Exp e;

	public DecFun(int fila, int columna, String id, Type tipo, Type returnType, ArrayList<Dec> args, Ins bloque,
			Exp e) {
		super(fila, columna, tipo, id);
		this.tipoDec = KindDec.FUNCION;
		this.returnType = returnType;
		this.args = args;
		this.bloque = bloque;
		this.e = e;
	}

	public String toString() {
		StringBuilder str = new StringBuilder("fun ");
		str.append(returnType.toString());
		str.append(' ');
		str.append(id);
		str.append('(');
		for (Dec dec : args) {
			str.append(dec.toString());
			str.append(", ");
		}
		str.delete(str.length() - 2, str.length());
		str.append(") ");
		str.append("{\n");
		str.append(bloque.toString());
		str.append("  return ");
		str.append(e.toString());
		str.append(";\n");
		str.append("\n}");
		return str.toString();
	}

	public boolean bind(Pila pila) {
		pila.insertaId(id, this);
		boolean b = returnType.bind(pila);
		pila.abreBloque();
		for (Dec a : args) {
			b = b && a.bind(pila);
		}
		b = b && bloque.bind(pila) && e.bind(pila);
		pila.cierraBloque();

		return b;
	}

	public void type() throws TypeException {
		returnType.type();
		if (!returnType.isAssignable())
			throw new TypeException(returnType.getFila(), returnType.getColumna(),
					"EL tipo que se devuelve no es asignable");
		for (Dec a : args)
			a.type();
		bloque.type();
		e.type();
		if (!e.getTipo().equals(returnType))
			throw new TypeException(e.getFila(), e.getColumna(),
					"La expresion " + e.toString() + " no coincide con el tipo que se tiene que devolver");
	}

	
}