package ast;

import java.util.ArrayList;

import errors.TypeException;

public class DecMain extends DecFunVoid {

    public DecMain(int fila, int columna, String id, Type tipo, ArrayList<Dec> args, Ins bloque) {
        super(fila, columna, id, tipo, args, bloque);
        this.tipoDec = KindDec.FUNVOID;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("|DecMain| main ");
        str.append("{\n");
        str.append(bloque.toString());
        str.append("\n}");
        return str.toString();
    }

    public void type() throws TypeException{
        tipo.type();
        bloque.type();
    }

    // generateCode heredado de DecFunVoid
}