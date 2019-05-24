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
public class Tag {
    private String tagId;
    private int x;
    private int y;
    
    public Tag(String tagId, int x, int y){
        this.tagId = tagId;
        this.x = x;
        this.y = y;
    }
    
    public String getTagId(){
        return this.tagId;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
}
