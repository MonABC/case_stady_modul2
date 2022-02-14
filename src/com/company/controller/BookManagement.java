package com.company.controller;

import com.company.model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookManagement implements GenericManagement<Book>, WriteFile, ReadFile {

    private List<Book> books = new ArrayList<>();

    public BookManagement() {
//        File file = new File("book1.txt");            ghi vào thế này không hợp lí lắm vì nó chỉ ghi khi tạo mà không ghi khi xóa hay sửa đổi
//        if (file.exists()) {
//            try {
//                readFile("book1.txt");
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
    }

    // lấy dộ dày danh sách
    @Override
    public int size() {
        return books.size();
    }

    // tìm Sách theo id
    @Override
    public int findById(String id) {
        int index = -1;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIdBook().equals(id)) {
                index = i;
            }
        }
        return index;
    }


    public int findByNameBook(String nameBook) {
        int index = -1;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getNameBook().equals(nameBook)) {
                index = i;
            }
        }
        return index;
    }


    //1. hiển thị Sách trong thư viện
    @Override
    public void showAll() {
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
    }

    //2. thêm Sách vào thư viện
    //2.1 thêm sách vào cuối
    @Override
    public void addNew(Book book) {
        books.add(book);
    }

    //2.2 thêm Sách vào vị trí
    @Override
    public void addNewByIndex(int index, Book book) {
        books.add(index, book);
    }

    //3. sửa thông tin Sách
    //3.1 sửa thông tin theo vị trí
    @Override
    public void updateByIndex(int index, Book book) {
        books.set(index, book);
    }

    //3.2 sửa thông tin sách theo id
    @Override
    public void updateById(String id, Book book) {
        int index = findById(id);
        books.set(index, book);
    }

    //4. xóa Sách trong thư viện
    //4.1 xóa theo vị trí
    @Override
    public void deleteByIndex(int index) {
        books.remove(index);
    }

    //4.2 xóa theo id Sách
    @Override
    public boolean deleteById(String id) {
        int index = findById(id);
        if (index != -1) {
            books.remove(index);
            return true;
        }
        return false;
    }

    //5. tìm kiếm Sách\
    //5.1 Tìm kiếm tuyến tính
    @Override
    public Book getOb(int index) {
        return books.get(index);
    }

    //5.2 tìm kiếm nhị phân
    @Override
    public int findObUsingBinarySearch(String id) {// chú ý trước khí tìm kiếm phái sắp xếp lại danh sách theo kiểu tìm kiếm
        bubbleSort();// sắp xếp lại cho danh sách
        int low = 0;
        int high = books.size() - 1;
        while (high >= low) {
            int mid = (high + low) / 2;
            if (books.get(mid).getIdBook().equals(id)) {
                return mid;
            } else if (books.get(mid).getIdBook().compareTo(id) > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public int findObUsingBinarySearchName(String nameBook) {
        bubbleSortName();
        int low = 0;
        int high = books.size() - 1;
        while (high >= low) {
            int mid = (high + low) / 2;
            if (books.get(mid).getNameBook().equals(nameBook)) {
                return mid;
            } else if (books.get(mid).getNameBook().compareTo(nameBook) > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public void bubbleSortName() {
        for (int i = 0; i < books.size(); i++) {
            for (int j = books.size() - 1; j > i; j--) {
                if (books.get(j).getNameBook().compareTo(books.get(j - 1).getNameBook()) < 0) {
                    Book temp = books.get(j);
                    books.set(j, books.get(j - 1));
                    books.set(j - 1, temp);
                }
            }
        }
    }


    //6. Sắp xếp
    //6.1 sắp xếp nổi bọt theo id
    @Override
    public void bubbleSort() {
        for (int i = 0; i < books.size(); i++) {
            for (int j = books.size() - 1; j > i; j--) {
                if (books.get(j).getIdBook().compareTo(books.get(j - 1).getIdBook()) < 0) {
                    Book temp = books.get(j);
                    books.set(j, books.get(j - 1));
                    books.set(j - 1, temp);
                }
            }
        }

    }

    //6.2 sắp xếp chọn theo id
    @Override
    public void selectionSort() {
        int min;
        for (int i = 0; i < books.size() - 1; i++) {
            min = i;
            for (int j = i + 1; j < books.size(); j++) {
                if (books.get(min).getNameBook().compareTo(books.get(j).getNameBook()) > 0) {
                    min = j;
                }
            }
            if (min != i) {
                Book temp = books.get(min);
                books.set(min, books.get(i));
                books.set(i, temp);
            }
        }
    }

    //6.3 sắp xếp chèn theo id
    @Override
    public void insertionSort() {
        int pos;
        Book b;
        for (int i = 0; i < books.size(); i++) {
            pos = i;
            b = books.get(i);
            while (pos > 0 && b.getPriceBook() < books.get(pos - 1).getPriceBook()) {
                books.set(pos, books.get(pos - 1));
                pos--;
            }
            books.set(pos, b);
        }

    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.books = (List<Book>) ois.readObject();
        ois.close();
        is.close();

    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.books);
        oos.close();
        os.close();
    }
}
