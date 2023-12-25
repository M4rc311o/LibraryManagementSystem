package org.but.feec.bds.services;

import org.but.feec.bds.api.PhysicalBookSimpleView;
import org.but.feec.bds.data.PhysicalBookRepository;

import java.util.List;

public class PhysicalBookService {
    private PhysicalBookRepository physicalBookRepository;

    public PhysicalBookService(PhysicalBookRepository physicalBookRepository) {
        this.physicalBookRepository = physicalBookRepository;
    }

    public List<PhysicalBookSimpleView> getPhysicalBooksSimpleView() {
        return physicalBookRepository.getPhysicalBooksSimpleView();
    }
}
