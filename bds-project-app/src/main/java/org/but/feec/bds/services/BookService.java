package org.but.feec.bds.services;

import org.but.feec.bds.api.BookCreateView;
import org.but.feec.bds.api.BookDetailedView;
import org.but.feec.bds.api.BookSimpleView;
import org.but.feec.bds.data.BookRepository;
import org.but.feec.bds.exceptions.ResourceNotFoundException;

import java.util.List;

public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookSimpleView> getBooksSimpleView() {
        return bookRepository.getBooksSimpleView();
    }

    public List<String> getBookGenres() {
        return bookRepository.getBookGenres();
    }

    public List<String> getBookLanguages() {
        return bookRepository.getBookLanguages();
    }

    public List<String> getBookBindings() {
        return bookRepository.getBookBindings();
    }

    public List<String> getBookLiteraryPeriods() {
        return bookRepository.getBookLiteraryPeriods();
    }

    public BookDetailedView getBookDetailedViewByIsbn(String isbn) {
        BookDetailedView bookDetailedView = bookRepository.getBookDetailedViewByIsbn(isbn);
        if (bookDetailedView == null) {
            throw new ResourceNotFoundException("Provided isbn was not found.");
        }
        return bookDetailedView;
    }

    public Long createBook(BookCreateView bookCreateView) {
        return bookRepository.createBook(bookCreateView);
    }

    public boolean bookExists(String isbn) {
        return bookRepository.getBookDetailedViewByIsbn(isbn) != null;
    }
}
