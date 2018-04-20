/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hdt9;

import java.util.regex.Pattern;


/**
 * @Date 19/03/18
 * @author Javier Anleu
 * @author Paul Belches
 * 
 * @param <E>
 */
public class NodoRedBlack<E> {

    private NodoRedBlack<E> izquierda;
    private NodoRedBlack<E> derecha;
    private NodoRedBlack<E> parent;
    private Association<String,String> valor;
    private boolean color;

    /**
     *
     * @param valor
     */
    public NodoRedBlack(String valor) {
        String sep = Pattern.quote(",");
        String[] partes = valor.split(sep);
        this.izquierda = null;
        this.derecha = null;
        this.parent = null;
        this.valor = new Association<String,String>(partes[0], partes[1]);
        this.color = false;  // negro: False, rojo: True
    }

    public NodoRedBlack<E> getParent() {
        return parent;
    }

    public void setParent(NodoRedBlack<E> parent) {
        this.parent = parent;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
    
    /**
     *
     * @return
     */
    public NodoRedBlack<E> getIzquierda() {
        return izquierda;
    }

    /**
     *
     * @param izquierda
     */
    public void setIzquierda(NodoRedBlack<E> izquierda) {
        this.izquierda = izquierda;
    }

    /**
     *
     * @return
     */
    public NodoRedBlack<E> getDerecha() {
        return derecha;
    }

    /**
     *
     * @param derecha
     */
    public void setDerecha(NodoRedBlack<E> derecha) {
        this.derecha = derecha;
    }

    /**
     *
     * @return
     */
    public Association<String,String>  getValor() {
        return valor;
    }

    /**
     *
     * @param valor
     */
    public void setValor(Association<String,String>  valor) {
        this.valor = valor;
    }


}
