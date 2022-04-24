package ru.job4j.ood.solid.lsp;

public class TextReader {
    static class Text {
    }

    static class ReaderOld {
        void read(Text text) {
            checkText(text);
        }

        void checkText(Text text) {

        }
    }

    static class ReaderNew extends ReaderOld {
        @Override
        void read(Text text) {
            /*
            забыли метод проверки
             */
        }
    }
}
