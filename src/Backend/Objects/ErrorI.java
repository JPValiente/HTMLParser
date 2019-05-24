/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anclenius
 */
public class ErrorI {
    private static List<ErrorI> errores = new ArrayList<>();
    private static List<String> archivos = new ArrayList<>();
    private int linea;
    private int columna;
    private String descripcion;
    private String archivo;
    /*
    a = lexico
    b = sintactico
    c = semantico
    */
    private char tipoError;
    
    public ErrorI(int linea, int columna, String descripcion, String archivo, char tipoError){
        this.linea = linea;
        this.columna = columna;
        this.descripcion = descripcion;
        this.archivo = archivo;
        this.tipoError = tipoError;
        System.out.println("\n\n" + porError());
    }
    
    public ErrorI(String descripcion, String archivo, char tipoError){
        this.linea = 0;
        this.columna = 0;
        this.descripcion = descripcion;
        this.archivo = archivo;
        this.tipoError = tipoError;
    }
    
    public static void addError(ErrorI error){
        errores.add(error);
    }
    
    public String porError(){
        String salida = "";
        salida = salida + "<error>\n";
        salida = salida + "\t<linea> "+this.linea+" </linea>\n";
        salida = salida + "\t<columna> "+this.columna+" </columna>\n";
        salida = salida + "\t<descripcion> "+this.descripcion+" </descripcion >\n";
        salida = salida + "\t<archivo> "+this.archivo+" </archivo>\n";
        salida = salida + "</error>\n";
        return salida;
    }
    
    public static boolean isEmpty(){
        return errores.isEmpty();
    }
    
    public static String salidaPorError(){
        String salida = "";
        for (ErrorI error : errores) {
            salida = salida + error.porError();
        }
        return salida;
    }
    
    public static String porTipoError(){
        int totErrores = 0;
        String salida = "";
        salida = salida + "<errores> \n";
        salida = salida + "\t<Semanticos> \n";
        for (ErrorI error : errores) {
            totErrores++;
            if(error.tipoError == 'c'){
                salida = salida + "\t\t<error"+totErrores+">";
                salida = salida + error.porError();
                salida = salida + "\t\t</error"+totErrores+">";
            } 
          
        }
        salida = salida + "\t<Semanticos> \n";
        totErrores = 0;
        salida = salida + "\t<Sintacticos> \n";
        for (ErrorI error : errores) {
            totErrores++;
            if(error.tipoError == 'b'){
                salida = salida + "\t\t<error"+totErrores+">";
                salida = salida + error.porError();
                salida = salida + "\t\t</error"+totErrores+">";
            } 
          
        }
        salida = salida + "\t<Sintacticos> \n";
        totErrores = 0;
        salida = salida + "\t<Lexicos> \n";
        for (ErrorI error : errores) {
            totErrores++;
            if(error.tipoError == 'a'){
                salida = salida + "\t\t<error"+totErrores+">";
                salida = salida + error.porError();
                salida = salida + "\t\t</error"+totErrores+">";
            } 
          
        }
        salida = salida + "\t<Lexicos> \n";
        return salida;
    }
    
    public static String porArchivoAnalizado(){
        String salida = "";
        buscarArchivos();
        salida = salida + "<errores>\n";
        for (String archivo : archivos) {
            salida = salida + "<archivo nombre = \""+archivo +"\">\n";
            for (ErrorI error : errores) {
                if(archivo.equals(error.archivo)){
                    salida = salida + error.porError();
                }
            }
            salida = salida + "</archivo>";
        }
        salida = salida + "</errores>";
        return salida;
    }
    
    public static void buscarArchivos(){
        archivos.clear();
        for (ErrorI error : errores) {
            if(!archivoExiste(error.archivo)){
                archivos.add(error.archivo);
            }
        }
    }
    
    public static boolean archivoExiste(String arc){
        for (String archivo : archivos) {
            if(archivo.equals(arc)){
                return true;
            }
        }
        return false;
    }
    
    public static void cleanTable(){
        errores.clear();
    }
    
    
}
