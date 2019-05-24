/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.MarioPaint;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anclenius
 */
public class HTMLPaint {
    private static List<String> outputText = new ArrayList<>();
    
    public static void addText(String text){
        outputText.add(text + "\n");
    }
    
    public static String getAllText(){
        String output = "";
        for (String text : outputText) {
            output = output + text;
        }
        return output;
    }
    
    public static void startActions(){
        
    }
    
    public static void startPainting(){
        
    }
}
