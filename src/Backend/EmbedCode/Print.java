/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.EmbedCode;

import Backend.Objects.Variable;
import Backend.SymbolTable.SymbolTable;
import Frontend.MainFrame;

/**
 *
 * @author anclenius
 */
public class Print extends Action{
    
    private String text;
    
    public Print(String text){
        
        this.text = text;
    }
    
    public String getText(){
        return this.text;
    }
    
    public void exec(){
        //HTMLPaint.addText(text);
        
        String aux = this.text;
        for (Variable variable : SymbolTable.getSymbolTable()) {
            if(text.contains(variable.getName())){
                
                text = text.replace(variable.getName(), variable.getValue().toString());
            }
        }
        text = text.replace("'", "");
        text = text.replace(",", " ");
        
        System.out.println("Texto de salida: " + "'" + text + "'");
        //this.text = aux;
        //MainFrame.newText = MainFrame.newText + "'" + text + "'" + " ";
        MainFrame.newText = MainFrame.newText  + text + " ";
        
    }
}
