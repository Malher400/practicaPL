package ast;

import java.util.ArrayList;
import java.util.HashMap;

public class Pila {
    private ArrayList<HashMap<String, Dec>> pila;
    private int size;

    public Pila() { // inicializa
        this.pila = new ArrayList<HashMap<String, Dec>>();
        size = 0;
    }

    public void abreBloque() {
        pila.add(new HashMap<String, Dec>());
        size++;
    }

    public void cierraBloque() {
        pila.remove(size - 1);
        size--;
    }

    public void insertaId(String id, Dec dec) {
        pila.get(size - 1).put(id, dec);
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
}