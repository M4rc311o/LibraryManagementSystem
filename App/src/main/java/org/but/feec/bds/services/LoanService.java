package org.but.feec.bds.services;

import org.but.feec.bds.api.LoanBookCreateView;
import org.but.feec.bds.api.LoanSimpleView;
import org.but.feec.bds.api.ReturnBookView;
import org.but.feec.bds.data.LoanRepository;
import org.but.feec.bds.data.PhysicalBookRepository;
import org.but.feec.bds.exceptions.StructureViolationException;

import java.util.List;

public class LoanService {
    private LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<LoanSimpleView> getLoansSimpleViewForUser(long userId) {
        return loanRepository.getLoansSimpleViewForUser(userId);
    }

    public void createLoan(LoanBookCreateView loanBookCreateView) throws StructureViolationException {
        PhysicalBookService physicalBookService = new PhysicalBookService(new PhysicalBookRepository());
        if (physicalBookService.isPhysicalBookLoaned(loanBookCreateView.getPhysicalBookId())) {
            throw new StructureViolationException("Book requested for loan is already loaned.");
        }
        loanRepository.createLoan(loanBookCreateView);
    }

    public void returnBook(ReturnBookView returnBookView) throws StructureViolationException {
        PhysicalBookService physicalBookService = new PhysicalBookService(new PhysicalBookRepository());
        if (!physicalBookService.isPhysicalBookLoaned(returnBookView.getId())) {
            throw new StructureViolationException("Book requested for return is already returned.");
        }
        loanRepository.returnBook(returnBookView);
    }
}
