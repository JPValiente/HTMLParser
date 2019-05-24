/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Objects;

/**
 *
 * @author anclenius
 */
public class Variable {
    private int id;
    private String name;
    private String type;
    private Object value;
    
    public Variable(int id, String name, String type, Object value){
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
    
    public void setValue(Object value) {
        this.value = value;
    }
}
