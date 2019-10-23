1.如何用jdk1.8运行：
    1.用java -version ，应该是1.8
    2.在 Project Structure中，能设1.8的都设1.8，能设8的都设8
    3.在 Settings->Compiler -> Java Compiler  设置相应Module的 bytecode version为1.8即可
    4.现在的工程里pom.xml里还是<java.version>1.7</java.version>和maven-compiler-plugin的版本还是1.7，不过暂时照样还是能运行起来