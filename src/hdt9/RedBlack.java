/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hdt9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author paulb
 */
public class RedBlack implements TreeInterface{
    private NodoRedBlack<Association<String,String>> raiz;
    public RedBlack() throws FileNotFoundException {
        raiz = null;
	llenar("./diccionario.txt");
    }
    /**
    *
    * @return
    */
    public NodoRedBlack<Association <String,String>> getRaiz() {
	return raiz;
    }
    /**
     *
     * @param raiz
     */
    public void setRaiz(NodoRedBlack<Association <String,String>> raiz) {
    	this.raiz = raiz;
    }

    /**
     * Método para insetar
     * 
     * @param valor
     * @param nodo
     * @return
     */
    public void insertar(NodoRedBlack<Association <String,String>> nodo) {
	NodoRedBlack y = null;
        NodoRedBlack<Association <String,String>> x = raiz;
        while (x!=null){
            y = x;
            if (nodo.getValor().key.compareTo(x.getValor().key) == -1)
                x = x.getIzquierda();
            else 
                x = x.getDerecha();    
        }
        nodo.setParent(y);
        if (y == null){
            raiz = nodo;
        } else {
            if (nodo.getValor().key.compareTo(x.getValor().key) == -1) {
                y.setIzquierda(nodo);
            } else {
                y.setDerecha(nodo);
            }
        }
        nodo.setColor(true);
        arreglar(nodo);
    }
    public void rotacionIzquierda(NodoRedBlack x) {
        NodoRedBlack y = x.getDerecha();
        x.setDerecha(y.getIzquierda());
        if (y.getIzquierda() != null)
            y.getIzquierda().setParent(x);
        y.setParent(x.getParent());
        if (x.getParent() == null){
            raiz = y;
        } else {
          if (x == x.getParent().getIzquierda()) 
              x.getParent().setIzquierda(y);
          else
              x.getParent().setDerecha(y);
        }
        y.setIzquierda(x);
        x.setParent(y);   
    }
    public void rotacionDerecha(NodoRedBlack x) {
        NodoRedBlack y = x.getIzquierda();
        x.setIzquierda(y.getDerecha());
        if (y.getDerecha() != null)
            y.getDerecha().setParent(x);
        y.setParent(x.getParent());
        if (x.getParent() == null){
            raiz = y;
        } else {
          if (x == x.getParent().getDerecha()) 
              x.getParent().setDerecha(y);
          else
              x.getParent().setIzquierda(y);
        }
        y.setDerecha(x);
        x.setParent(y);   
    }
    public void arreglar(NodoRedBlack<Association <String,String>> z){
        NodoRedBlack y = null;
        while (z.getParent().isColor()){
            if (z.getParent().equals(z.getParent().getParent().getIzquierda())){
                y = z.getParent().getParent().getDerecha();
                if (y.isColor()){
                    z.getParent().setColor(false);
                    y.setColor(false);
                    z.getParent().getParent().setColor(true);
                    z = z.getParent().getParent();
                }
                else{ 
                    if (z.equals(z.getParent().getDerecha())){
                        z = z.getParent();
                        rotacionIzquierda(z);
                    }
                    z.getParent().setColor(false);
                    z.getParent().getParent().setColor(true);
                    rotacionDerecha(z);
                }
            } else {
               y = z.getParent().getParent().getIzquierda();
                if (y.isColor()){
                    z.getParent().setColor(false);
                    y.setColor(false);
                    z.getParent().getParent().setColor(true);
                    z = z.getParent().getParent();
                }
                else{ 
                    if (z.equals(z.getParent().getDerecha())){
                        z = z.getParent();
                        rotacionDerecha(z);
                    }
                    z.getParent().setColor(false);
                    z.getParent().getParent().setColor(true);
                    rotacionIzquierda(z);
                } 
            }
        }
    }

/**
 * Método para buscar
 *
 * @param palabra
 * @param nodo
 * @return
 */
public String Buscar(String palabra, NodoRedBlack<Association <String,String>> nodo) {
	String s = nodo.getValor().key.toString();
	if (s.compareTo(palabra) == 0) {
		return nodo.getValor().value.toString();
	} else {
		if ((s.compareTo(palabra) < 0) && (nodo.getDerecha() != null)) {
			return Buscar(palabra, nodo.getDerecha());
		} else if (nodo.getIzquierda() != null) {
			return Buscar(palabra, nodo.getIzquierda());
		} else
			return "*" + palabra + "*";
	}
}

/**
 * Método que devuelve la colecion
 *
 * @param mapa
 * @param nodo
 * @return
 */
public Map<String, String> coleccion(Map<String, String> mapa, NodoRedBlack<Association <String, String>> nodo) {
	if (nodo.getIzquierda() != null)
		mapa = coleccion(mapa, nodo.getIzquierda());
	mapa.put(nodo.getValor().getKey().toString(), nodo.getValor().getValue().toString());
	if (nodo.getDerecha() != null)
		mapa = coleccion(mapa, nodo.getDerecha());
	return mapa;
}


   public void  llenar(String linea) throws FileNotFoundException {
       File f = new File("./diccionario.txt");
       FileReader fr = new FileReader(f);
       BufferedReader br = new BufferedReader (fr);
       NodoRedBlack<Association<String,String>> nodo = null;
       try {
            while ((linea = br.readLine()) != null) {
		linea = linea.substring(linea.indexOf('(') + 1, linea.indexOf(')'));
                nodo = new NodoRedBlack(linea);
            }
        br.close();
	fr.close();
	}
	// Si el archivo se modifica manualmente o sucede cualquier otros tipo de
	// error, este sera comunicado con el usuario
	catch (Exception e) {
			System.err.println("Se produjo un error:" + e);
		}
    }

    public void insertar(Association<String, String> x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Association<String,String> Buscar(String x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertar(String x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
