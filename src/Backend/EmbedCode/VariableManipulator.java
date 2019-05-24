/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.EmbedCode;

import Backend.Objects.Variable;
import Backend.SymbolTable.SymbolTable;
import Exceptions.IDNotFound;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author anclenius
 */
public class VariableManipulator extends Action{
    private String variable;
    private Object newValue;
    
    private boolean expression;
    
    public VariableManipulator(String var, Object newValue,boolean expression) throws ScriptException{
        this.variable = var;
        if(this.variable == null){
            throw new IDNotFound(var);
        }
        this.expression = expression;
        this.newValue = newValue;
        
        
    }
    
    
    public void changeValue(Variable var, Object newValue){
        if(newValue instanceof Variable){
            newValue = ((Variable)newValue).getValue();
        }
        
        System.out.println("Cambie el valor de la variable: " + var.getName() + " de " + var.getValue() + " a " + newValue.toString());
        SymbolTable.changeSymbolValue(var.getName(), newValue);
        
    }
    
    public int getNewValor(){
        return Integer.parseInt(newValue.toString());
    }
    
    public void exec() throws ScriptException{
        Object aux = this.newValue;
        System.out.println("Empiezo las cosas de la variable");
        if (expression){
            try{
            this.newValue = evaluateExpression(newValue.toString());}
            catch(NumberFormatException ex){
                this.newValue = newValue;
                this.newValue = this.newValue.toString().replace("'", "");
                this.newValue = this.newValue.toString().replace(",", "");
            }
        } else {
            this.newValue = newValue;
        }
        Variable var = SymbolTable.getSymbol(variable);
        if(var == null){
            throw new IDNotFound(variable);
        }
        changeValue(var,newValue);
        this.newValue = aux;
        if(Action.isDebuggin()){
                Debug.actualizarTabla();
        }
    }
    
    public static int evaluateExpression(String expression) throws ScriptException,NumberFormatException{
        ScriptEngine engine = new ScriptEngineManager().getEngineByExtension("js");
            //System.out.println("Expresion a evaluar: " + expresionAEvaluar);
            System.out.println("Tamano tabla: "+SymbolTable.getTableSize());
            for (Variable id : SymbolTable.getSymbolTable()) {
                if(expression.contains(id.getName())){
                    expression = expression.replace(id.getName(), id.getValue() + "");    
                }
            }
            Object result = engine.eval(expression);
            String result2 = result.toString();
            float result4 = Float.parseFloat(result2);
            int result3 = (int) result4;
            return result3;
            
    }
    
    public String getVar(){
        return this.variable;
    }
    
}
