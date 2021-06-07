/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import entity.Book;
import entity.Lending;
import entity.LinkedList;
import entity.Reader;
import entity.Validator;

/**
 *
 * @author admin
 */
public class Main {
    static void menu() {
        System.out.println("1.Process book list");
        System.out.println("2.Process reader list");
        System.out.println("3.Process lending list");
        System.out.println("4.Exit");
        
    }
    public static void main(String[] args) {
        LinkedList<Book> bookList = new LinkedList<>();
        LinkedList<Reader> readerList = new LinkedList<>();
        LinkedList<Lending> lendList = new LinkedList<>();
        
        BookMain bookMain = new BookMain(bookList);
        ReaderMain readerMain = new ReaderMain(readerList);
        LendingMain lendingMain = new LendingMain(readerList, bookList, lendList);
        int choice = 0;
        do {            
            menu();
            choice = Validator.checkInputIntLimit(1, 4, "Enter your choice");
            switch(choice){
                case 1:
                    bookMain.run();
                    break;
                case 2:
                    readerMain.run();
                    break;
                case 3:
                    lendingMain.run();
                case 4:
                    return;
            }
        } while (true);
    }
}
