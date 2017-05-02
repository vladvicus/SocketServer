package com.epam;

import com.epam.model.Book;

import java.util.ArrayList;
import java.util.List;

public class Store {
    static List<Book> books = new ArrayList<Book>();

    public static List<Book> getBooks() {
        return books;
    }

    public static void setBooks(List<Book> books) {
        Store.books = books;
    }

    public static List<Book> getAllBook() {

        books.add(new Book(01, "Java", "third", "Herbert Schildt", "1987-05-12 00:00:00 -0800"));
        books.add(new Book(02, "C++", "second", "E.Balagurusamy", "1987-05-12 00:00:00 -0700"));
        books.add(new Book(03, "Java", "forth", "B.Ekkel", "1995-05-12 00:00:00 -0700"));
        setBooks(books);
        return books;
    }

    public static void addBook(Book book) {

        books.add(book);
    }

    public static boolean updateBook(Book bookCreate) {

        for (Book book : books) {

            if (book.getId() == bookCreate.getId()) {

                books.set(book.getId()-1,bookCreate);

                return true;
            }
        }
        return false;
    }
}


