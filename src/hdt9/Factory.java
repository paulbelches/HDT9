/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hdt9;

import java.io.FileNotFoundException;

/**
 *
 * @author javia
 */
public class Factory {
    
    private static final String choice1 ="1";
    private static final String choice2 ="2";
    
    public TreeInterface Factory(String opcion,Association<String,String> association) throws FileNotFoundException{
        if (opcion.equals(choice1)){
            return new SplayTree();
        } else if (opcion.equals(choice2)){
            return new RedBlack();    
    } else {
            return null;
        }
    }
    
}
