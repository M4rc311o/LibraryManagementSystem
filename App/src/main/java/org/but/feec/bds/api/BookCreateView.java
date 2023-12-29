package org.but.feec.bds.api;

import java.math.BigDecimal;

public class BookCreateView {
    private String isbn;
    private String title;
    private int year;
    private BigDecimal evaluation;
    private String genre;
    private String language;
    private String binding;
    private String literaryPeriod;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(BigDecimal evaluation) {
        this.evaluation = evaluation;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getLiteraryPeriod() {
        return literaryPeriod;
    }

    public void setLiteraryPeriod(String literaryPeriod) {
        this.literaryPeriod = literaryPeriod;
    }
}
