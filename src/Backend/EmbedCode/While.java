/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.EmbedCode;

import static Backend.EmbedCode.Action.sameType;
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
public class While extends Action{
    private List<Action> acciones = new ArrayList<>();
    private Object con1;
    private String sym;
    private Object con2;
    private boolean sameTypeVar;
    
    public While(List<Action> acciones, Object con1, String sym, Object con2){
        this.acciones = acciones;
        System.out.println("cant acciones: " + acciones.size());
        this.con1 = con1;
        if(con1 instanceof Variable){
            con1 = ((Variable)con1).getValue();
        }
        this.con1 = con1.toString();
        this.con2 = con2;
        if(con2 instanceof Variable){
            con2 = ((Variable)con2).getValue();
        }
        this.con2 = con2.toString();
        this.sym = sym;
        this.sameTypeVar = sameType(con1,con2);
        
        
    }
    
    
    
    public void exec() throws InterruptedException, ScriptException{
        int vecesCiclo = 0;
        while(compare()){
            vecesCiclo++;
            System.out.println("\n\n\nSe repite el ciclo");
            for (Action action : acciones) {
                
                Action.doActions(action);
            }
            update();
          
        }
        System.out.println("El while itero " + vecesCiclo + " vez/ces");
    }
    
    public void update(){
        
        if(con1 instanceof String){
            try {
                con1 = Integer.parseInt((String)con1);
            } catch(Exception ex){
               Variable varo = SymbolTable.getSymbol((String)con1);
                if(varo != null){
                    con1 = varo.getName();
                } 
            }
        } else if(con1 instanceof Variable){
            con1 = SymbolTable.getSymbol(((Variable)con1).getName());
        }
        
        if(con2 instanceof String){
            try {
                con2 = Integer.parseInt((String)con2);
            } catch(Exception ex){
               Variable varo = SymbolTable.getSymbol((String)con2);
                if(varo != null){
                    if(varo.getType().equals("INTEGER")){
                        con2 = varo;
                    } else if(varo.getType().equals("STRING")){
                        con2 = varo.getValue();
                    } else if(varo.getType().equals("BOOLEAN")){
                        con2 = varo.getValue();
                    }
                } 
            }
        }else if(con2 instanceof Variable){
            con2 = SymbolTable.getSymbol(((Variable)con2).getName());
        }
        
        
        
        System.out.println("Actualice con1: " + con1.toString() + " con2: " + con2.toString());
    }
    
    
    public boolean compare(){
        String type = getType(con1,con2);
        Object ana1 = con1;
        Object ana2 = con2;
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
                    System.out.println("\n\n\n"+var1 + "<=" + var2);
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
                    System.out.println("\n\n\n"+var1 + ">=" + var2);
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
                    System.out.println("\n\n\n"+var1 + ">" + var2);
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
                    System.out.println("\n\n\n"+var1 + "<" + var2);
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
        Object ana1 = var1;
        Object ana2 = var2;
        if(var1 instanceof Integer){
            vari1 = var1;
        } else if(var1 instanceof String){
            try {
                vari1 = Integer.parseInt((String)var1);
            } catch(Exception ex){
               Variable varo = SymbolTable.getSymbol((String)var1);
                if(varo != null){
                    if(varo.getType().equals("INTEGER")){
                        vari1 = varo.getValue();
                    } else if(varo.getType().equals("STRING")){
                        vari1 = varo.getValue();
                    } else if(varo.getType().equals("BOOLEAN")){
                        vari1 = varo.getValue();
                    }
                } else {
                    vari1 = var1;
                } 
            }
            
           
        } else if(var1 instanceof Boolean){
            vari1 = var1;
        } else if(var1 instanceof Variable){
            if(((Variable) var1).getType().equals("INTEGER")){
                vari1 = Integer.parseInt(((Variable) var1).getValue().toString());
            } else if(((Variable) var1).getType().equals("STRING")){
                vari1 = Integer.parseInt(((Variable) var1).getValue().toString());
            } else if(((Variable) var1).getType().equals("BOOLEAN")){
                vari1 = Integer.parseInt(((Variable) var1).getValue().toString());
            }
        } 
        
        if(var2 instanceof Integer){
            vari2 = var2;
        } else if(var2 instanceof String){
            try{
                vari2 = Integer.parseInt((String)var2);
            }catch(Exception ex){
               Variable varo = SymbolTable.getSymbol((String)var2);
            if(varo != null){
                if(varo.getType().equals("INTEGER")){
                    vari2 = Integer.parseInt((String) varo.getValue());
                } else if(varo.getType().equals("STRING")){
                    vari2 = varo.getValue();
                } else if(varo.getType().equals("BOOLEAN")){
                    vari2 = Boolean.parseBoolean((String) varo.getValue());
                }
            } else {
                vari2 = var2;
            } 
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
            return "INTEGER";
        } else if(vari1 instanceof String && vari2 instanceof String){
            return "STRING";
        } else if(vari1 instanceof Boolean && vari2 instanceof Boolean){
            return "BOOLEAN";
        }
        
        return "NULL";
    }
    
    public int getIntValue(Object var1){
        Object vari1;
        if(var1 instanceof Integer){
            return (Integer)var1;
        } else if(var1 instanceof Variable){
            if(((Variable) var1).getType().equals("INTEGER")){
                return Integer.parseInt(((Variable) var1).getValue().toString());
            }
        } else if(var1 instanceof String){
            try {
                return Integer.parseInt((String)var1);
            } catch(Exception ex){
               Variable varo = SymbolTable.getSymbol((String)var1);
                if(varo != null){
                    if(varo.getType().equals("INTEGER")){
                        return (Integer)varo.getValue();
                    } 
                }
            }
            
           
        }
        return -1;
    }
}
