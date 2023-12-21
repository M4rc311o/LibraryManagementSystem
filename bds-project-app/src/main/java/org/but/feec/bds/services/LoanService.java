package org.but.feec.bds.services;

import org.but.feec.bds.api.LoanSimpleView;
import org.but.feec.bds.data.LoanRepository;

import java.util.List;

public class LoanService {
    private LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<LoanSimpleView> getLoansSimpleViewForUser(long userId) {
        return loanRepository.getLoansSimpleViewForUser(userId);
    }
}
