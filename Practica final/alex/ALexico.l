package alex;

%%
%cup
%char
%line
%column
%class AnalizadorLexicoTiny
%type  UnidadLexica
%unicode
%public

%{
  private ALexOperations ops;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
letra  = ([A-Z]|[a-z])

separador = [ \t\r\b\n]
comentario = \/\/[^\n]*

enteroPositivo = ({digitoPositivo}{digito}*|0)
bool = (true|false)
punteroNulo = null

claveVar = var
claveConst = const

identificador = {letra}({letra}|{digito})*

claveInt = lenInt
claveBool = boolShevik
clavePointer = pointer
claveArray = guevArray
claveStruct = stalinTruct

claveMain = main

claveFun = fun        
claveReturn = return         
claveIf = trotskIf                
claveElse = fidElseCastro               
claveWhile = while 
claveFor = forLetariat              
claveRead = red              
claveWrite = write               
claveCall = call                
claveNew = new                  
claveDelete = delete  

operadorMas = \+        
operadorMenos = \-         
operadorAsterisco = \*      
operadorDiv = \/              
operadorResto = \%

operadorIgual = \==   
operadorDistinto = \!=  
operadorMenorIgual = \<=       
operadorMayorIgual = \>=    
operadorMenor = \<         
operadorMayor = \>             
operadorAnd = \&&             
operadorOr = \|\|       

operadorNeg = \!
operadorRef = \#
operadorPunto = \.
operadorFlecha = \->
operadorCAbre \[
operadorCCierra = \]        

parentesisAbre = \(
parentesisCierra = \)
llaveAbre = \{
llaveCierra = \}
puntoYComa = \;
asignacion = \:=
coma = \,





%%
{separador}                 {}
{comentario}                {}

 
{enteroPositivo}            {return ops.unidadEntPositivo();}  
{booleano}                  {return ops.unidadBool();}
{punteroNulo}               {return ops.unidadNull();}

{claveVar}                  {return ops.unidadVar();} 
{claveConst}                {return ops.unidadConst();} 

{identificador}             {return ops.unidadIden();}

{claveInt}                  {return ops.unidadLenInt();} 
{claveBool}                 {return ops.unidadBoolShevik();} 
{clavePointer}              {return ops.unidadPointer();} 
{claveArray}                {return ops.unidadGuevArray();} 
{claveStruct}               {return ops.unidadStalinTruct();} 

{claveMain}                 {return ops.unidadMain();}  

{claveFun}                  {return ops.unidadFun();}
{claveReturn}               {return ops.unidadReturn();}
{claveIf}                   {return ops.unidadTrotskIf();} 
{claveElse}                 {return ops.unidadFidElseCastro();} 
{claveWhile}                {return ops.unidadWhile();} 
{claveFor}                  {return ops.unidadForLetariat();} 
{claveRead}                 {return ops.unidadRed();} 
{claveWrite}                {return ops.unidadWrite();} 
{claveCall}                 {return ops.unidadCall();} 
{claveNew}                  {return ops.unidadNew();} 
{claveDelete}               {return ops.unidadDelete();} 

{operadorMas}               {return ops.unidadMas();}
{operadorMenos}             {return ops.unidadMenos();}
{operadorAsterisco}         {return ops.unidadAsterisco();}
{operadorDiv}               {return ops.unidadDivision();}
{operadorResto}             {return ops.unidadResto();}

{operadorIgual}             {return ops.unidadIgual();}
{operadorDistinto}          {return ops.unidadDistinto();}
{operadorMenorIgual}        {return ops.unidadMenorIgual();}
{operadorMayorIgual}        {return ops.unidadMayorIgual();}
{operadorMenor}             {return ops.unidadMenor();}
{operadorMayor}             {return ops.unidadMayor();}
{operadorAnd}               {return ops.unidadAnd();}
{operadorOr}                {return ops.unidadOr();}

{operadorNeg}               {return ops.unidadNegacion();}
{operadorRef}               {return ops.unidadReferencia();}
{operadorPunto}             {return ops.unidadPunto();}
{operadorFlecha}            {return ops.unidadFlecha();}
{operadorCAbre}             {return ops.unidadCorcheteAbre();}
{operadorCCierra}           {return ops.unidadCorcheteCierra();}

{parentesisAbre}            {return ops.unidadParentesisAbre();}
{parentesisCierra}          {return ops.unidadParentesisCierra();}
{llaveAbre}                 {return ops.unidadLlaveAbre();}
{llaveCierra}               {return ops.unidadLlaveCierra();}
{puntoYComa}                {return ops.unidadPuntoYComa();}
{asignacion}                {return ops.unidadAsignacion();}
{coma}                      {return ops.unidadComa();}

[^]                         {ops.error();}  