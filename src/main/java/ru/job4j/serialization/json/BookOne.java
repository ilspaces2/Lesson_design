package ru.job4j.serialization.json;

import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

public class BookOne {
    private BookTwo bookTwo;
    private String bookOne = "1";

    @JSONPropertyIgnore
    public BookTwo getBookTwo() {
        return bookTwo;
    }

    public String getBookOne() {
        return bookOne;
    }

    public void setBookTwo(BookTwo bookTwo) {
        this.bookTwo = bookTwo;
    }


    public static void main(String[] args) {
        BookOne one = new BookOne();
        BookTwo two = new BookTwo();
        one.setBookTwo(two);
        two.setBookOne(one);

        System.out.println(new JSONObject(one));
    }
}
