package ast;

import java.util.ArrayList;
import errors.TypeException;

public class Program implements ASTNode {
    private ArrayList<Dec> decs;
    private DecMain main;
    private int fila;
    private int columna;
    private int maxSize = 0; // CALCULAR

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public Program(int fila, int columna, ArrayList<Dec> decs, DecMain main) {
        this.fila = fila;
        this.columna = columna;
        this.decs = decs;
        this.main = main;
    }

    public NodeKind nodeKind() {
        return NodeKind.PROGRAM;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Dec dec : decs) {
            str.append(dec.toString());
            str.append('\n');
        }
        str.append(main.toString());
        return str.toString();
    }

    public boolean bind(Pila pila) {
        boolean b = true;
        pila.abreBloque();
        for (Dec d : decs) {
            b = d.bind(pila) && b;
        }
        b = main.bind(pila) && b;
        pila.cierraBloque();


        return b;
    }

    public void type() throws TypeException {
        for (Dec d : decs)
            d.type();
        main.type();
    }

    public int setDelta(int d) {
        return d;
    }

    public String codeFun(int depth) {
    	StringBuilder ss = new StringBuilder("");
    	for (Dec dec : decs) ss.append(dec.codeFun(depth));
    	ss.append(main.codeFun(depth));
    	return ss.toString();
    }

    

    public String generateCode(int depth){
        StringBuilder ss = new StringBuilder("");
        ss.append("(module \n");
    	ss.append("(import \"runtime\" \"print\" (func $print (type $_sig_i32)))\n");
    	ss.append("(import \"runtime\" \"read\" (func $read (result i32)))\n");
    	ss.append("(import \"runtime\" \"exceptionHandler\" (func $exception (type $_sig_i32)))\n");
    	ss.append("(type $_sig_i32 (func (param i32)))\n");
    	ss.append("(type $_sig_void (func ))\n");
    	ss.append("(type $_sig_ri32 (func (result i32)))\n");
    	ss.append("(memory 2000)\n");
    	ss.append("(global $SP (mut i32) (i32.const 0))\n");
    	ss.append("(global $MP (mut i32) (i32.const 0))\n");
    	ss.append("(global $NP (mut i32) (i32.const 131071996))\n");

        ss.append("(func $reserveStack (param $size i32) (result i32)\n");
        ss.append("\tglobal.get $MP\n");
    	ss.append("\tglobal.get $SP\n");
    	ss.append("\tglobal.set $MP\n");
    	ss.append("\tglobal.get $SP\n");
    	ss.append("\tlocal.get $size\n");
    	ss.append("\ti32.add\n");
    	ss.append("\tglobal.set $SP\n");
    	ss.append("\tglobal.get $SP\n");
    	ss.append("\tglobal.get $NP\n");
    	ss.append("\ti32.gt_u\n");
    	ss.append("\tif\n"); // SP no puede ser mayor que NP
    	ss.append("\ti32.const 3\n");
    	ss.append("\tcall $exception\n");
    	ss.append("\tend\n");
    	ss.append(")\n");

    	ss.append("(func $freeStack\n");
    	ss.append("(type $_sig_void)\n");
    	ss.append("\tglobal.get $MP\n");
    	ss.append("\ti32.load\n"); // Apilamos enlace dinamico = MP del marco de la funcion llamante
    	ss.append("\ti32.load offset=8\n"); // Apilamos MP del marco de la funcion que se elimina
    	ss.append("\tglobal.set $SP\n"); // Actualizamos $SP al MP del marco de la funcion que se elimina
    	ss.append("\tglobal.get $MP\n");
    	ss.append("\ti32.load\n"); // Apilamos enlace dinamico = MP del marco de la funcion llamante
    	ss.append("\tglobal.set $MP\n"); // Actualizamos $MP al MP del marco de la funcion llamante
    	ss.append(")\n");

        // Funcion que reserva $size posiciones de memoria del heap.
    	// Devuelve la primera posicion del heap reservada.
    	ss.append("(func $reserveHeap (param $size i32) (result i32)\n");
    	ss.append("\tglobal.get $NP\n");
    	ss.append("\tlocal.get $size\n");
    	ss.append("\ti32.sub\n");
    	ss.append("\tglobal.set $NP\n"); // Actualizamos $NP a $NP - $size
    	ss.append("\tglobal.get $NP\n");
    	ss.append("\ti32.const 4\n");
    	ss.append("\ti32.add\n"); // Apilamos el resultado ($NP + 4)
    	ss.append("\tglobal.get $SP\n");
    	ss.append("\tglobal.get $NP\n");
    	ss.append("\ti32.gt_u\n");
    	ss.append("\tif\n"); // Si $SP > $NP salta una excepcion
    	ss.append("\ti32.const 3\n");
    	ss.append("\tcall $exception\n");
    	ss.append("\tend\n");
    	ss.append(")\n");
    	
    	
    	ss.append("(func $jumpStatic (param $depth i32) (result i32)\n");
    	ss.append("(local $i i32)\n"); // Variable local iteraciones bucle
    	ss.append("(local $marco i32)\n"); // Marco actual
    	ss.append("\tglobal.get $MP\n");
    	ss.append("\tlocal.set $marco\n"); // Guardamos en $marco el $MP actual (0 saltos)
    	ss.append("\tlocal.get $depth\n");
    	ss.append("\tlocal.set $i\n"); // Guardamos en $i el numero de saltos
    	ss.append("\tblock\n");
    	ss.append("\tloop\n");
    	ss.append("\tlocal.get $i\n");
    	ss.append("\ti32.eqz\n");
    	ss.append("\tbr_if 1\n"); // Salimos del bucle si $i == 0
    	ss.append("\tlocal.get $marco\n");
    	ss.append("\ti32.const 4\n");
    	ss.append("\ti32.add\n");
    	ss.append("\ti32.load\n");
    	ss.append("\tlocal.set $marco\n"); // Guardamos en $marco el enlace estatico del $marco actual (que esta en MP + 4)
    	ss.append("\tlocal.get $i\n");
    	ss.append("\ti32.const 1\n");
    	ss.append("\ti32.sub\n");
    	ss.append("\tlocal.set $i\n"); // Hacemos i--
    	ss.append("\tbr 0\n"); // Volvemos al bucle
    	ss.append("\tend\n");
    	ss.append("\tend\n");
    	ss.append("\tlocal.get $marco\n");// Apilamos el resultado
    	ss.append(")\n");
    
    	
    	ss.append(codeFun(depth+1));
    	
        
        ss.append("(func $_main_\n");
    	// Creación de marco de $_main_
    	ss.append("\tglobal.get $SP\n");
    	ss.append("\ti32.const ");
    	ss.append(maxSize);
    	ss.append("\n");
    	ss.append("\ti32.const 12\n");
		ss.append("\ti32.add\n");
		ss.append("\tcall $reserveStack\n");
		ss.append("\ti32.store\n");
		ss.append("\tglobal.get $MP\n");
		ss.append("\tglobal.get $MP\n");
		ss.append("\ti32.store offset=4\n");
		ss.append("\tglobal.get $MP\n");
		ss.append("\tglobal.get $SP\n");
		ss.append("\ti32.store offset=8\n");
		
    	for (Dec dec : decs) ss.append(dec.generateCode(depth+1));

		ss.append("\tglobal.get $SP\n");
		ss.append("\ti32.const ");
		ss.append(0); // maxSize de main
		ss.append("\n");
		ss.append("\ti32.const 12\n");
		ss.append("\ti32.add\n");
		ss.append("\tcall $reserveStack\n");
		ss.append("\ti32.store\n");
		ss.append("\tglobal.get $MP\n");
		ss.append("\tglobal.get $MP\n");
		ss.append("\ti32.load\n");
		ss.append("\ti32.store offset=4\n");
		ss.append("\tglobal.get $MP\n");
		ss.append("\tglobal.get $SP\n");
		ss.append("\ti32.store offset=8\n");
    	ss.append("\tcall $");
    	ss.append(main.getId());
    	ss.append("\n");
    	ss.append("if\n"); // Si $SP > $NP salta una excepcion
    	ss.append("\ti32.const 3\n");
    	ss.append("\tcall $exception\n");
    	ss.append("end\n");
		ss.append("call $freeStack\n");
    	ss.append(")\n");
    	ss.append("(start $_main_)\n)\n");

        return ss.toString();
    }

}
