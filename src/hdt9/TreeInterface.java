/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hdt9;

/**
 *
 * @author javia
 */
public interface TreeInterface {
    
    public void insertar(NodoRedBlack<Association <String,String>> nodo);
    public void insertar(String x);
    
    public String Buscar(String palabra, NodoRedBlack<Association <String,String>> nodo);
    public Association<String,String> Buscar(String x);
}
