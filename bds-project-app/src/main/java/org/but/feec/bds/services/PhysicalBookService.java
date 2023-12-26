package org.but.feec.bds.services;

import javafx.util.Pair;
import org.but.feec.bds.api.PhysicalBookDetailedView;
import org.but.feec.bds.api.PhysicalBookSimpleView;
import org.but.feec.bds.data.PhysicalBookRepository;
import org.but.feec.bds.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public class PhysicalBookService {
    private PhysicalBookRepository physicalBookRepository;

    public PhysicalBookService(PhysicalBookRepository physicalBookRepository) {
        this.physicalBookRepository = physicalBookRepository;
    }

    public List<PhysicalBookSimpleView> getPhysicalBooksSimpleView() {
        return physicalBookRepository.getPhysicalBooksSimpleView();
    }

    public PhysicalBookDetailedView getPhysicalBookDetailedViewById(Long id) {
        PhysicalBookDetailedView physicalBookDetailedView = physicalBookRepository.getPhysicalBookDetailedViewById(id);
        if (physicalBookDetailedView == null) {
            throw new ResourceNotFoundException("Provided id was not found.");
        }
        return physicalBookDetailedView;
    }

    public Long getPhysicalBookLibraryId(Long id) {
        Optional<Long> optionalLibraryId = physicalBookRepository.getPhysicalBookLibraryId(id);
        if (optionalLibraryId == null) {
            throw new ResourceNotFoundException("Provided id was not found.");
        }
        if (optionalLibraryId.isEmpty()) {
            return null;
        }
        return optionalLibraryId.get();
    }

    public boolean isPhysicalBookLoaned(Long id) {
        Optional<Long> optionalLibraryId = physicalBookRepository.getPhysicalBookLibraryId(id);
        if (optionalLibraryId == null) {
            throw new ResourceNotFoundException("Provided id was not found.");
        }
        return optionalLibraryId.isEmpty();
    }
}
