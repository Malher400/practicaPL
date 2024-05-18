package ast;

import java.util.ArrayList;
import errors.TypeException;

public class DecFun extends Dec {
	private Type returnType;
	private ArrayList<Dec> args;
	private Ins bloque;
	private Exp e;
	private int MaxSize;

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
		StringBuilder str = new StringBuilder("    |DecFun| fun ");
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
		//str.append("{\n");
		//str.append(bloque.toString());
		//str.append("  return ");
		//str.append(e.toString());
		//str.append(";\n");
		//str.append("}");
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

	public int setDelta(int d){
		int n = 0;
		for (Dec dec: args) {
			n = dec.setDelta(d);
			d = n;
		}
		n = bloque.setDelta(d);
		d = n;
		MaxSize = n;
		return d;
	}

	public String codeFun(int depth) {
    	StringBuilder ss = new StringBuilder("(func $");
    	ss.append(id);
    	ss.append(" (result i32)\n");
    	ss.append(bloque.generateCode(depth+1));
    	ss.append(e.generateCode(depth+1));
    	ss.append(")\n");
    	return ss.toString();
    }

	public String generateCode(int depth){ return ""; }

	public int getSize() {return MaxSize;}  

}