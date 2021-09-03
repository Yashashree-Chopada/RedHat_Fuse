package com.mycompany.camel;

import org.apache.camel.main.Main;

public class Launcher {
    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        Main main = new Main();
        //JAXBProcessor jb = new JAXBProcessor();
        main.addRouteBuilder(new CamelRoute());
        main.run(args);
    }
}
