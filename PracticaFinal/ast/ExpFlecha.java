package ast;

import errors.TypeException;

public class ExpFlecha extends Exp {
	private Exp id;
	private String campo;

	public ExpFlecha(int fila, int columna, Exp id, String campo) {
		this.fila = fila;
		this.columna = columna;
		this.id = id;
		this.campo = campo;
		this.tipoExp = KindExp.FLECHA;
	}

	public String toString() {
		return id.toString() + "->" + campo;
	}

	public boolean bind(Pila pila) {
		return id.bind(pila);
	}

	public void type() throws TypeException {
		id.type();
		if (id.getTipo().getKindType() == KindType.POINTER) {
			if (id.getTipo().getTipo().getKindType() == KindType.IDEN) {
				if (id.getTipo().getTipo().getTipo().getKindType() == KindType.STRUCT) {
					if (id.getDec(campo) != null) {
						tipo = id.getDec(campo).getTipo();
						designador = id.getDesignador();
					} else
						throw new TypeException(fila, columna,
								"No existe un campo " + campo + " dentro de este struct");

				} else
					throw new TypeException(fila, columna, "La expresion " + id.toString() + " no es de tipo struct");
			} else
				throw new TypeException(fila, columna, "La expresion " + id.toString() + " no es de tipo iden");
		} else
			throw new TypeException(fila, columna, "La expresion " + id.toString() + " no es de tipo puntero");
	}

	public String codeD(int depth) { // Esto no funciona muy bien
		StringBuilder ss = new StringBuilder();

		try {
			ss.append(id.codeD(depth));
			ss.append("\ti32.load\n");
			ss.append("\ti32.const ");
			
			ss.append(id.getDec(campo).getDelta());

			ss.append("\n");
			ss.append("\ti32.add\n");
		} catch (Exception e) {
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