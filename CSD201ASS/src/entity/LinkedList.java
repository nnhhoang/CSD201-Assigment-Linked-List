/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import constant.Constaint;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;

/**
 *
 * @author admin
 */
public class LinkedList<T> {

    public Node<T> head, tail;

    public LinkedList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = tail = null;
    }

    public void addLast(T value) {
        Node p = new Node(value);
        if (isEmpty()) {
            head = tail = p;
            return;
        }
        tail.next = p;
        tail = p;
    }

    public void addAfter(int pos, T value) {
        Node p = new Node(value);
        Node pre = getNode(pos);
        if (pre == null) {
            return;
        }
        if (pre == tail) {
            pre.next = p;
            tail = p;
        } else {
            Node pNext = pre.next;
            pre.next = p;
            p.next = pNext;
        }
    }

    public void addFirst(T value) {
        Node p = new Node(value);
        p.next = head;
        head = p;
    }

    public void swap(Node pi, Node pj) {
        T value = (T) pj.value;
        pj.value = pi.value;
        pi.value = value;
    }

    //reader , book list
    public void sort() {
        Node<T> pi, pj;
        pi = head;
        while (pi != null) {
            pj = pi.next;
            while (pj != null) {
                if (pi.value instanceof Book) {
                    Book b1 = (Book) pi.value;
                    Book b2 = (Book) pj.value;
                    if (b1.getBcode().compareTo(b2.getBcode()) < 0) {
                        swap(pi, pj);
                    }
                } else {
                    Reader b1 = (Reader) pi.value;
                    Reader b2 = (Reader) pj.value;
                    if (b1.getRcode().compareTo(b2.getRcode()) < 0) {
                        swap(pi, pj);
                    }
                }
                pj = pj.next;
            }
            pi = pi.next;
        }
    }

    public void sort1() {
        Node<T> pi, pj;
        pi = head;
        while (pi != null) {
            pj = pi.next;
            while (pj != null) {

                Book b1 = (Book) pi.value;
                Book b2 = (Book) pj.value;
                if (b1.getQuantity() > b2.getQuantity()) {
                    swap(pi, pj);
                }
                pj = pj.next;
            }
            pi = pi.next;
        }
    }

    public void sortLending() {
        Node<T> pi, pj;
        pi = head;
        while (pi != null) {
            pj = pi.next;
            while (pj != null) {
                if (pi.value instanceof Lending) {
                    Lending b1 = (Lending) pi.value;
                    Lending b2 = (Lending) pj.value;
                    if (b1.getBcode().compareTo(b2.getBcode()) < 0) {
                        swap(pi, pj);
                    } else if (b1.getBcode().compareToIgnoreCase(b2.getBcode()) == 0
                            && b1.getRcode().compareToIgnoreCase(b2.getRcode()) < 0) {
                        swap(pi, pj);
                    }
                }
                pj = pj.next;
            }
            pi = pi.next;
        }
    }

    public void addMany(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            addLast(arr[i]);
        }
    }

    public int delete(Node q) {
        if (q == null || isEmpty()) {
            return Constaint.SEARCH_FAIL;
        }
        Node f, p;
        f = null;
        p = head;
        while (p != null) {
            if (p == q) {
                break;
            }
            f = p;
            p = p.next;
        }
        if (p == null) {
            return Constaint.SEARCH_FAIL;
        }
        if (f == null)//q=head 
        {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            return Constaint.SEARCH_FAIL;
        }
        f.next = p.next;
        if (f.next == null) {
            tail = f;
        }
        return Constaint.SEARCH_SUCCESS;
    }

    public Node<T> searchByCode(String code) {
        Node<T> pi = head;
        while (pi != null) {
            T value = pi.value;
            if ((value instanceof Book && ((Book) pi.value).getBcode().trim().equalsIgnoreCase(code.trim()))
                    || (value instanceof Reader && ((Reader) pi.value).getRcode().trim().equalsIgnoreCase(code.trim()))) {
                return pi;
            }
            pi = pi.next;
        }
        return null;
    }

    public Node<T> searchBy2Code(String bcode, String rcode) {
        Node<T> p = head;
        while (p != null) {
            T value = p.value;
            if (value instanceof Lending) {
                Lending lending = (Lending) value;
                if (lending.getBcode().equalsIgnoreCase(bcode) && lending.getRcode().equalsIgnoreCase(rcode)) {
                    return p;
                }
            }
            p = p.next;
        }
        return null;
    }

    public int getLength() {
        int len = 0;
        Node<T> p = head;
        while (p != null) {
            p = p.next;
            len++;
        }
        return len;
    }

    public Node getNode(int pos) {
        int len = 0;
        Node<T> p = head;
        while (p != null) {
            if (len == pos) {
                return p;
            }
            p = p.next;
            len++;
        }
        return null;
    }

    public int deleteByCode(String code) {
        Node<T> p = searchByCode(code);
        return delete(p);
    }

    public void visit(Node p) {
        System.out.println(p.value.toString() + "  ");
    }

    public void traverse() {

        Node p = head;
        while (p != null) {
            visit(p);
            p = p.next;
        }
    }

    public void loadFromFile(String fname, int type) throws Exception {
        RandomAccessFile f = new RandomAccessFile(fname, "r");

        StringTokenizer t;
        String s, s1, s2;
        int k;
        clear();
        while (true) {
            s = f.readLine();
            if (s == null) {
                break;
            }
            if (s.trim().length() < 5) {
                continue;
            }
            t = new StringTokenizer(s, "|");

//B01 | Morning | 12 | 0 | 0.0
//5 token : B01, Moring, 12, 0 , 0.0
            if (type == Constaint.INPUT_BOOK) {
                String bcode = t.nextToken().trim();
                String title = t.nextToken().trim();
                int quantity = Integer.parseInt(t.nextToken().trim());
                int lended = Integer.parseInt(t.nextToken().trim());
                double price = Double.parseDouble(t.nextToken().trim());
                Book book = new Book(bcode, title, quantity, lended, price);
                addLast((T) book);

            } else {
                String rcode = t.nextToken();
                String name = t.nextToken();
                int byear = Integer.parseInt(t.nextToken().trim());
                Reader reader = new Reader(rcode, name, byear);
                addLast((T) reader);
            }
        }
        f.close();
    }

    public void saveToFile(String fname) throws Exception {
        File f = new File(fname);
        if (f.exists()) {
            f.delete();
        }
        RandomAccessFile g = new RandomAccessFile(fname, "rw");
        Node p = head;
        while (p != null) {
            g.writeBytes(p.value.toString() + "\n");
            p = p.next;
        }
        g.close();
    }

    public static void main(String[] args) throws Exception {
        LinkedList<Book> bookList = new LinkedList();
        LinkedList<Reader> readerList = new LinkedList();
        bookList.loadFromFile("book.txt", Constaint.INPUT_BOOK);
        bookList.traverse();
    }
}
