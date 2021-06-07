/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import constant.Constaint;
import java.util.ArrayList;
import java.util.Scanner;

public class Validator {

    private final static Scanner in = new Scanner(System.in);

    //check user input number limit
    public static int checkInputIntLimit(int min, int max,String message) {
        //loop until user input correct
        while (true) {
            try {
                System.out.println(message);
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in rage [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }
    

    public static double checkInputDoubleLimit(double min, double max, String message) {
        //loop until user input correct
        while (true) {
            try {
                System.out.println(message);
                double result = Double.parseDouble(in.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in range [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }

    //check user input string
    public static String checkInputString(String message) {
        //loop until user input correct
        while (true) {
            System.out.println(message);
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public static Book getBook() {
        String bcode = Validator.checkInputString("Enter bcode: ");
        String title = Validator.checkInputString("Enter title: ");
        int quantity = Validator.checkInputIntLimit(0, Integer.MAX_VALUE, "Enter quantity: ");
        double price = Validator.checkInputDoubleLimit(0, Double.POSITIVE_INFINITY, "Enter price");
        int lended = 0;
        Book book = new Book(bcode, title, quantity, lended, price);
        return book;
    }
    public static Reader getReader() {
        String rcode = Validator.checkInputString("Enter rcode: ");
        String name = Validator.checkInputString("Enter name: ");
        int year = Validator.checkInputIntLimit(1900, 2021, "Enter year: ");
        return new Reader(rcode, name, year);
    }
    public static void getLending(LinkedList<Reader> readerList, LinkedList<Book> bookList, LinkedList<Lending> lendList) {
        String bcode = Validator.checkInputString("Enter bcode: ");
        String rcode = Validator.checkInputString("Enter rcode: ");
        boolean valid = true;
        if(bookList.searchByCode(bcode) == null) {
            System.out.println(bcode + " not found in book list!!!");
            valid = false;
        }
        if(readerList.searchByCode(rcode) == null) {
            System.out.println(rcode + " not found in reader list!!!");
            valid = false;
            
        }
        if(!valid) return;
        
        int state = 0;
        
        Node<Lending> lendNode = lendList.searchBy2Code(bcode, rcode);
        if(lendNode != null) {
            if(lendNode.value.getState() == Constaint.NOT_GIVEN_READER) {
                System.out.println("The book is not given for reader");
            }
            if(lendNode.value.getState() == Constaint.NOT_GIVEN_BACK) {
                System.out.println("The book is not given back");
            }
        }else {
            Book book = bookList.searchByCode(bcode).value;
            if(book.getLended() == book.getQuantity()) {
                System.out.println();
            }else {
                book.setLended(book.getLended() + 1);
                state = 1;
            }
            
        }

        lendList.addLast( new Lending(bcode, rcode, state));
    }

}
