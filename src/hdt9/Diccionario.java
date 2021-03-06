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
import java.util.*;
import java.util.regex.Pattern;

/**
 * @Date 19/03/18
 * @author Javier Anleu
 * @author Paul Belches
 * 
 * 
 */
public class Diccionario {
	private NodoRedBlack<Association<String,String>> raiz;

	public Diccionario() throws FileNotFoundException {
		raiz = llenar("./diccionario.txt");
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
	public NodoRedBlack<Association <String,String>> insertar(String valor, NodoRedBlack<Association <String,String>> nodo) {
		if (nodo == null) {
			nodo = new NodoRedBlack<Association<String,String>>(valor);
		} else {
			String sep = Pattern.quote(",");
			String[] partes2 = valor.split(sep);
			String s = nodo.getValor().key.toString();
			// Revisar si el valor es mayor
			if (s.compareTo(partes2[0]) < 0) {
				nodo.setDerecha(insertar(valor, nodo.getDerecha()));
			} else {
				nodo.setIzquierda(insertar(valor, nodo.getIzquierda()));
			}
		}
		return nodo;
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

	/**Método para llenar el mapa
     *
     * @param cadena
     * @return
	 * @throws FileNotFoundException 
     */
    public NodoRedBlack<Association <String,String>> llenar(String linea) throws FileNotFoundException {
        File f = new File("./diccionario.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader (fr);
        NodoRedBlack<Association<String,String>> nodo = null;
        try {

			while ((linea = br.readLine()) != null) {
				linea = linea.substring(linea.indexOf('(') + 1, linea.indexOf(')'));
				nodo = insertar(linea, nodo);
			}
			br.close();
			fr.close();

		}
		// Si el archivo se modifica manualmente o sucede cualquier otros tipo de
		// error, este sera comunicado con el usuario
		catch (Exception e) {

			System.err.println("Se produjo un error:" + e);

		}
		return nodo;
	}
    
    
    	public SplayNode<Association <String,String>> insertar(String valor, SplayNode<Association <String,String>> nodo) {
		if (nodo == null) {
			nodo = new SplayNode<Association<String,String>>(valor);
		} else {
			String sep = Pattern.quote(",");
			String[] partes2 = valor.split(sep);
			String s = nodo.getValor().key.toString();
			// Revisar si el valor es mayor
			if (s.compareTo(partes2[0]) < 0) {
				nodo.setRight(insertar(valor, nodo.getRight()));
			} else {
				nodo.setLeft(insertar(valor, nodo.getLeft()));
			}
		}
		return nodo;
	}

        	public String Buscar(String palabra, SplayNode<Association <String,String>> nodo) {
		String s = nodo.getValor().key.toString();
		if (s.compareTo(palabra) == 0) {
			return nodo.getValor().value.toString();
		} else {
			if ((s.compareTo(palabra) < 0) && (nodo.getRight() != null)) {
				return Buscar(palabra, nodo.getRight());
			} else if (nodo.getLeft() != null) {
				return Buscar(palabra, nodo.getLeft());
			} else
				return "*" + palabra + "*";
		}
	}
}
