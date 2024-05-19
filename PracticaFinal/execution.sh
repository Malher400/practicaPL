#! /bin/bash
javac -cp ./cup.jar */*.java
java -cp ".:cup.jar" constructorast.Main input.txt
./wat2wasm main.wat
node main.js
