#! /bin/bash
javac -cp "cup.jar" */*.java
java -cp ".:cup.jar" constructorast.Main ./inputs/input_funciones.txt
./wat2wasm main.wat
node main.js