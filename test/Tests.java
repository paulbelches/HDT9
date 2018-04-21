package hdt9;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

/**
 * Pruebas unitarias
 * 
 * @author Javier Anleu - 17149
 * @author Paul Belches - 17088
 *
 */
public class Tests {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBuscar() throws FileNotFoundException {
		Diccionario diccionario = new Diccionario();
		String[] words = new String[6];
		words[0] = "house";
		words[1] = "dog";
		words[2] = "woman";
		words[3] = "homework";
		words[4] = "town";
		words[5] = "yes";
		String[] results = new String[6];
		for (int i = 0; i < words.length; i++) {
			results[i] = diccionario.Buscar(words[i], diccionario.getRaiz());
		}
		String[] expected = new String[6];
		expected[0] = "casa";
		expected[1] = "perro";
		expected[2] = "mujer";
		expected[3] = "tarea";
		expected[4] = "pueblo";
		expected[5] = "si";
		assertArrayEquals(expected, results);
	}

	@Test
	public void testInsert() throws FileNotFoundException {
		Diccionario diccionario = new Diccionario();
		@SuppressWarnings("unused")
		String test = "";
		String valor = "test";
		diccionario.insertar(valor, diccionario.getRaiz());
		String result = (String) diccionario.getRaiz().getValor().getKey();
		assertEquals("intento", valor, result);

	}

}