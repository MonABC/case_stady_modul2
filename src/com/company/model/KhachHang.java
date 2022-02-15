package com.company.model;

import com.company.model.Book;

import java.io.Serializable;

public class KhachHang implements Serializable {
    private int cmt;
    private String tenKhachHang;
    private int soNgayThue;
    private double soTienTra;
    private Book book;

    public KhachHang() {
    }

    public KhachHang(int cmt, String tenKhachHang, int soNgayThue, Book book) {
        this.cmt = cmt;
        this.tenKhachHang = tenKhachHang;
        this.soNgayThue = soNgayThue;
        this.soTienTra = soNgayThue * 5000;
        this.book = book;
    }

    public int getCmt() {
        return cmt;
    }

    public void setCmt(int cmt) {
        this.cmt = cmt;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public int getSoNgayThue() {
        return soNgayThue;
    }

    public void setSoNgayThue(int soNgayThue) {
        this.soNgayThue = soNgayThue;
    }

    public double getSoTienTra() {
        return soTienTra;
    }

    public void setSoTienTra(double soTienTra) {
        this.soTienTra = soTienTra;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Thông Tin Khách Hàng: " +"số chứng minh thư: "+
                cmt+
                ", "+"tên khách hàng: " + tenKhachHang +
                ", "+"số ngày thuê: " + soNgayThue +
                ", "+"số tiền phải trả: " + soTienTra +
                ", " + book;
    }
}
