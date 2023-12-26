package org.but.feec.bds.services;

import org.but.feec.bds.api.BookRequestCreateView;
import org.but.feec.bds.api.BookRequestEditView;
import org.but.feec.bds.api.BookRequestSimpleView;
import org.but.feec.bds.data.BookRequestRepository;

import java.util.List;

public class BookRequestService {
    private BookRequestRepository bookRequestRepository;

    public BookRequestService(BookRequestRepository bookRequestRepository) {
        this.bookRequestRepository = bookRequestRepository;
    }

    public void createBookRequest(BookRequestCreateView bookRequestCreateView) {
        bookRequestRepository.createBookRequest(bookRequestCreateView);
    }

    public List<BookRequestSimpleView> getBookRequestsSimpleView() {
        return bookRequestRepository.getBookRequestsSimpleView();
    }

    public void editBookRequest(BookRequestEditView bookRequestEditView) {
        bookRequestRepository.editBookRequest(bookRequestEditView);
    }

    public void deleteBookRequest(Long id) {
        bookRequestRepository.deleteBookRequest(id);
    }
}
