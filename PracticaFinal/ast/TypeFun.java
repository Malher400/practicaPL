package ast;

public class TypeFun extends Type {
    private ArrayList<Dec> listaArgs;
    private HashMap<String, Dec> dicArgs;

    public TypeFun(Type tipo, ArrayList<Dec> listaArgs) {
        this.tipoType = KindType.FUN;
        this.tipo = tipo;
        this.listaArgs = listaArgs;
        this.dicArgs = new HashMap<String, Dec>();
        for (Dec dec : listaArgs)
            dicArgs.put(dec.getIden(), dec);
    }

    public String toString() {
        StringBuilder str = new StringBuilder("");
        for (Dec dec : listaArgs) {
            str.append(dec.getTipo().toString());
            str.append(" x ");
        }
        str.delete(str.length() - 3, str.length());
        str.append(" -> ");
        str.append(tipo.toString());
        str.append('\n');
        return str.toString();
    }
}