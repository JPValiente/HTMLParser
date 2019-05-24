/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.EmbedCode;

import Backend.Objects.Variable;
import Backend.SymbolTable.SymbolTable;
import Exceptions.IDNotFound;
import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptException;

/**
 *
 * @author anclenius
 */
public class For extends Action{
    private List<Action> actions = new ArrayList<>();
    private Variable i;
    private String nombreI;
    private int init;
    private int con;
    private int noTimes;
    private int comienzo;
    
    public For(List<Action> acciones, String i,int con,int comienzo){
        this.actions = acciones;
        this.nombreI = i;
        this.con = con;
        this.noTimes = con - init + 1;
        this.comienzo = comienzo;
    }
    
    public void iterate(){
        int iterator = Integer.parseInt(i.getValue().toString());
        iterator++;
        i.setValue(iterator);
    }
    
    public boolean compare(){
        int iterator = Integer.parseInt(i.getValue().toString());
        if(iterator <=   con){
            
            return true;
        }
        return false;
    }
    
    public void exec() throws InterruptedException, ScriptException{
        i = SymbolTable.getSymbol(nombreI);
        SymbolTable.changeSymbolValue(i.getName(), comienzo);
        if(i != null){
           while(compare()){
               System.out.println("\n\n\nNuevo ciclo");
                for (Action action : actions) {
                    
                    Action.doActions(action);
                }
                iterate();
            } 
        } else {
            throw new IDNotFound(nombreI);
        }
        
    }
    

    
    
    
}
