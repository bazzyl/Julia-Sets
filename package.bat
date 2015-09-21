md classpath
javac -d classpath *.java
jar.exe cvfm Julia.jar manifest.txt -C classpath/ .
pause