package ru.job4j.io.scanner;

import java.io.*;
import java.util.Scanner;

/**
 * В качестве источника данных Scanner принимает любой вид данных,
 * включая Reader, InputStream, File для java.io и Readable,
 * Path для java.util.nio. Также можно задать источник в виде строки String.
 * Важно! Если Scanner работает с внешними источниками его нужно использовать с try-with-resources.
 * Класс java.util.Scanner может быть полезен, когда нужно вычленить из данных только те,
 * что Вам нужны. Для этого нужно назначить разделитель, например, пробел, запятая или регулярное выражение.
 * Также Scanner имеет полезную особенность для чтения чисел различных систем счисления.
 */
public class ScannerExamples {

    /**
     * Если следующий символ в строке число то печатаем его спомощью сканера
     * (join - склеить в одну строку из нескольких с помощью разделителя)
     * В качестве входа в сканер подали массив символов
     */
    public static void example1() {
        var data = String.join(System.lineSeparator(),
                "1 2 3 a f", "4 g 5 6", "7 8 f 9");
        var scanner = new Scanner(new CharArrayReader(data.toCharArray()));
        while (scanner.hasNextInt()) {
            System.out.print(scanner.nextInt());
            System.out.print(" ");
        }
    }

    /**
     * На вход сканера подаем строку ввиде массива байтов
     * Делим строку с помощью метода сканера и выводим на экран
     */
    public static void example2() {
        var data = "empl1@mail.ru, empl2@mail.ru, empl3@mail.ru";
        var scanner = new Scanner(new ByteArrayInputStream(data.getBytes()))
                .useDelimiter(", ");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }

    /**
     * Сканер умеет работать с системами счисления
     * Например на вход подаем хекс на выходе печатаем в десятичной( с помощью int)
     * Записали строку в файл -> считали из файла сделав перевод системы счисления
     */
    public static void example3() throws Exception {
        var data = "A 1B FF 110";
        var file = File.createTempFile("data", null);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            out.write(data.getBytes());
        }
        try (var scanner = new Scanner(file).useRadix(16)) {
            while (scanner.hasNextInt()) {
                System.out.print(scanner.nextInt());
                System.out.print(" ");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        example1();
        example2();
        example3();
    }
}
