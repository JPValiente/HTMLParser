/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Actions;

import Backend.Objects.Tag;
import Frontend.MainFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author anclenius
 */
public class HtmlAction {
    private static JTextPane textPane;
    
    private static List<Tag> tags = new ArrayList<>();
    public enum actions {
        PARAGRAPH,
        LINEJUMP,
        BODYBGCOLOR,
        BODYFONTCOLOR,
        BODYLINKCOLOR,
        TOCENTER,
        HIGHLIGHT,
        BOLD,
        ITALIC,
        STRIKE,
        BLINK,
        SUBINDEX,
        SUPINDEX,
        UL,
        OL,
        TYPELIST,
        NOBR,
        HR,
        ALIGNHR,
        WIDHTHR,
        SIZEHR,
        BLOCKQUOTE,
        SETTAG,
        GOTO
    }
    
    
    public static void p(align alineacion,String text){
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet attribs = new SimpleAttributeSet();
        if(alineacion == align.left){
            StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_LEFT);
        } else if(alineacion == align.right){
            StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_RIGHT);
        } else if(alineacion == align.center){
            StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        } else if(alineacion == align.justified){
            StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_JUSTIFIED);
        }
       
        try {
            doc.setParagraphAttributes(doc.getLength(),1,attribs, false);
            doc.insertString(doc.getLength(), "\n\n" + text, attribs);

            textPane.setParagraphAttributes(attribs, true);
        } 
        catch (BadLocationException e) {
            e.printStackTrace();
        } 
    }
    
    public static void endp(){
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_LEFT);
        doc.setParagraphAttributes(doc.getLength(),1,attribs, true);
    }
    
    public static void br(){
        StyledDocument doc = textPane.getStyledDocument();
        Style style = textPane.addStyle("Color Style", null);
        try {
            doc.insertString(doc.getLength(), "\n", style);
        } 
        catch (BadLocationException e) {
            e.printStackTrace();
        }   
    }
    
    public static void b(String text){
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        attributes.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);
        StyledDocument doc = textPane.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), text, attributes);
        } 
        catch (BadLocationException e) {
            e.printStackTrace();
        } finally {
            attributes.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.FALSE);
        }   
    }
    
    public static void i(String text){
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        attributes.addAttribute(StyleConstants.CharacterConstants.Italic, Boolean.TRUE);
        StyledDocument doc = textPane.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), text, attributes);
        } 
        catch (BadLocationException e) {
            e.printStackTrace();
        } finally {
            attributes.addAttribute(StyleConstants.CharacterConstants.Italic, Boolean.FALSE);
        }   
    }
    
    public static void u(String text){
        StyledDocument doc =  textPane.getStyledDocument();
        Style style = doc.addStyle("Style", null);
        StyleConstants.setUnderline(style, true);
        try {
            doc.insertString(doc.getLength(), text, style);
        } 
        catch (BadLocationException e) {
            e.printStackTrace();
        } finally{
            StyleConstants.setUnderline(style, false);
        }
    }
    
    public static void strike(String text){
        StyledDocument doc =  textPane.getStyledDocument();
        Style style = doc.addStyle("Style", null);
        StyleConstants.setStrikeThrough(style, true);
        try {
            doc.insertString(doc.getLength(), text, style);
        } 
        catch (BadLocationException e) {
            e.printStackTrace();
        } finally{
            StyleConstants.setStrikeThrough(style, false);
        }
    }
    
//    public static void blink(String text){
//        HTMLDocument doc=(HTMLDocument) textPane.getStyledDocument();
//        try {
//            doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()),"<blink>"+text+"</blink>");
//            //Colocar excepciones
//        } catch (BadLocationException ex) {
//            
//        } catch (IOException ex) {
//            
//        }
//    }
    
    public static void sub(String text){
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        attributes.addAttribute(StyleConstants.CharacterConstants.Subscript, Boolean.TRUE);
        StyledDocument doc = textPane.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), text, attributes);
        } 
        catch (BadLocationException e) {
            e.printStackTrace();
        } finally {
            attributes.addAttribute(StyleConstants.CharacterConstants.Subscript, Boolean.FALSE);
        }   
    }
    
    public static void sup(String text){
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        attributes.addAttribute(StyleConstants.CharacterConstants.Superscript, Boolean.TRUE);
        StyledDocument doc = textPane.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), text, attributes);
        } 
        catch (BadLocationException e) {
            e.printStackTrace();
        } finally {
            attributes.addAttribute(StyleConstants.CharacterConstants.Superscript, Boolean.FALSE);
        }   
    }
    
    public static void nobr(String text){
        StyledDocument doc = textPane.getStyledDocument();
        Style style = textPane.addStyle("Color Style", null);
        try {
            text.replace("\n","");
            text.replace("<br>", "");
            text.replace("</br>", "");
            text.replace("<p>", "");
            text.replace("</p>", "");
            doc.insertString(doc.getLength(),text, style);
        } 
        catch (BadLocationException e) {
            e.printStackTrace();
        }   
    }
    
    public static void blockquote(String text){
        StyledDocument doc = textPane.getStyledDocument();
        Style style = textPane.addStyle("Color Style", null);
        try {
            text.replace("\n","\n\t");
            doc.insertString(doc.getLength(),text, style);
        } 
        catch (BadLocationException e) {
            e.printStackTrace();
        }   
    }
    
    public static void ul(String type,List<String> items) throws BadLocationException{
        StyledDocument doc = textPane.getStyledDocument();
        Style style;
        style = textPane.addStyle("BOLD",null);
        if(type.equals("circle")){
            for (String item : items) {
                doc.insertString(doc.getLength(), "  ○  ", style);
                doc.insertString(doc.getLength(), item + "\n", style);
            }
        } else if(type.equals("disc")){
            for (String item : items) {
                doc.insertString(doc.getLength(), "  ●  ", style);
                doc.insertString(doc.getLength(), item + "\n", style);
            }
        } else if(type.equals("square")){
            for (String item : items) {;
                doc.insertString(doc.getLength(), "  ■  ", style);
                doc.insertString(doc.getLength(), item + "\n", style);
            }
        }
        
    }
    
    public static void executeActions(String texto){
       textPane = MainFrame.getPane();
        
       
    }
    
    public static void ol(String type,List<String> items) throws BadLocationException{
        StyledDocument doc = textPane.getStyledDocument();
        Style style;
        style = textPane.addStyle("BOLD",null);
        if(type.equals("1")){
            for (String item : items) {
                doc.insertString(doc.getLength(), "  1.  ", style);
                doc.insertString(doc.getLength(), item + "\n", style);
            }
        } else if(type.equals("a")){
            for (String item : items) {
                doc.insertString(doc.getLength(), "  a.  ", style);
                doc.insertString(doc.getLength(), item + "\n", style);
            }
        } else if(type.equals("A")){
            for (String item : items) {;
                doc.insertString(doc.getLength(), "  A.  ", style);
                doc.insertString(doc.getLength(), item + "\n", style);
            }
        }
        
    }
    
    public static void insertTag(String tagId,int x, int y, String text){
        tags.add(new Tag(tagId,x,y));
        JLabel label = new JLabel(text);
        label.setOpaque(true);
        textPane.setCaretPosition(textPane.getDocument().getLength());
        textPane.insertComponent(label);
        label.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent evt)
                {
                    loadTag(tagId);
                }
            });
        
    }
    
    
    public static void loadTag(String tagId){
        Tag tag = searchTag(tagId);
        String []t = textPane.getText().split("\n");
	int position=0;
	for(int index=0;index<t.length;index++){
		if(index == tag.getY()-1)break;
		if(t[index].length()!=0)
		        position+=t[index].length();
	}
	textPane.setCaretPosition(position+tag.getY()-1);
    }   
    
    
    public static void printOnlyText(String text){
        StyledDocument doc = textPane.getStyledDocument();
        Style style = textPane.addStyle("Color Style", null);
        try {
            doc.insertString(doc.getLength(), "text", style);
        } 
        catch (BadLocationException e) {
            e.printStackTrace();
        }   
    }
    
    public static Tag searchTag(String tagId){
        for (Tag tag : tags) {
            if(tag.getTagId().equals(tagId)){
                return tag;
            }
        }
        return null;
    }
    
    
    
    
    
    
    public static enum align{
        center,
        left,
        right,
        justified;
    }
    
    
    
    
}
