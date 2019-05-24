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
    
    public static Object getSymbolValue(String name){
        for (Variable variable : SymbolTable) {
            if(variable.getName().equals(name)){
                if(variable.getValue() != null){
                    return variable.getValue();
                } else {
                    System.out.println("La variable aun no se le ha asignado un valor");
                }
            }
        }
        return null;
    }
    
    public static void cleanTable(){
        SymbolTable.clear();
    }
    
    public static Object getSymbolValue(int id){
        for (Variable variable : SymbolTable) {
            if(variable.getId() == id){
                if(variable.getValue() != null){
                    return variable.getValue();
                } else {
                    System.out.println("La variable aun no se le ha asignado un valor");
                }
            }
        }
        return null;
    }
    
    public static Variable getSymbol(String name){
        //System.out.println(SymbolTable.size());
        for (Variable variable : SymbolTable) {
            //System.out.println("Variable " + variable.getName());
            if(variable.getName().equals(name)){
                return variable;
            }
        }
        return null;
    }
    
   
    
    
    
    public static Variable setSymbol(String name, String type, String value){
        if(!name.equals("") && !type.equals("") && !value.equals("")){
            int id = SymbolTable.size() + 1;
            Variable var = new Variable(id,name,type,value);
            SymbolTable.add(var);
            return var;
        }
        return null;
    }
    
    public static void changeSymbolValue(String name, Object newValue){
        Variable id = getSymbol(name);
        if(id != null){
            if(newValue instanceof Variable){
                newValue = ((Variable)newValue).getValue();
                
            }
            
            id.setValue(newValue);
        } else {
            throw new IDNotFound(name);
        }
    }

    
    public static void defineSymbol(String name, String type){
        Variable vari = getSymbol(name);
        if(vari == null){
            if(!name.equals("") && !type.equals("")){
                int id = SymbolTable.size() + 1;
                Variable var = new Variable(id,name,type,null);
                System.out.println("Defini la variable " + name + " de tipo: " + type);
                SymbolTable.add(var);
            }  
        } else {
            System.out.println("TIRAR EXCEPCION DE SIMBOLO YA EXISTENTE");
        }
        
    }
    
    public static List<Variable> getSymbolTable(){
        return SymbolTable;
    }
    
    public static int getTableSize(){
        return SymbolTable.size();
    }
}
