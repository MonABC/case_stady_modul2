package com.company.view;

import java.util.Scanner;

public class ManagementMenu {
    private Scanner scanner = new Scanner(System.in);

    public void runManagenmentMenu() {
        int choice = -1;
        MenuBook menuBook = new MenuBook();
        MenuKhachHang menuKhachHang = new MenuKhachHang();
        do {
            System.out.println("---QUẢN LÍ TỔNG---");
            System.out.println("1. QUẢN LÍ SÁCH");
            System.out.println("2. QUẢN LÍ KHÁCH HÀNG");
            System.out.println("0. THOÁT");
            System.out.println("---mời nhập lựa chọn của bạn---");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    menuBook.runBook();
                    break;
                }
                case 2: {
                    menuKhachHang.runKhachHang();
                    break;
                }
            }

        } while (choice != 0);
    }
}
