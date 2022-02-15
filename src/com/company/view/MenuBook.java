package com.company.view;

import com.company.controller.BookManagement;
import com.company.model.Book;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MenuBook {
    public  final String ANSI_RESET = "\u001B[0m";
    public  final String ANSI_YELLOW = "\u001B[33m";
    public  final String ANSI_BLUE = "\u001B[34m";
    public  final String ANSI_PURPLE = "\u001B[35m";
    public  final String ANSI_GREEN = "\u001B[32m";
    public Scanner scanner = new Scanner(System.in);

    public void runBook() {
        BookManagement bookManagement = new BookManagement();
        int choice = -1;
        do {
            menu();
            choice = scanner.nextInt();

// đọc file
            checkAndReadFileBook(bookManagement);
            switch (choice) {


                case 1: {
                    DisplayAllBook(bookManagement);
                    break;
                }
                case 2: {
                    addNewBook(bookManagement);
                    break;
                }
                case 3: {
                    fixBook(bookManagement);
                    break;
                }
                case 4: {
                    deleteBook(bookManagement);
                    break;
                }
                case 5: {
                    searchBook(bookManagement);
                    break;
                }
                case 6: {
                    sortBook(bookManagement);
                    break;
                }
            }
// ghi file
            writeFileBook(bookManagement);
        } while (choice != 0);
    }

    private void writeFileBook(BookManagement bookManagement) {
        try {
            bookManagement.writeFile("book.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sortBook(BookManagement bookManagement) {
        int choice6 = -1;
        do {
            munuSort();
            choice6 = scanner.nextInt();
            switch (choice6) {
                case 1: {
                    System.out.println("sắp xếp theo id của Sách");// sắp xếp nổi bọt
                    bookManagement.bubbleSort();
                    System.out.println(ANSI_YELLOW + "xắp xếp thành công" +ANSI_RESET);
                    break;
                }
                case 2: {
                    System.out.println("sắp xếp chọn theo tên của Sách");// sắp xếp chọn
                    bookManagement.selectionSort();
                    System.out.println(ANSI_YELLOW + "xắp xếp thành công" + ANSI_RESET);
                    break;
                }
                case 3: {
                    System.out.println("sắp xếp theo giá của Sách");//sắp xếp chèn
                    bookManagement.insertionSort();
                    System.out.println(ANSI_YELLOW + "xắp xếp thành công"+ ANSI_RESET);
                    break;
                }
            }
        } while (choice6 != 0);
    }

    private void deleteBook(BookManagement bookManagement) {
        int choice4 = -1;
        do {
            menuDelete();
            choice4 = scanner.nextInt();
            switch (choice4) {
                case 1: {
                    deleteBookByIndex(bookManagement);
                    break;
                }
                case 2: {
                    deleteBookById(bookManagement);
                    break;
                }
            }

        } while (choice4 != 0);
    }

    private void searchBook(BookManagement bookManagement) {
        int choice5 = -1;
        do {
            menuSearchBook();
            choice5 = scanner.nextInt();
            switch (choice5) {
                case 1: {
                    System.out.println("mời nhập ID Sách cần tìm");
                    scanner.nextLine();
                    String id = scanner.nextLine();
                    int index = bookManagement.findById(id);
                    if (index != -1) {
                        System.out.println("thông tin Sách theo Id: " + id + " là: ");
                        System.out.println(bookManagement.getOb(index));
                    } else {
                        System.err.println("không tìm thấy Id sách");
                    }
                    break;
                }

                case 2: {
                    System.out.println("mời nhập tên sách cần tìm");
                    scanner.nextLine();
                    String nameBook = scanner.nextLine();
                    int index = bookManagement.findObUsingBinarySearchName(nameBook);
                    if (index != -1) {
                        System.out.println("thông tin sách có tên: " + nameBook + " là: ");
                        System.out.println(bookManagement.getOb(index));
                    } else {
                        System.err.println("không tim thấy tên Sách: " + nameBook + " trong thư viện");
                    }
                    break;
                }
            }

        } while (choice5 != 0);
    }

    private void menuSearchBook() {
        System.out.println("--tìm kiếm Sách--");
        System.out.println("1. tìm kiếm Sách theo ID");// theo phương pháp tuyến tính
        System.out.println("2. tìm kiếm theo tên Sách"); // theo phương pháo nhị phân không đệ quy
        System.out.println("0. thoát khỏi mục tìm kiếm");
        System.out.println("mời nhập lựa chọn phương thức tìm kiếm");
    }

    private void deleteBookById(BookManagement bookManagement) {
        System.out.println("--nhập mã id Sách cần xóa--");
        scanner.nextLine();
        String id = scanner.nextLine();
        boolean isDelete = bookManagement.deleteById(id);
        if (isDelete) {
            System.out.println(ANSI_YELLOW +"xóa thành công"+ ANSI_RESET);
        } else {
            System.err.println(" xóa lỗi do không có id Sách này trong thư viện");
        }
    }

    private void deleteBookByIndex(BookManagement bookManagement) {
        System.out.println(" --nhâp vị trí Sách muốn xóa--");
        int index = scanner.nextInt();
        if (index < 0 || index > bookManagement.size()) {
            System.err.println("vị trí không hợp lệ!!!");
        } else {
            bookManagement.deleteByIndex(index);
            System.out.println(ANSI_YELLOW +"xóa thành công"+ ANSI_RESET);
        }
    }

    private void fixBook(BookManagement bookManagement) {
        int choice3 = -1;
        do {
            menuUpdate();
            choice3 = scanner.nextInt();

            switch (choice3) {

                case 1: {
                    System.out.println("chỉnh sửa thông tin Sách");
                    System.out.println(" mời nhập vị trí cần chỉnh sửa");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    if (index < 0 || index > bookManagement.size() - 1) {
                        System.err.println("vị trí không hợp lệ!!!");
                    } else {
                        Book book = getBook();
                        bookManagement.updateByIndex(index, book);
                        System.out.println(ANSI_YELLOW +"chỉnh sửa thành công"+ ANSI_RESET);
                    }
                    break;
                }
                case 2: {
                    System.out.println("chỉnh sửa thông tin Sách");
                    System.out.println("nhập id Sách cần chỉnh sửa");
                    scanner.nextLine();
                    String id = scanner.nextLine();
                    int index = bookManagement.findById(id);
                    if (index != -1) {
                        Book book = getBook();
                        bookManagement.updateById(id, book);
                        System.out.println(ANSI_YELLOW +"Cập nhật thành công"+ ANSI_RESET);
                    } else {
                        System.err.println("không tìm thấy id sách!!!");

                    }
                    break;
                }
            }

        } while (choice3 != 0);
    }

    private void addNewBook(BookManagement bookManagement) {
        int choice2 = -1;
        do {
            System.out.println("thêm Sách vào thư viện");
            addBook();
            choice2 = scanner.nextInt();
            scanner.nextLine();
            switch (choice2) {
                case 1: {
                    System.out.println("mục thêm sách vào cuối");
                    Book book = getBook();
                    bookManagement.addNew(book);
                    break;
                }
                case 2: {

                    System.out.println(" mục thêm sách theo vị trí mong muốn");
                    System.out.println(" mời nhập vị trí mong muốn");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    if (index < 0 || index > bookManagement.size()) {
                        System.err.println("vị trí không hợp lệ!!!");
                    } else {
                        Book book = getBook();
                        bookManagement.addNewByIndex(index, book);
                    }
                    break;
                }
            }
        } while (choice2 != 0);
    }

    private void DisplayAllBook(BookManagement bookManagement) {
        int checkSize = bookManagement.size();
        if (checkSize == 0) {
            System.err.println("chưa có thông tin Sách");
        } else {
            System.out.println("danh sách Sách có trong thư viện");
            bookManagement.showAll();
        }
    }

    private void checkAndReadFileBook(BookManagement bookManagement) {
        File file = new File("book.txt");
        if (file.exists()) {
            try {
                bookManagement.readFile("book.txt");
            } catch (IOException | ClassNotFoundException e) {
            }
        }
    }

    private void menuUpdate() {
        System.out.println("--mời lựa chọn cách chỉnh sửa--");
        System.out.println("1. chỉnh sửa thông tin Sách theo vị trí");
        System.out.println("2. chỉnh sửa thông tin Sách theo id");
        System.out.println("0. thoát khỏi mục chỉnh sửa ");
    }

    private void munuSort() {
        System.out.println("--mời lựa chọn cách sắp xếp--");
        System.out.println("1. sắp xếp theo Id Sách");
        System.out.println("2. sắp xếp theo tên Sách");
        System.out.println("3. sắp xếp giá Sách");
        System.out.println("0. thoát khỏi mục sắp xếp");
    }

    private void menuDelete() {
        System.out.println("Xóa Sách");
        System.out.println("mời lựa chọn cách xóa");
        System.out.println("1. xóa theo vị trí");
        System.out.println("2. xóa theo id Sách");
        System.out.println("0. thoát khỏi mục xóa");
    }


    private void addBook() {
        System.out.println("mời lựa chọn cách thêm Sách vào thư viên");
        System.out.println("1. thêm Sách vào cuối");
        System.out.println("2. thêm Sách theo vị trí");
        System.out.println("0. thoát khỏi mục thêm sách");
    }

    private  Book getBook() {

        System.out.println("mời nhập id Sách");
        String idBook = scanner.nextLine();
        System.out.println("mời nhập tên sách: ");
        String nameBook = scanner.nextLine();
        System.out.println("mời nhập loại Sách: ");
        String typeBook = scanner.nextLine();
        System.out.println("mời nhập giá: ");
        double price = scanner.nextDouble();
        Book book = new Book(idBook, nameBook, typeBook, price);
        return book;
    }

    private void menu() {
        System.out.println("---MENU---");
        System.out.println("1. HIỂN THỊ SÁCH CÓ TRONG THƯ VIỆN");
        System.out.println("2. THÊM SÁCH VÀO THƯ VIÊN");
        System.out.println("3. CHỈNH SỬA SÁCH TRONG THƯ VIỆN");
        System.out.println("4. XÓA SÁCH TRONG THƯ VIÊN");
        System.out.println("5. TÌM KIẾM SÁCH");
        System.out.println("6. SẮP XẾP SÁCH TRONG THƯ VIỆN");
        System.out.println("0. THOÁT MỤC QUẢN LÍ SÁCH");
        System.out.println("Mời nhập lựa chọn! ");
    }
}


