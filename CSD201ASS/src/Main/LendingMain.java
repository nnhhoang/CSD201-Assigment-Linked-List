/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import constant.Constaint;
import entity.Book;
import entity.Lending;
import entity.LinkedList;
import entity.Node;
import entity.Reader;
import entity.Validator;

/**
 *
 * @author admin
 */
public class LendingMain {
    private static LinkedList<Reader> readerList;
    private static LinkedList<Book> bookList;
    private static LinkedList<Lending> lendingList;
    public LendingMain(LinkedList<Reader> readerList, LinkedList<Book> bookList, LinkedList<Lending> lendingList) {
        this.readerList = readerList;
        this.bookList = bookList;
        this.lendingList = lendingList;
    }
    
    
    
    public static void menu() {
        System.out.println("********* Process lending list *********\n");
        System.out.println("1.Input data");
        System.out.println("2.Display data");
        System.out.println("3.Sort by bcode and rcode");
        System.out.println("4.Exit\n");
    }
    
    public void run() {
        
        int choice = 0;
        Node<Lending> p;
        Node<Book> b;
        do {
            menu();
            choice = Validator.checkInputIntLimit(1, 4, "Enter your choice: ");
            switch(choice){
                case 1:
                    Validator.getLending(readerList, bookList, lendingList);
                    break;
                case 2: 
                    lendingList.traverse();
                    break;
                case 3:
                    lendingList.sortLending();
                    break;
                    
            }
        }while(choice != 4);
    

    }
   
   
}
