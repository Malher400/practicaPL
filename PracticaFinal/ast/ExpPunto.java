package ast;

import errors.TypeException;

public class ExpPunto extends Exp {
	private Exp id;
	private String campo;

	public ExpPunto(int fila, int columna, Exp id, String campo) {
		this.fila = fila;
		this.columna = columna;
		this.id = id;
		this.campo = campo;
		this.tipoExp = KindExp.PUNTO;
	}

	public String toString() {
		return id.toString() + '.' + campo;
	}

	public boolean bind(Pila pila) {
		return id.bind(pila);
	}

	public void type() throws TypeException {
		id.type();
		if (id.getTipo().getKindType() == KindType.IDEN) {
			if (id.getTipo().getTipo().getKindType() == KindType.STRUCT) {
				if (id.getDec(campo) != null) {
					tipo = id.getDec(campo).getTipo();
					designador = id.getDesignador();
				} else
					throw new TypeException(fila, columna, "No existe un campo " + campo + " dentro de este struct");
			} else
				throw new TypeException(fila, columna, "La expresion " + id.toString() + " no es de tipo struct");
		} else
			throw new TypeException(fila, columna,
					"La expresion " + id.toString() + " no es de tipo iden");
	}

	public String generateCodeD(int depth) {
		StringBuilder ss = new StringBuilder();
		try {
			ss.append(id.codeD(depth));
			ss.append("\ti32.const ");
			ss.append(id.getDec(campo).getDelta());
			ss.append("\n");
			ss.append("\ti32.add\n");
		} catch (TypeException e) {
			e.printStackTrace();
		}
		return ss.toString();
	}

	public String generateCode(int depth) {
		StringBuilder ss = new StringBuilder();
		ss.append(codeD(depth));
		ss.append("\ti32.load\n");
		return ss.toString();
	}

}