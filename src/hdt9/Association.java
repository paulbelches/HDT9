/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hdt9;

import java.util.Map;

/**
<<<<<<< HEAD
 * Clase Association
 * 
 * @author Paul Belches 17088
 * @author Javier Anleu 17149
 * 
 */
public class Association<K, V> implements Map.Entry<K, V> {
	protected K key;
	protected V value;


	public Association(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		return key; // To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public V getValue() {
		return value; // To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public V setValue(V value) {
		return this.value = value;// To change body of generated methods, choose Tools | Templates.
	}
}
