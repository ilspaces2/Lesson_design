package ru.job4j.design.isp.menu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SimpleMenuPrinterInterceptor extends SimpleMenuPrinter {
    private String output = "";

    public String getOutput() {
        return output;
    }

    @Override
    public void print(Menu menu) {
        var stdout = System.out;
        var buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        super.print(menu);
        System.setOut(stdout);
        output = buffer.toString();
    }
}
