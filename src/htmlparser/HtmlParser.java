/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htmlparser;

import Backend.EmbedCode.Action;
import Backend.EmbedCode.DefineVariable;
import Backend.EmbedCode.For;
import Backend.EmbedCode.Print;
import Backend.EmbedCode.VariableManipulator;
import Backend.Objects.Variable;
import Frontend.MainFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptException;

/**
 *
 * @author anclenius
 */
public class HtmlParser {

    /**
     * @param args the command line arguments
     */
    public static List<Action> actions = new ArrayList<>();
    public static List<Action> moreActions = new ArrayList<>();
    public static List<Action> moreMoreActions = new ArrayList<>();
    static Variable var1;
    static Variable var2;
    public static void main(String[] args) throws ScriptException {
        
        try {
//            moreActions();
//            addActions();
//            
//            new VariableTable();
//            Action.doActions(actions);
//            System.out.println("Termine de hacer las acciones");
            new MainFrame();
            
        } catch (Exception ex) {
            System.out.println("Mamadas");
        }
    }
    
    public static void addActions() throws ScriptException{
        actions.add(new DefineVariable("var1","INTEGER"));
        actions.add(new VariableManipulator("var1",0,false));
        
        
        
        
    }
    
    public static void moreActions() throws ScriptException{
        
        //moreActions.add(new VariableManipulator("var1","var1 + 1",true));
        moreActions.add(new Print("ciclo #var1"));
    }
    
    
    
    
    //Break the bond, tear the fabric, cleave the stone, stop the magic.
}
