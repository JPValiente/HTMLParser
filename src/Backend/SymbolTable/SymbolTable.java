/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.SymbolTable;

import Backend.Objects.Variable;
import Exceptions.IDNotFound;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anclenius
 */
public class SymbolTable {
    private static List<Variable> SymbolTable = new ArrayList<>();
    
    public static String getSymbolValue(String name){
        for (Variable variable : SymbolTable) {
            if(variable.getName().equals(name)){
                return variable.getValue();
            }
        }
        return null;
    }
    
    public static String getSymbolValue(int id){
        for (Variable variable : SymbolTable) {
            if(variable.getId() == id){
                return variable.getValue();
            }
        }
        return null;
    }
    
    public static Variable getSymbol(String name){
        for (Variable variable : SymbolTable) {
            if(variable.getName().equals(name)){
                return variable;
            }
        }
        return null;
    }
    
    
    
    public static void setSymbol(String name, String type, String value){
        if(!name.equals("") && !type.equals("") && !value.equals("")){
            int id = SymbolTable.size() + 1;
            SymbolTable.add(new Variable(id,name,type,value));
        }
    }
    
    public static void changeSymbolValue(String name, String newValue){
        Variable id = getSymbol(name);
        if(id != null){
            id.setValue(newValue);
        } else {
            throw new IDNotFound(name);
        }
    }
}
