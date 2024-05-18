package ast;

import java.util.ArrayList;
import errors.TypeException;

public class Bloque extends Ins {
	private ArrayList<Dec> decs;
	private ArrayList<Ins> ins;

	public Bloque(ArrayList<Dec> decs, ArrayList<Ins> ins) {
		this.decs = decs;
		this.ins = ins;
		this.tipoIns = KindIns.BLOCK;
	}

	public String toString() {
		StringBuilder str = new StringBuilder("    |Bloque|");
		for (Dec d : decs) {
			str.append("  ");
			str.append(d.toString());
			str.append('\n');
		}
		str.append('\n');
		for (Ins i : ins) {
			str.append("  ");
			str.append(i.toString());
			str.append('\n');
		}
		return str.toString();
	}

	public boolean bind(Pila pila) {
		boolean b = true;
		for (Dec d : decs) {
			b = b && d.bind(pila);
		}
		for (Ins i : ins) {
			b = b && i.bind(pila);
		}
		return b;
	}

	public void type() throws TypeException {
		ArrayList<TypeException> errores = new ArrayList<TypeException>();
		for (Dec d : decs) {
			try {
				d.type();
			} catch (TypeException te) {
				for (TypeException tEx : te.getErrors()) {
					errores.add(tEx);
				}
				if (te.getErrors().size() == 0)
					errores.add(te);
			}
		}
		for (Ins i : ins) {
			try {
				i.type();
			} catch (TypeException te) {
				for (TypeException tEx : te.getErrors()) {
					errores.add(tEx);
				}
				if (te.getErrors().size() == 0)
					errores.add(te);
			}
		}
		if (errores.size() != 0)
			throw new TypeException(errores);
	}

	public int setDelta(int d) {
		int i = d;
		for (Dec dec : decs)
			i = dec.setDelta(i);
		for (Ins in : ins)
			i = in.setDelta(i);
		return d;
	}

	public String generateCode(int depth) {
		StringBuilder ss = new StringBuilder("");
		for (Dec dec : decs)
			ss.append(dec.generateCode(depth));
		for (Ins i : ins)
			ss.append(i.generateCode(depth));
		return ss.toString();
	}

}