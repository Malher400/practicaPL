package ast;

import java.util.ArrayList;
import errors.TypeException;

public class DecFunVoid extends Dec {
    protected ArrayList<Dec> args;
    protected Ins bloque;
    protected ArrayList<Type> tiposArgs;

    public DecFunVoid(int fila, int columna, String id, Type tipo, ArrayList<Dec> args, Ins bloque) {
        super(fila, columna, tipo, id);
        this.tipoDec = KindDec.FUNVOID;
        this.args = args;
        this.bloque = bloque;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("    |DecFunVoid| fun void ");
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
        //str.append("\n}");
        return str.toString();
    }

    public int setDelta(int d){
		int n = 12;
		for (Dec dec: args) {
			n = dec.setDelta(n);
		}
		n = bloque.setDelta(n);
		return d;
	}

    public void setDepth(int d){
        super.setDepth(d);
        for (Dec dec : args) {
            dec.setDepth(d+1);
        }
        bloque.setDepth(d+1);
    }

    public boolean bind(Pila pila) {
        pila.insertaId(id, this);
        boolean b = true;
        pila.abreBloque();
        for (Dec a : args) {
            b = b && a.bind(pila);
        }
        b = b && bloque.bind(pila);
        pila.cierraBloque();

        return b;
    }

    public void type() throws TypeException {
        for (Dec a : args)
            a.type();
        bloque.type();
    }

    public String codeFun(int depth) {
    	StringBuilder ss = new StringBuilder("(func $");
    	ss.append(id);
    	ss.append("\n");
    	ss.append(bloque.generateCode(depth+1));
    	ss.append(")\n");
    	return ss.toString();
    }

    public String generateCode(int depth) {
        return "";
    }


}