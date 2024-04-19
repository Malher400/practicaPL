package ast;

import java.util.ArrayList;
import java.util.HashMap;

public class Pila {
    private ArrayList<HashMap<String, Dec>> pila;
    private int size;
    private Dec openFun, openClass;
    private Dec lastDec;
    private int levelFun;// nivel de openFun

    public Pila() {
        pila = new ArrayList<HashMap<String, Dec>>();
        size = 0;
    }

    // Para structs y clases:
    public Pila(HashMap<String, Dec> inicio) {
        pila = new ArrayList<HashMap<String, Dec>>();
        pila.add(inicio);
        size = 1;
    }

    public HashMap<String, Dec> getTop() {
        if (pila.size() > 0)
            return pila.get(pila.size() - 1);
        else
            return new HashMap<String, Dec>();
    }

    public void abreBloque() {
        if (lastDec != null) {
            if (lastDec.decKind() == DecKind.TYPE && ((DecType) lastDec).decTypeKind() == DecTypeKind.CLASS)
                openClass = lastDec;
            else if (lastDec.decKind() == DecKind.FUN) {
                openFun = lastDec;
                levelFun = pila.size();
            }
        }
        pila.add(new HashMap<String, Dec>());
    }

    public void cierraBloque() {
        pila.remove(pila.size() - 1);
        if (pila.size() == 1)
            openClass = null;
        if (pila.size() == levelFun)
            openFun = null;
    }

    public void insertaId(String id, Dec dec) {
        pila.get(pila.size() - 1).put(id, dec);
        lastDec = dec;
    }

    public Dec buscaId(String id) {
        Dec d = null;
        int i = pila.size() - 1;
        while (d == null && i >= 0) {
            d = pila.get(i).get(id);
            i = i - 1;
        }
        return d;
    }

    public Dec getOpenFun() {
        return openFun;
    }

    public Dec getOpenClass() {
        return openClass;
    }

    public int getDepth() {
        return pila.size() - 1;
    }

}