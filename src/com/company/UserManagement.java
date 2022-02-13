package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManagement implements GenericManagement<User>, WriteFile, ReadFile {
    List<User> users = new ArrayList<>();


    public UserManagement() {
        File file = new File("user.txt");
        if (file.exists()) {
            try {
                readFile("user.txt");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int findById(String id) {
        return 0;
    }

    @Override
    public void showAll() {
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Override
    public void addNew(User user) {
        this.users.add(user);
        try {
            writeFile("user.txt");
        } catch (IOException e) {
        }

    }

    @Override
    public void addNewByIndex(int index, User user) {

    }

    @Override
    public void updateByIndex(int index, User user) {

    }

    @Override
    public void updateById(String id, User user) {

    }

    @Override
    public void deleteByIndex(int index) {

    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public User getOb(int index) {
        return null;
    }

    @Override
    public int findObUsingBinarySearch(String id) {
        return 0;
    }

    @Override
    public void bubbleSort() {

    }

    @Override
    public void selectionSort() {

    }

    @Override
    public void insertionSort() {

    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.users = (List<User>) ois.readObject();
        ois.close();
        is.close();

    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.users);
        oos.close();
        os.close();

    }

    public boolean checkUsernameExits(String username) {
        boolean isExisted = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                isExisted = true;
            }
        }
        return isExisted;
    }

    public boolean checkLoginUsername(String username, String password) {// kiểm tra username có tồn tại hay không
        boolean isLogin = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                isLogin = true;
                break;
            }
        }
        return isLogin;

    }
}
