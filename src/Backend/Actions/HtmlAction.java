/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Actions;

import Frontend.MainFrame;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLDocument;

/**
 *
 * @author anclenius
 */
public class HtmlAction {
    private static JTextPane textPane;
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
    public static void executeActions(String texto){
       textPane = MainFrame.getPane();
       
       b(texto);
       br();
       i(texto);
       br();
       u(texto);
       br();
       strike(texto);
       br();
//       blink(texto);
       br();
       p(align.center, texto);
       sub(texto);
       sup(texto);
       br();
       b("JEJEJEJE");
       
       
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
            text.replace("\n","");
            text.replace("<br>", "    ");
            text.replace("<p>", "    ");
            doc.insertString(doc.getLength(),text, style);
        } 
        catch (BadLocationException e) {
            e.printStackTrace();
        }   
    }
    
    
    
    
    
    
    public static enum align{
        center,
        left,
        right,
        justified;
    }
    
    
    
    
}
