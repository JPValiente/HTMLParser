/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.EmbedCode;

import Backend.SymbolTable.SymbolTable;

/**
 *
 * @author anclenius
 */
public class DefineVariable extends Action{
    
    private String var;
    private String tipo;
    
    public DefineVariable(String var,String tipo){
        this.var = var;
        this.tipo = tipo;
    }
    
    public void exec(){
        SymbolTable.defineSymbol(var, tipo);
    }
    
    public String getVar(){
        return this.var;
    }
}
