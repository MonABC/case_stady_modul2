package com.company.view;

import com.company.controller.KhachHangManagement;
import com.company.model.Book;
import com.company.model.KhachHang;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class MenuKhachHang {
    public  final String ANSI_RESET = "\u001B[0m";
    public  final String ANSI_YELLOW = "\u001B[33m";
    public Scanner scanner = new Scanner(System.in);
    public void runKhachHang() {
        KhachHangManagement khachHangManagement = new KhachHangManagement();
        int luaChon = -1;
        do {
            menuKhachHang();
            luaChon = scanner.nextInt();

            //kiểm tra file xem có tồn tại không và đọc file
            checkAndReadFileKhachHang(khachHangManagement);

            switch (luaChon) {
                case 1: {
                    displayAllKhachHang(khachHangManagement);
                    break;
                }
                case 2: {
                    addNewKhachHang(khachHangManagement);

                    break;
                }
                case 3: {
                    fixKhachHang(khachHangManagement);
                    break;
                }
                case 4: {
                    deleteKhachHang(khachHangManagement);
                    break;
                }
                case 5: {
                    searchKhachHang(khachHangManagement);
                    break;
                }
                case 6: {
                    sortKhachHang(khachHangManagement);
                    break;
                }
            }
            //ghi file
            writeFileKhachHang(khachHangManagement);
        } while (luaChon != 0);
    }

    private void writeFileKhachHang(KhachHangManagement khachHangManagement) {
        try {
            khachHangManagement.writeFile("khachHang.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sortKhachHang(KhachHangManagement khachHangManagement) {
        int luaChon6 = -1;
        do {
            meneSapxep();
            luaChon6 = scanner.nextInt();
            switch (luaChon6) {
                case 1: {
                    System.out.println("sắp xếp theo số CMT khách hàng");// sắp xến nổi bọt
                    khachHangManagement.bubbleSort();
                    System.out.println(ANSI_YELLOW +"sắp xếp thành công"+ANSI_RESET);
                    break;
                }
                case 2: {
                    System.out.println("sắp xếp theo tên khách hàng"); //sắp xếp chọn
                    khachHangManagement.selectionSort();
                    System.out.println(ANSI_YELLOW +"sắp xếp thành công"+ANSI_RESET);
                    break;
                }
                case 3: {
                    System.out.println("sắp xếp theo số ngày thuê Sách của khách hàng");// sắp xếp chọn
                    khachHangManagement.insertionSort();
                    System.out.println(ANSI_YELLOW +"sắp xếp thành công"+ANSI_RESET);

                    break;
                }
            }

        } while (luaChon6 != 0);
    }

    private void searchKhachHang(KhachHangManagement khachHangManagement) {
        int luaChon5 = -1;
        do {
            System.out.println("--Tìm kiếm khách hàng--");
            System.out.println("1. tìm kiếm  khách theo CMT");
            System.out.println("2. tìm kiếm khách hàng theo tên");
            System.out.println("0. thoát khỏi mục tìm kiếm");
            System.out.println(" mời nhập lựa chọn phương thức tìm kiếm");
            luaChon5 = scanner.nextInt();
            switch (luaChon5) {
                case 1: {

                    System.out.println("mời nhập số CMT khách hàng cần tìm"); // tìm kiếm tuyến tính
                    int cmt = scanner.nextInt();
                    int index = khachHangManagement.findByCmt(cmt);
                    if (index != -1) {
                        System.out.println("thông tin khách hàng có số CMT: " + cmt + " là :");
                        System.out.println(khachHangManagement.getOb(index));
                    } else {
                        System.out.println("không tìm thấy số CMT vừa nhập");
                    }

                    break;
                }

                case 2: {
                    System.out.println("mời nhập tên khách hàng cần tìm"); //tìm kiếm nhị phân
                    scanner.nextLine();
                    String tenKhachHang = scanner.nextLine();
                    int index = khachHangManagement.findKhachHangUsingByName(tenKhachHang);
                    if (index != -1) {
                        System.out.println("thông tin khách hàng có tên là: " + tenKhachHang + " là: ");
                        System.out.println(khachHangManagement.getOb(index));
                    } else {
                        System.out.println("không tìm thấy tên khách hàng vừa nhập");
                    }
                    break;
                }
            }
        } while (luaChon5 != 0);
    }

    private void deleteKhachHang(KhachHangManagement khachHangManagement) {
        int luaChon4 = -1;
        do {
            menuXoaKhachHang();
            luaChon4 = scanner.nextInt();
            switch (luaChon4) {
                case 1: {
                    System.out.println("nhập vị trí khách hàng muốn xóa");
                    int index = scanner.nextInt();
                    if (index < 0 || index > khachHangManagement.size() - 1) {
                        System.out.println("vị trí nhập không hợp lệ");

                    } else {
                        khachHangManagement.deleteByIndex(index);
                        System.out.println(ANSI_YELLOW +"xóa thành công"+ANSI_RESET);
                    }
                    break;
                }
                case 2: {
                    System.out.println("nhập CMT khách hàng cần xóa");
                    int cmt = scanner.nextInt();
                    boolean xoaKhachHang = khachHangManagement.deleteByCmt(cmt);
                    if (xoaKhachHang) {
                        System.out.println(ANSI_YELLOW +"xóa thành công"+ANSI_RESET);
                    } else {
                        System.out.println("không tồn tại số CMT này");
                    }
                    break;
                }
            }

        } while (luaChon4 != 0);
    }

    private void fixKhachHang(KhachHangManagement khachHangManagement) {
        int luachon3 = -1;
        do {
            menuChinhSuaThongTinKhachHang();
            luachon3 = scanner.nextInt();
            switch (luachon3) {
                case 1: {
                    System.out.println("chỉnh sửa thông tin khách hàng theo vị trí");
                    System.out.println("nhập vị trí cần chỉnh sửa");
                    int index1 = scanner.nextInt();
                    if (index1 < 0 || index1 > khachHangManagement.size() - 1) {
                        System.out.println(" vị trí nhập không hợp lệ");
                    } else {
                        KhachHang khachHang = getKhachHang();
                        khachHangManagement.updateByCmt(index1, khachHang);
                    }
                    break;
                }
                case 2: {
                    System.out.println("---chỉnh sửa thông tin khách hàng theo CMT--");
                    System.out.println("mời nhâp CMT khách hàng: ");
                    int cmt = scanner.nextInt();
                    int index = khachHangManagement.findByCmt(cmt);
                    if (index != -1) {
                        KhachHang khachHang = getKhachHang();
                        khachHangManagement.updateByCmt(cmt, khachHang);
                        System.out.println(ANSI_YELLOW +"chỉnh sửa thành công"+ANSI_RESET);
                    } else {
                        System.out.println(" không tìm thấy CMT khách hàng");
                    }
                    break;
                }
            }
        } while (luachon3 != 0);
    }

    private void displayAllKhachHang(KhachHangManagement khachHangManagement) {
        int kiemTra = khachHangManagement.size();
        if (kiemTra == 0) {
            System.out.println("chưa có thông tin khách hàng");
        } else {
            System.out.println("danh sách khách hàng");
            khachHangManagement.showAll();
        }
    }

    private void addNewKhachHang(KhachHangManagement khachHangManagement) {
        int luaChon2 = -1;
        do {
            System.out.println("thêm khách Hàng");
            System.out.println("1. thêm vào cuối danh sách");
            System.out.println("2. thêm khách hàng theo vị trí mong muốn");
            System.out.println("0. thoát khỏi mục thêm khách hàng");
            System.out.println("mời nhập lựa chọn của bạn");
            luaChon2 = scanner.nextInt();
            switch (luaChon2) {
                case 1: {
                    System.out.println("thêm khách hàng vào cuối danh sách");
                    KhachHang khachHang = getKhachHang();
                    khachHangManagement.addNew(khachHang);
                    break;
                }
                case 2: {
                    System.out.println("thêm khách hàng theo vị trí");
                    System.out.println("mời nhập vị trí");
                    int index = scanner.nextInt();
                    if (index < 0 || index > khachHangManagement.size()) {
                        System.out.println("vị trí không hợp lệ");
                    } else {
                        KhachHang khachHang = getKhachHang();
                        khachHangManagement.addNewByIndex(index, khachHang);
                    }
                    break;
                }
            }
        } while (luaChon2 != 0);
    }

    private void checkAndReadFileKhachHang(KhachHangManagement khachHangManagement) {
        File file = new File("khachHang.txt");
        if (file.exists()) {
            try {
                khachHangManagement.readFile("khachHang.txt");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void menuXoaKhachHang() {
        System.out.println("--Xóa thông tin khách hàng--");
        System.out.println("1. xóa theo vị trí ");
        System.out.println("2. xóa theo số CMT");
        System.out.println("0. thoát khoải mục xóa");
        System.out.println("mời lựa chọn");
    }

    private void meneSapxep() {
        System.out.println("---sắp xếp khách hàng---");
        System.out.println("1. sắp xếp theo số CMT của khách hàng");
        System.out.println("2. sắp xếp theo tên khách hàng");
        System.out.println("3. sắp xếp theo số ngày thuê Sách của khách hàng");
        System.out.println("0. thoát khỏi mục sắp xếp");
        System.out.println("mời lựa chọn");
    }

    private void menuChinhSuaThongTinKhachHang() {
        System.out.println("--chỉnh sửa thông tin khách hàng--");
        System.out.println("1. chỉnh sửa thông tin khách hàng theo vị trí");
        System.out.println("2. chỉnh sửa thông tin khách hàng theo số CMT");
        System.out.println("0. thoát khỏi mục chỉnh sửa thông tin khách hàng");
        System.out.println("--mời nhập lựa chọn cách chỉnh sửa--");
    }

    private KhachHang getKhachHang() {
        System.out.println("mời nhập thông tin khách hàng");
        System.out.println("mời nhập chứng minh thư");
        int cmt = scanner.nextInt();
        System.out.println("mời nhập tên khách hàng");
        scanner.nextLine();
        String tenKhachHang = scanner.nextLine();
        System.out.println("mời nhập số ngày muốn thuê: ");
        int soNgayThue = scanner.nextInt();
        System.out.println("nhập thông tin sách thuê");
        System.out.println("mời nhập id sách thuê: ");
        scanner.nextLine();
        String idBook = scanner.nextLine();
        System.out.println("mời nhập tên sách thuê: ");
        String nameBook = scanner.nextLine();
        System.out.println("mời nhập loại sách thuê: ");
        String typeBook = scanner.nextLine();
        System.out.println("mời nhập giá sách muốn thuê: ");
        double price = scanner.nextDouble();
        Book book = new Book(idBook, nameBook, typeBook, price);

        KhachHang khachHang = new KhachHang(cmt, tenKhachHang, soNgayThue, book);
        return khachHang;
    }

    private void menuKhachHang() {
        System.out.println("---MENU KHÁCH HÀNG---");
        System.out.println("1. HIỂN THỊ DANH SÁCH KHÁCH HÀNG");
        System.out.println("2. THÊM KHÁCH HÀNG");
        System.out.println("3. CHỈNH SỬA THÔNG TIN KHÁCH HÀNG");
        System.out.println("4. XÓA KHÁCH HÀNG");
        System.out.println("5. TÌM KIẾM KHÁCH");
        System.out.println("6. SẮP XẾP KHÁCH HÀNG");
        System.out.println("0. THOÁT KHỎI MỤC QUẢN LÍ KHÁCH HÀNG");
        System.out.println("Mời nhập lựa chọn! ");
    }
}

