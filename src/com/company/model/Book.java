package com.company.model;


import java.io.Serializable;

public class Book implements Serializable {
    private String idBook;
    private String nameBook;
    private String typeBook;
    private double priceBook;

    public Book() {
    }

    public Book(String idBook, String nameBook, String typeBook, double priceBook) {
        this.idBook = idBook;
        this.nameBook = nameBook;
        this.typeBook = typeBook;
        this.priceBook = priceBook;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getTypeBook() {
        return typeBook;
    }

    public void setTypeBook(String typeBook) {
        this.typeBook = typeBook;
    }

    public double getPriceBook() {
        return priceBook;
    }

    public void setPriceBook(double priceBook) {
        this.priceBook = priceBook;
    }

    @Override
    public String toString() {
        return "Thông Tin Sách: id sách: " + idBook + ", "+"tên sách: " + nameBook + ", "+"loại sách: " + typeBook + ", "+"giá sách: " + priceBook;
    }
}

