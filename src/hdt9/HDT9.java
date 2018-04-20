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
import java.util.TreeMap;

/**
 * Main del programa
 * 
 * @author Paul Belches - 17088
 * @author Javier Anlue - 17149
 */
public class HDT9 {

	/**
	 * @param args
	 *            the command line arguments
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Diccionario diccionario = new Diccionario();
		Map<String, String> mapa = diccionario.coleccion(new TreeMap<String, String>(), diccionario.getRaiz());
		// Coleccion
		for (Map.Entry<String, String> entry : mapa.entrySet()) {
			System.out.println("(" + entry.getKey() + "," + entry.getValue() + ")");
		}

		File f;
		FileReader fr;
		BufferedReader br;
		String text = "";
		try {
			f = new File("./text.txt");
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String linea;
			while (br.ready()) {
				linea = br.readLine();
				text = text + linea;

			}
                        text.toLowerCase();
                        String[] words = text.split(" ");
                        String translation = "";
                        for (int i = 0; i < words.length; i++) {
                            if (words[i].contains(".")) {
                               	String[] lastword = words[i].split(".");
				translation = translation + diccionario.Buscar(lastword[i], diccionario.getRaiz()) + ". ";
                            } else {
				translation = translation + diccionario.Buscar(words[i], diccionario.getRaiz()) + " ";
                            }
                        }

		System.out.println(translation);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Se produjo un error:" + e);
		}
                

	}

}

