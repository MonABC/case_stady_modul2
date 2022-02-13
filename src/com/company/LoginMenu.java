package com.company;

import java.util.Scanner;

public class LoginMenu {
    public Scanner scanner = new Scanner(System.in);
    private final UserManagement userManagement = new UserManagement();
    private ManagementMenu managementMenu = new ManagementMenu();

    public void runUser() {

        int choice = -1;
        do {
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng kí");
            System.out.println("0. Đăng xuất ");
            System.out.println("--mời nhập lựa chọn--");
            choice = scanner.nextInt();

            switch (choice) {
                case 1: {
                    doLogin();
                    break;
                }
                case 2: {

                    User user = getUser();
                    userManagement.addNew(user);
                    break;
                }
            }
        } while (choice != 0);
    }

    private void doLogin() {
        System.out.println("mời nhập tài khoản");
        scanner.nextLine();
        String username = scanner.nextLine();
        System.out.println("mời nhập password");
        String passworrd = scanner.nextLine();
        boolean isLogin = userManagement.checkLoginUsername(username, passworrd);
        if (isLogin == true) {
            System.out.println("đăng nhập thành công");
            managementMenu.runManagenmentMenu();
        } else {
            System.out.println("username hoặc password không đúng");
        }

    }

    private User getUser() {
        System.out.println("Đăng kí tài khoản mới");
        scanner.nextLine();
        String username;
        do {
            System.out.println("nhập tên tài khoản (6-12 kí tự)");
            username = scanner.nextLine();
            if (username.length() < 6) {
                System.err.println("tài khoản phải có it nhất 6 kí tự");
            } else if (username.length() > 12) {
                System.err.println("tài khoản tối đa 12 kí tự");
            } else if (userManagement.checkUsernameExits(username)) {
                System.err.println("tài khoản này đã được đăng kí");
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (username.length() < 6 || username.length() > 12 || userManagement.checkUsernameExits(username));

        String password;
        do {
            System.out.println("nhập password");
            password = scanner.nextLine();
            if (password.length() < 6) {
                System.err.println("password cần tối thiếu 6 kí tự");
            } else if (password.length() > 12) {
                System.err.println("password vượt quá 12 kí tự cho phép");
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (password.length() < 6 || password.length() > 10);

        User user = new User(username, password);
        return user;
    }

}
