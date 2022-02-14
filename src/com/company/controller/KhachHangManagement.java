package com.company.controller;

import com.company.model.KhachHang;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangManagement implements GenericManagement<KhachHang>, WriteFile, ReadFile {
    List<KhachHang> khachHangs = new ArrayList<>();

    @Override
    public int size() {
        return khachHangs.size();
    }

    @Override
    public int findById(String id) {
        return 0;
    }

    public int findByCmt(int cmt) {
        int index = -1;
        for (int i = 0; i < khachHangs.size(); i++) {
            if (khachHangs.get(i).getCmt() == cmt) {
                return index = i;
            }
        }
        return index;
    }

    @Override
    public void showAll() {
        for (int i = 0; i < khachHangs.size(); i++) {
            System.out.println(khachHangs.get(i));
        }

    }

    @Override
    public void addNew(KhachHang khachHang) {
        khachHangs.add(khachHang);

    }


    @Override
    public void addNewByIndex(int index, KhachHang khachHang) {
        khachHangs.add(index, khachHang);

    }

    @Override
    public void updateByIndex(int index, KhachHang khachHang) {
        khachHangs.set(index, khachHang);
    }

    @Override
    public void updateById(String id, KhachHang khachHang) {

    }

    public void updateByCmt(int cmt, KhachHang khachHang) {
        int index = findByCmt(cmt);
        khachHangs.set(index, khachHang);
    }

    @Override
    public void deleteByIndex(int index) {
        khachHangs.remove(index);
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    public boolean deleteByCmt(int cmt) {
        int index = findByCmt(cmt);
        if (index != -1) {
            khachHangs.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public KhachHang getOb(int index) {
        return khachHangs.get(index);
    }

    @Override
    public int findObUsingBinarySearch(String id) {
        return 0;
    }

    public int findKhachHanhUsingBinarySearch(int cmt) {//bắt buộc phải sắp xếp lại danh sách  trước khi tìm kiếm
        bubbleSort();
        int low = 0;
        int high = khachHangs.size() - 1;
        while (high >= low) {
            int mid = (high + low) / 2;
            if (khachHangs.get(mid).getCmt() == cmt) {
                return mid;
            } else if (khachHangs.get(mid).getCmt() < cmt) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public int findKhachHangUsingByName(String name) {
        bubbleSortByName();
        int low = 0;
        int high = khachHangs.size() - 1;
        while (high >= low) {
            int mid = (high + low) / 2;
            if (khachHangs.get(mid).getTenKhachHang().equals(name)) {
                return mid;
            } else if (khachHangs.get(mid).getTenKhachHang().compareTo(name) > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }


    public void bubbleSortByName() {
        for (int i = 0; i < khachHangs.size(); i++) {
            for (int j = khachHangs.size() - 1; j > i; j--) {
                if (khachHangs.get(j).getTenKhachHang().compareTo(khachHangs.get(j - 1).getTenKhachHang()) < 0) {
                    KhachHang temp = khachHangs.get(j);
                    khachHangs.set(j, khachHangs.get(j - 1));
                    khachHangs.set(j - 1, temp);
                }
            }
        }
    }

    //6. sắp xếp theo số Cmt
    //6.1 sắp xếp nổi bọt
    @Override
    public void bubbleSort() {
        for (int i = 0; i < khachHangs.size(); i++) {
            for (int j = khachHangs.size() - 1; j > i; j--) {
                if (khachHangs.get(j).getCmt() < khachHangs.get(j - 1).getCmt()) {
                    KhachHang temp = khachHangs.get(j);
                    khachHangs.set(j, khachHangs.get(j - 1));
                    khachHangs.set(j - 1, temp);
                }
            }
        }

    }

    //6.2 xắp sếp chọn
    @Override
    public void selectionSort() {
        int min;
        for (int i = 0; i < khachHangs.size() - 1; i++) {
            min = i;
            for (int j = i + 1; j < khachHangs.size(); j++) {
                if (khachHangs.get(min).getTenKhachHang().compareTo(khachHangs.get(j).getTenKhachHang()) > 0) {
                    min = j;
                }
            }
            if (min != i) {
                KhachHang temp = khachHangs.get(min);
                khachHangs.set(min, khachHangs.get(i));
                khachHangs.set(i, temp);
            }
        }
    }

    @Override
    public void insertionSort() {
        int pos;
        KhachHang kh;
        for (int i = 0; i < khachHangs.size(); i++) {
            pos = i;
            kh = khachHangs.get(i);
            if (pos > 0 && khachHangs.get(i).getSoNgayThue() < khachHangs.get(pos - 1).getSoNgayThue()) {
                khachHangs.set(pos, khachHangs.get(pos - 1));
                pos--;
            }
            khachHangs.set(pos, kh);
        }
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream inputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        this.khachHangs = (List<KhachHang>) objectInputStream.readObject();
        objectInputStream.close();
        inputStream.close();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream outputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(this.khachHangs);
        objectOutputStream.close();
        outputStream.close();
    }
}

