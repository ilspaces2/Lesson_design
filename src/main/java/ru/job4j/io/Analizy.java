package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader input = new BufferedReader(new FileReader(source));
             PrintWriter output = new PrintWriter(new FileOutputStream(target))) {
            boolean serverOff = true;
            String lines;
            String[] strings = null;
            while ((lines = input.readLine()) != null) {
                strings = lines.split(" ");
                if (("400".equals(strings[0]) || "500".equals(strings[0])) && serverOff) {
                    output.print(strings[1] + ";");
                    serverOff = false;
                }
                if (("200".equals(strings[0]) || "300".equals(strings[0])) && !serverOff) {
                    output.println(strings[1] + ";");
                    serverOff = true;
                }
            }
            if (!serverOff) {
                output.print(strings[1] + ";");
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.txt", "target.txt");
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
