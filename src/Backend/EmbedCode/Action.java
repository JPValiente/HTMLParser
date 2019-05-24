/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.EmbedCode;

import Backend.Objects.Variable;
import java.util.List;
import javax.script.ScriptException;

/**
 *
 * @author anclenius
 */
public class Action {
    private static boolean enableDebug = false;
    private static final int TIEMPO_ESPERA = 1000;
    public static void doActions(List<Action> acciones) throws InterruptedException, ScriptException{
        System.out.println("Empece a hacer acciones");
        System.out.println("La cantidad de acciones es: " + acciones.size());
        for (Action accion : acciones) {
            System.out.println("\n\n\nNueva accion");
            doActions(accion);
            if(enableDebug){
                Debug.actualizarTabla();
            }
            
        
        }
    
    }
    
    public static void enableDebug(){
        enableDebug = true;
    }
    
    public static void disableDebug(){
        enableDebug = false;
    }
    
    public static boolean isDebuggin(){
        return enableDebug;
    }
    
    public static void doActions(Action accion) throws InterruptedException, ScriptException{
        //System.out.println(accion);
        if(enableDebug){
            Thread.sleep(TIEMPO_ESPERA);}
        if(accion instanceof For){
            System.out.println("Estoy en un for");
            ((For)accion).exec();
        } else if(accion instanceof If){
            System.out.println("Estoy ejecutando un if");
            ((If)accion).exec();
        } else if(accion instanceof Print){
            System.out.println("Estoy ejecutando un print");
            ((Print)accion).exec();
        } else if(accion instanceof While){
            System.out.println("Estoy ejecutando un while");
            ((While)accion).exec();
        } else if(accion instanceof VariableManipulator){
            System.out.println("Estoy definiendo una variable");
            ((VariableManipulator)accion).exec();
        } else if(accion instanceof DefineVariable){
            System.out.println("Estoy declarando una variable");
            ((DefineVariable)accion).exec();
        }
        //Cambiar para hacer el debug
        if(enableDebug){
                Debug.actualizarTabla();
        }
    }
    
    public static boolean sameType(Object var1,Object var2){
        Object vari1 = null;
        Object vari2 = null;
        if(var1 instanceof Integer){
            vari1 = var1;
        } else if(var1 instanceof String){
            vari1 = var1;
        } else if(var1 instanceof Boolean){
            vari1 = var1;
        } else if(var1 instanceof Variable){
            if(((Variable) var1).getType().equals("INTEGER")){
                vari1 = ((Variable) var1).getValue();
            } else if(((Variable) var1).getType().equals("STRING")){
                vari1 = ((Variable) var1).getType();
            } else if(((Variable) var1).getType().equals("BOOLEAN")){
                vari1 = ((Variable) var1).getType();
            }
        } 
        
        if(var2 instanceof Integer){
            vari2 = var2;
        } else if(var2 instanceof String){
            vari2 = var2;
        } else if(var2 instanceof Boolean){
            vari2 = var2;
        } else if(var2 instanceof Variable){
            if(((Variable) var2).getType().equals("INTEGER")){
                vari2 = ((Variable) var2).getValue();
            } else if(((Variable) var2).getType().equals("STRING")){
                vari2 = ((Variable) var2).getType();
            } else if(((Variable) var2).getType().equals("BOOLEAN")){
                vari2 = ((Variable) var2).getType();
            }
        }
        
        if(vari1 instanceof Integer && vari2 instanceof Integer){
            return true;
        } else if(vari1 instanceof String && vari2 instanceof String){
            return true;
        } else if(vari1 instanceof Boolean && vari2 instanceof Boolean){
            return true;
        }
        
        return false;
    }
    

}
