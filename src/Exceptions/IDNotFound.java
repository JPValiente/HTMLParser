/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author anclenius
 */
public class IDNotFound extends RuntimeException{
    public IDNotFound(String nombre){
        super("El identificador " + nombre + " no existe en la tabla de simbolos");
    }
}
