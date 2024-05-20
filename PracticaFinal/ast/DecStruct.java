package ast;

import java.util.ArrayList;
import errors.TypeException;

public class DecStruct extends Dec {
    private ArrayList<Dec> listaDs;

    public DecStruct(int fila, int columna, String id, Type tipo, ArrayList<Dec> listaDs) {
        super(fila, columna, tipo, id);
        this.tipoDec = KindDec.STRUCT;
        this.listaDs = listaDs;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("    |DecStruct| stalinTruct ");
        str.append(id);
        str.append(" {\n");
        for (Dec dec : listaDs) {
            str.append("  ");
            str.append(dec.toString());
            str.append('\n');
        }
        str.append('}');
        str.append('\n');
        return str.toString();
    }

    public void type() throws TypeException {
        tipo.type();
    }

    public int setDelta(int d){
        delta = d;
        int aux = 0;
        for (Dec dec : listaDs){
            aux = dec.setDelta(aux);
        }
        return delta;
    }
    
    public void setDepth(int d){
        super.setDepth(d);
        for (Dec dec : listaDs){
            dec.setDepth(d);
        }
    }

    public String generateCode(int depth){
        StringBuilder ss = new StringBuilder();
        for (Dec dec : listaDs) ss.append(dec.generateCode(depth));
        return ss.toString();
    }
}