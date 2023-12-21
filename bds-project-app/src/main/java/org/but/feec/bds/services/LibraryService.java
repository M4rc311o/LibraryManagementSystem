package org.but.feec.bds.services;

import org.but.feec.bds.api.LibrarySimpleView;
import org.but.feec.bds.data.LibraryRepository;

import java.util.List;

public class LibraryService {
    private LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public List<LibrarySimpleView> getLibrariesSimpleView() {
        return libraryRepository.getLibrariesSimpleView();
    }
}
