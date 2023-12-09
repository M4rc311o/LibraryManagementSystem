package org.but.feec.bds.services;

import org.but.feec.bds.api.BookRequestCreateView;
import org.but.feec.bds.data.BookRequestRepository;

public class BookRequestService {
    private BookRequestRepository bookRequestRepository;

    public BookRequestService(BookRequestRepository bookRequestRepository) {
        this.bookRequestRepository = bookRequestRepository;
    }

    public void createBookRequest(BookRequestCreateView bookRequestCreateView) {
        bookRequestRepository.createBookRequest(bookRequestCreateView);
    }
}
