/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author admin
 */
public class Node<T> {
    public T value;
    public Node next;

    public Node(T value, Node next) {
        this.value = value;
        this.next = next;
    }
    public Node(T value) {
        this.value = value;
        this.next = null;
    }
    
   
}
