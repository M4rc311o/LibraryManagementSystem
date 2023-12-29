package org.but.feec.bds.api;

import java.time.LocalDate;

public class LoanBookCreateView {
    private Long userId;
    private Long physicalBookId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private Long library;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPhysicalBookId() {
        return physicalBookId;
    }

    public void setPhysicalBookId(Long physicalBookId) {
        this.physicalBookId = physicalBookId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Long getLibrary() {
        return library;
    }

    public void setLibraryId(Long library) {
        this.library = library;
    }
}
