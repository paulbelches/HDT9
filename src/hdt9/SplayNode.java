/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hdt9;

import java.util.regex.Pattern;

public class SplayNode<E> {

    private int key;
    public SplayNode<E> left, right, parent;
    public Association<String, String> valor;

    public SplayNode(String valor) {
        String sep = Pattern.quote(",");
        String[] partes = valor.split(sep);
        this.left = null;
        this.right = null;
        this.parent = null;
        this.valor = new Association<String, String>(partes[0], partes[1]);

    }

    public SplayNode<E> getParent() {
        return parent;
    }

    public void setParent(SplayNode<E> parent) {
        this.parent = parent;
    }

    public SplayNode<E> getLeft() {
        return left;
    }

    public void setLeft(SplayNode<E> left) {
        this.left = left;
    }

    public SplayNode<E> getRight() {
        return right;
    }

    public void setRight(SplayNode<E> right) {
        this.right = right;
    }

    public Association<String, String> getValor() {
        return valor;
    }

    public void setValor(Association<String, String> valor) {
        this.valor = valor;
    }

}
