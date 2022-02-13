package com.company;

import java.io.IOException;

public interface GenericManagement<T> {

    public int size();

    public int findById(String id);

    public void showAll();

    //2.1 thêm phần tử mới vào cuối
    public void addNew(T t) throws IOException;


    //2.2 thêm vào theo vị trí
    public void addNewByIndex(int index, T t);

    //3. sửa thông tin sách
    // 3.1 theo vị trí
    public void updateByIndex(int index, T t);

    //3.2 theo id
    public void updateById(String id, T t);

    //4. xóa
    //4.1 xóa theo vị trí
    public void deleteByIndex(int index);

    //4.2 xóa theo Id
    public boolean deleteById(String id);

    //5. tìm kiếm
    //5.1 tìm kiếm theo phương pháp tuyến tính
    public T getOb(int index);

    //5.2 tìm kiếm theo phương pháp không đệ quy
    public int findObUsingBinarySearch(String id);

    //6. sắp xếp
    //6.1 sắp xếp nổi bọt
    public void bubbleSort();

    //6.2 sắp xếp chọn
    public void selectionSort();

    //6.2 sắp xép chèn
    public void insertionSort();


}
