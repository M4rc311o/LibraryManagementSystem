package org.but.feec.bds.services;

import org.but.feec.bds.api.BookSimpleView;
import org.but.feec.bds.data.BookRepository;

import java.util.List;

public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookSimpleView> getBooksSimpleView() {
        return bookRepository.getBooksSimpleView();
    }
}
