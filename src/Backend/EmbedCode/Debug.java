/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.EmbedCode;

import Backend.Objects.Variable;
import Backend.SymbolTable.SymbolTable;
import Frontend.VariableTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author anclenius
 */
public class Debug {
    
    public static void actualizarTabla(){
        eliminar();
        for (Variable variable : SymbolTable.getSymbolTable()) {
            addDatatoJTable(variable);
        }
    }
    
    public static void eliminar(){
        DefaultTableModel tb = (DefaultTableModel) VariableTable.jTable1.getModel();
        int a = VariableTable.jTable1.getRowCount()-1;
        for (int i = a; i >= 0; i--) {           
            tb.removeRow(tb.getRowCount()-1);
        } 
    }
    
    public static void addDatatoJTable(Variable variable){
        DefaultTableModel model = (DefaultTableModel) VariableTable.jTable1.getModel();
        Object rowData[] = new Object[4];
            rowData[0] = variable.getId();
            rowData[1] = variable.getName();
            rowData[2] = variable.getType();
            rowData[3] = variable.getValue();
            model.addRow(rowData);
    }
    
    
    
}
