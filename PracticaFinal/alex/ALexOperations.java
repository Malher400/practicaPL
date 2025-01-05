package alex;

import constructorast.ClaseLexica;

public class ALexOperations {
  private AnalizadorLexicoTiny alex;
  public ALexOperations(AnalizadorLexicoTiny alex) {
   this.alex = alex;   
  }
  public void error() { // 0
   System.err.println("***"+alex.fila()+", "+alex.columna()+" Caracter inesperado: "+alex.lexema());
  }
  public UnidadLexica unidadEof() { // 1
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.EOF); 
  }
  public UnidadLexica unidadVar() { // 2
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.VAR); 
  }
  public UnidadLexica unidadBool() { // 3
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.BOOL, alex.lexema()); // Nos importa lo que vale
  }
  public UnidadLexica unidadEntPositivo() { // 4
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ENTPOS, alex.lexema()); // Nos importa lo que vale
  }
  public UnidadLexica unidadGuevArray() { // 5
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.GUEVARRAY); 
  }
  public UnidadLexica unidadPointer() { // 6
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.POINTER); 
  }
  public UnidadLexica unidadStalinTruct() { // 7
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.STALINTRUCT); 
  }
  public UnidadLexica unidadMain() { // 8
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAIN); 
  }
  public UnidadLexica unidadLenInt() { // 9
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.LENINT); 
  }
  public UnidadLexica unidadBoolShevik() { // 10
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.BOOLSHEVIK); 
  }
  public UnidadLexica unidadNull() { // 11
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.NULL); 
  } 
  public UnidadLexica unidadFun() { // 12
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FUN); 
  } 
  public UnidadLexica unidadTrotskIf() { // 13
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.TROTSKIF); 
  }
  public UnidadLexica unidadFidElseCastro() { // 14
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FIDELSECASTRO); 
  }
  public UnidadLexica unidadUSSWhile() { // 15
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.USSWHILE); 
  } 
  public UnidadLexica unidadForLetariat() { // 16
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FORLETARIAT); 
  } 
  
  public UnidadLexica unidadRed() { // 17
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.RED); 
  } 
  public UnidadLexica unidadWrite() { // 18
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.WRITE); 
  } 
  public UnidadLexica unidadCall() { // 19
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CALL); 
  } 
  public UnidadLexica unidadNew() { // 20
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.NEW); 
  } 
  public UnidadLexica unidadDelete() { // 21
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DELETE); 
  } 
  public UnidadLexica unidadConst() { // 22
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CONST); 
  }
  public UnidadLexica unidadIden() { // 23
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IDEN, alex.lexema()); // Nos importa como se llama 
  }
  public UnidadLexica unidadMas() { // 24
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAS); 
  }
  public UnidadLexica unidadMenos() { // 25
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENOS); 
  } 
  public UnidadLexica unidadAsterisco() { // 26
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ASTERISCO); 
  }
  public UnidadLexica unidadDivision() { // 27
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DIV); 
  }
  public UnidadLexica unidadResto() { // 28
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.RESTO); 
  }
  public UnidadLexica unidadIgual() { // 29
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IGUAL); 
  }
  public UnidadLexica unidadMenorIgual() { // 30
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENORIGUAL); 
  }
  public UnidadLexica unidadMayorIgual() { // 31
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAYORIGUAL); 
  }
  public UnidadLexica unidadMenor() { // 32
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENOR); 
  }
  public UnidadLexica unidadMayor() { // 33
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAYOR); 
  }
  public UnidadLexica unidadAnd() { // 34
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.AND); 
  }
  public UnidadLexica unidadOr() { // 35
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.OR); 
  }
  public UnidadLexica unidadNegacion() { // 36
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.NEG); 
  }
  public UnidadLexica unidadReferencia() { // 37
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.REF); 
  }
  public UnidadLexica unidadCorcheteAbre() { // 38
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CABRE); 
  }
  public UnidadLexica unidadCorcheteCierra() { // 39
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CCIERRA); 
  }
  public UnidadLexica unidadPunto() { // 40
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PUNTO); 
  }
  public UnidadLexica unidadFlecha() { // 41
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FLECHA); 
  }
  public UnidadLexica unidadParentesisAbre() { // 42
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PABRE); 
  }
  public UnidadLexica unidadParentesisCierra() { // 43
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PCIERRA); 
  }
  public UnidadLexica unidadLlaveAbre() { // 44
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.LLABRE); 
  }
  public UnidadLexica unidadLlaveCierra() { // 45
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.LLCIERRA); 
  }
  public UnidadLexica unidadPuntoYComa() { // 46
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PUNTOYCOMA); 
  }
  public UnidadLexica unidadAsignacion() { // 47
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ASIGNACION); 
  }
  public UnidadLexica unidadReturn() { // 48
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.RETURN);
  }
  public UnidadLexica unidadComa() { // 49
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.COMA);
  }
  public UnidadLexica unidadDistinto() { // 50
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DISTINTO);
  }
  public UnidadLexica unidadType() { // 51
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.TYPE);
  }
  public UnidadLexica unidadVoid() { // 52
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.VOID);
  }
  public UnidadLexica unidadCaSoviets() { // 13
    return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CASOVIETS); 
  }
  public UnidadLexica unidadDosPuntos() {
  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DOSPUNTOS);
  }
}
