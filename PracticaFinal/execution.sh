javac -cp "cup.jar" */*.java
java -cp ".:cup.jar" constructorast.Main input2.txt
./wat2wasm main.wat
node main.js