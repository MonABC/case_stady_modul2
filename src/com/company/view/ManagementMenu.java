package com.company.view;

import java.util.Scanner;

public class ManagementMenu {
    private Scanner scanner = new Scanner(System.in);

    public void runManagenmentMenu() {
        int choice = -1;
        String test = "";
        MenuBook menuBook = new MenuBook();
        MenuKhachHang menuKhachHang = new MenuKhachHang();
        do {
            System.out.println("---QUẢN LÍ TỔNG---");
            System.out.println("1. QUẢN LÍ SÁCH");
            System.out.println("2. QUẢN LÍ KHÁCH HÀNG");
            System.out.println("0. THOÁT");
            System.out.println("---mời nhập lựa chọn của bạn---");
//            choice = scanner.nextInt();
            test = scanner.nextLine();
            try{
                choice = Integer.parseInt(test);
                switch (choice) {
                    case 1: {
                        menuBook.runBook();
                        break;
                    }
                    case 2: {
                        menuKhachHang.runKhachHang();
                        break;
                    }
                    default:{
                        System.err.println("xin nhập đúng các mục có trong menu!!!");
                    }
                }
            }catch (NumberFormatException e){
                System.err.println("sai định dạng!!!");
            }
        } while (choice != 0);
    }
}
