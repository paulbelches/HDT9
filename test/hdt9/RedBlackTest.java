/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hdt9;

import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author paulb
 */
public class RedBlackTest {
    
    public RedBlackTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    @Test
     public void testInsertar()  {
        System.out.println("insertar");
        NodoRedBlack<Association<String, String>> nodo = new NodoRedBlack("hi, hola");
        RedBlack instance = new RedBlack();
        instance.insertar(nodo);
        assertNotEquals(null, instance.getRaiz());
    }
    @Test
    public void testBuscar()  {
        System.out.println("Buscar");
        RedBlack instance = new RedBlack();
        NodoRedBlack<Association<String, String>> nodo = new NodoRedBlack("hi, hola");
        instance.insertar(nodo);
        nodo = new NodoRedBlack("hi, hola");
        instance.insertar(nodo);
        nodo = new NodoRedBlack("duck, pato");
        instance.insertar(nodo);
        nodo = new NodoRedBlack("dog, perro");
        instance.insertar(nodo);
        nodo = new NodoRedBlack("two, dos");
        instance.insertar(nodo);
        nodo = new NodoRedBlack("game, juego");
        instance.insertar(nodo);
        boolean expResult = true;
        boolean result = instance.Buscar("game", nodo).equals(" juego");
        assertEquals(expResult, result);
    } 
    
}
