package ast;

import java.util.ArrayList;
import java.util.HashMap;

import errors.TypeException;

public class InsSwitch extends Ins {
	private Exp e;
	private HashMap<String,Ins> casos;
	
	public InsSwitch(int fila,int columna,Exp e,HashMap<String,Ins> casos){
		this.fila = fila;
		this.columna = columna;
        this.casos = casos;
		this.tipoIns = KindIns.SWITCH;
        this.e = e;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("caSoviets ");
		sb.append(e.toString());
		sb.append(":\n");
		for (String str : casos.keySet()) {
			if (str != "default") {
				sb.append("caSoviet ");
				sb.append(str);
				sb.append(" do\n ");
			}
			else sb.append("Por defecto\n");
			sb.append(casos.get(str).toString());
		}
	sb.append("fcaSoviets\n");
	return sb.toString();
	}
	
	public boolean bind(Pila pila) {
		// ArrayList<BindException> errors = new ArrayList<BindException>();
		//try {
		boolean b = e.bind(pila);
		/* }
		catch (BindException be) {
			errors.add(be);
		}
            */
        for(String str: casos.keySet()) {
        	//try {
        	b &= casos.get(str).bind(pila);
    		//}
    		//catch (BindException be) {
    		//	errors.add(be);
    		//}
        }
        //if (errors.size() > 0) throw new BindException(errors);

        return b;
	}
	
    /*
	public void simplify() {
    	e.simplify();
    	for(String str: casos.keySet()) {
    		casos.get(str).simplify();
        }
    }
    */
	
	public void type() throws TypeException {
		e.type();
		if (e.getTipo().getKindType() != KindType.ENT) throw new TypeException(e.getFila(),e.getColumna(),"La expresion " + e.toString() + " no es de tipo entero");
	    for (String str: casos.keySet()) {
	    	casos.get(str).type();
	    }
	}

	public String generateCode(int depth) {
		int maximo = 0;
		int size = casos.size();
		for(String s:casos.keySet()) {
			if(s != "default") {
				maximo = Math.max(maximo,Integer.parseInt(s));
			}
			else {
				size--;
			}
		}
		ArrayList<Integer> tabla = new ArrayList<Integer>();
		int x = 0;
		for(int i = 0; i <= maximo;i++) {
			if(casos.containsKey(String.valueOf(i))) {
				tabla.add(x);
				x++;
			}
			else {
				tabla.add(size);
			}
		}
		tabla.add(size);
    	StringBuilder sb = new StringBuilder("");
    	sb.append("block\n");
    	for(int i = 0; i <= size;i++) {
    		sb.append("block\n");
    	}
    	sb.append(e.generateCode(depth));
    	sb.append("br_table");
    	for(Integer i:tabla) {
    		sb.append(" ");
    		sb.append(i);
    	}
    	sb.append("\n");
    	for(int i = 0; i <= maximo;i++) {
    		if(casos.containsKey(String.valueOf(i))) {
    			sb.append("end\n");
    			sb.append(casos.get(String.valueOf(i)).generateCode(depth));
    			sb.append("br ");
    			sb.append(size-tabla.get(i));
    			sb.append("\n");
    		}
    	}
    	sb.append("end\n");
    	if(casos.containsKey("default")) {
    		sb.append(casos.get("default").generateCode(depth));
    	}
    	sb.append("end\n");
    	return sb.toString();
    }
	
	public String generateCodeFun(int depth) {
		StringBuilder sb = new StringBuilder();
		for(String str: casos.keySet()) {
			sb.append(casos.get(str).generateCodeFun(depth));
        }
		return sb.toString();
    }
	
	public void setName(HashMap<String,Integer> tablaNombres) {
    	for(String str: casos.keySet()) {
    		casos.get(str).setName(tablaNombres);
        }
	}
	
    public void setDepth(int prof) {
    	for(String str: casos.keySet()) {
    		casos.get(str).setDepth(prof);
        }
    }
	
	public int calculateDelta(int pos) {		
		for(String str : casos.keySet()) {
			casos.get(str).calculateDelta(pos);
		} 
		return pos;
	}
	
    public void setMaxSize(Pair<Integer, Integer> currentMax) {
    	for (String str : casos.keySet()) casos.get(str).setMaxSize(currentMax);
    }
    
    public void setNumIfs(int num) {
    	for(String str : casos.keySet()) {
			casos.get(str).setNumIfs(0);
		}
    }
}
