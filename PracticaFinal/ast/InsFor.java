package ast;

import errors.TypeException;

public class InsFor extends Ins {
	private Exp e;
	private Ins asig;
	private Ins bloque;
	private Dec dec;

	public InsFor(int fila, int columna, Dec dec, Exp e, Ins asig, Ins bloque) {
		this.fila = fila;
		this.columna = columna;
		this.dec = dec;
		this.e = e;
		this.asig = asig;
		this.bloque = bloque;
		this.tipoIns = KindIns.FOR;
	}

	public String toString() {
		StringBuilder str = new StringBuilder("forLetariat ( ");
		str.append(dec.toString());
		str.append(" ");
		str.append(e.toString());
		str.append("; ");
		str.append(asig.toString());
		str.append(" ) ");
		str.append("{\n");
		str.append(bloque.toString());
		str.append("\n}");
		return str.toString();
	}

	public boolean bind(Pila pila) {
		pila.abreBloque();
		boolean b = dec.bind(pila) && e.bind(pila) && asig.bind(pila) && bloque.bind(pila);
		pila.cierraBloque();
		return b;
	}

	public void type() throws TypeException {
		e.type();
		asig.type();
		dec.type();
		if (dec.getKindDec() == KindDec.VARIABLE && dec.getTipo().getKindType() == KindType.ENT
				&& asig.kindIns() == KindIns.ASIG && e.getTipo().getKindType() == KindType.BOOL) {
			bloque.type();
		} else
			throw new TypeException(fila, columna, "La declaracion del for no es correcta");
	}

	public int setDelta(int d) {
		int i = d;
		i = dec.setDelta(i);
		i = bloque.setDelta(i);
		return d;
	}

	public String generateCode(int depth) {
		StringBuilder ss = new StringBuilder();
		ss.append("block\n");
		ss.append(dec.generateCode(depth));
		ss.append("loop\n");
		ss.append(e.generateCode(depth));
		ss.append("i32.eqz\n");
		ss.append("br_if 1\n");
		sb.append(bloque.generateCode(depth));
		ss.append(asig.generateCode(depth));
		ss.append("br 0\n");
		ss.append("end\n");
		ss.append("end\n");
		return ss.toString();
	}
}