package ru.job4j.serialization.json;

public class BookTwo {
    private BookOne bookOne;
    private String bookTwo = "2";


    public BookOne getBookOne() {
        return bookOne;
    }

    public String getBookTwo() {
        return bookTwo;
    }

    public void setBookOne(BookOne bookOne) {
        this.bookOne = bookOne;
    }

}
