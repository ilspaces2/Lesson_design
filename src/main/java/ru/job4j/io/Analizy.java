package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader input = new BufferedReader(new FileReader(source));
             PrintWriter output = new PrintWriter(new FileOutputStream(target))) {
            boolean serverOff = true;
            String lines;
            String[] strings;
            while ((lines = input.readLine()) != null) {
                strings = lines.split(" ");
                if ((strings[0].equals("400") || strings[0].equals("500")) && serverOff) {
                    output.print(strings[1] + ";");
                    serverOff = false;
                }
                if ((strings[0].equals("200") || strings[0].equals("300")) && !serverOff) {
                    output.println(strings[1] + ";");
                    serverOff = true;
                }
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
