/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import entity.Book;
import constant.Constaint;
import entity.LinkedList;
import entity.Node;
import entity.Validator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class BookMain {
    private static LinkedList<Book> bookList;
    public BookMain(LinkedList<Book> bookList) {
        this.bookList = bookList;
    }

    public LinkedList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(LinkedList<Book> bookList) {
        BookMain.bookList = bookList;
    }
    
    public static void menu() {
        System.out.println("********* Process book list *********\n");
        System.out.println("1.Load data from file");
        System.out.println("2.Input & add data to the end");
        System.out.println("3.Display data");
        System.out.println("4.Save book list to file");
        System.out.println("5.Search by code");
        System.out.println("6.Delete by code");
        System.out.println("7.Sort by code");
        System.out.println("8.Input & add data to the begining");
        System.out.println("9.Add after position k");
        System.out.println("10.Delete position k");
        System.out.println("11.Load dữ liệu từ tệp và traverse.");
        System.out.println("12.Không nhập dữ liệu, thêm bản ghi (6,E,3,2,2) và traverse.");
        System.out.println("13.tìm bản ghi có code = 3, sửa lại price=8 và traverse.");
        System.out.println("14.Xóa bản ghi có code = 2, và traverse.");
        System.out.println("15.Sắp xếp theo quantity và traverse.");
        System.out.println("16.Exit\n");
        
    }
    
    public  void run() {
        int choice = 0;
        Node<Book> p;
        do {
            menu();
            choice = Validator.checkInputIntLimit(0, 16, "Enter your choice: ");
            switch(choice){
                case 1:
                    String filePath = Validator.checkInputString("Enter file path");
                
                    try {
                        bookList.loadFromFile(filePath, Constaint.INPUT_BOOK);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    
                    bookList.addLast(Validator.getBook());
                    break;
                case 3:
                    bookList.traverse();
                    break;
                case 4:
                    String path = Validator.checkInputString("Enter file path: ");
                    try {
                        bookList.saveToFile(path);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case 5:
                    String code = Validator.checkInputString("Enter code to search: ");
                    p = bookList.searchByCode(code);
                    if(p == null) {
                        System.out.println("Not found book have code = " + code);
                    }else {
                        System.out.println(p.value.toString());
                    }
                    break;
                case 6:
                    String delCode = Validator.checkInputString("Enter code to delete: ");
                    p = bookList.searchByCode(delCode);
                    if(p == null){
                        System.out.println("Not found book have code = " + delCode);
                    }else {
                        bookList.delete(p);
                        System.out.println("Delete success");
                    }
                    break;
                case 7:
                    bookList.sort();
                    bookList.traverse();
                case 8: 
                    bookList.addFirst(Validator.getBook());
                    break;
                case 9:
                    int k = Validator.checkInputIntLimit(0, bookList.getLength()-1, "Enter position to add");
                    bookList.addAfter(k, Validator.getBook());
                    break;
                case 10:
                    int pos = Validator.checkInputIntLimit(0, bookList.getLength()-1, "Enter position to delete");
                    bookList.delete(bookList.getNode(pos));
                    break;
                case 11:
                    String filePath1 = "book.txt";
                    try {
                        bookList.loadFromFile(filePath1, 9);
                        bookList.traverse();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                    
                case 12: 
                    bookList.addLast(new Book("6", "E", 3,2,2));
                    bookList.traverse();
                    break;
                case 13: 
                    Node<Book> n= bookList.searchByCode("3");
                    if(n != null)
                        n.value.setPrice(8);
                    bookList.traverse();
                    break;
                case 14:
                    bookList.deleteByCode("2");
                    bookList.traverse();
                    break;
                case 15:
                    bookList.sort1();
                    bookList.traverse();
                    break;
            }
        }while(choice != 16);
    }
}
