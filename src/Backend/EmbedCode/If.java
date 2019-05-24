/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.EmbedCode;

import Backend.Objects.ErrorI;
import Backend.Objects.Variable;
import Backend.SymbolTable.SymbolTable;
import Frontend.MainFrame;
import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptException;

/**
 *
 * @author anclenius
 */
public class If extends Action{
    private List<Action> acciones = new ArrayList<>();
    private Object con1;
    private String sym;
    private Object con2;
    private boolean hasElse;
    private List<Action> elseActions = new ArrayList<>();
    private boolean type;
    
    public If(List<Action> acciones,String con1, String sym, Object con2, boolean hasElse, List<Action> elseActions){
        this.acciones = acciones;
        this.sym = sym;
        this.acciones = acciones;
        this.con1 = con1;
        this.con2 = con2;
        this.hasElse = hasElse;
        this.elseActions = elseActions;
        
    }
    
    
    
    public void exec() throws InterruptedException, ScriptException{
        con1 = SymbolTable.getSymbol(con1.toString());
        if(compare()){
            Action.doActions(acciones);
        } else if(!compare()){
            Action.doActions(elseActions);
        }
    }
    
    public boolean compare(){
        String type = getType(con1,con2);
        switch (sym){
            case "=":
                if(getComp(con1,con2)){
                    return true;
                }
            break;
            case "<=":
                if(type.equals("INTEGER")){
                    int var1 = getIntValue(con1);
                    int var2 = getIntValue(con2);
                    if(var1 <= var2){
                        return true;
                    }
                } else {
                    ErrorI.addError(new ErrorI("Los simbolos " + con1.toString() + " y "+con2.toString()+" No son del mismo tipo",MainFrame.currentFile,'c'));
                }
            break;
            case ">=":
                if(type.equals("INTEGER")){
                    int var1 = getIntValue(con1);
                    int var2 = getIntValue(con2);
                    if(var1 >= var2){
                        return true;
                    }
                } else {
                    ErrorI.addError(new ErrorI("Los simbolos " + con1.toString() + " y "+con2.toString()+" No son del mismo tipo",MainFrame.currentFile,'c'));
                } 
            break;
            case ">":
                if(type.equals("INTEGER")){
                    int var1 = getIntValue(con1);
                    int var2 = getIntValue(con2);
                    if(var1 > var2){
                        return true;
                    }
                } else {
                    ErrorI.addError(new ErrorI("Los simbolos " + con1.toString() + " y "+con2.toString()+" No son del mismo tipo",MainFrame.currentFile,'c'));
                } 
            break;
            case "<":
                if(type.equals("INTEGER")){
                    int var1 = getIntValue(con1);
                    int var2 = getIntValue(con2);
                    if(var1 < var2){
                        return true;
                    }
                } else {
                    ErrorI.addError(new ErrorI("Los simbolos " + con1.toString() + " y "+con2.toString()+" No son del mismo tipo",MainFrame.currentFile,'c'));
                } 
            break;
            case "<>":
                if(getVersusComp(con1,con2)){
                    return true;
                }
            break;
        }
        return false;
    }
    
    public static boolean getComp(Object var1,Object var2){
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
            try{
                vari2 = Integer.parseInt((String)var2);
            } catch (Exception ex){
                vari2 = var2;
            }
            
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
            if((Integer)vari1 == (Integer) vari2){
                return true;
            }
        } else if(vari1 instanceof String && vari2 instanceof String){
            if(((String)vari1).equals((String)vari2)){
                return true;
            }
        } else if(vari1 instanceof Boolean && vari2 instanceof Boolean){
            if((Boolean)var1 == (Boolean) vari2){
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean getVersusComp(Object var1,Object var2){
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
            if((Integer)vari1 != (Integer) vari2){
                return true;
            }
        } else if(vari1 instanceof String && vari2 instanceof String){
            if(!((String)vari1).equals((String)vari2)){
                return true;
            }
        } else if(vari1 instanceof Boolean && vari2 instanceof Boolean){
            if((Boolean)var1 != (Boolean) vari2){
                return true;
            }
        }
        
        return false;
    }
    
    
    
    public static String getType(Object var1,Object var2){
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
                System.out.println("\n\n\n Mirenme: " + vari1);
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
            return "INTEGER";
        } else if(vari1 instanceof String && vari2 instanceof String){
            return "STRING";
        } else if(vari1 instanceof Boolean && vari2 instanceof Boolean){
            return "BOOLEAN";
        }
        
        return "NULL";
    }
    
    public int getIntValue(Object var1){
        System.out.println("\n\n\nEl valor de la var1: " + var1);
        if(var1 instanceof Integer){
            return (Integer)var1;
        } else if(var1 instanceof Variable){
            if(((Variable) var1).getType().equals("INTEGER")){
                return Integer.parseInt(((Variable) var1).getValue().toString());
            }
        }
        return -1;
    }
    
    
    
    
    
    
}
