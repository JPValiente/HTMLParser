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
public class Text {
    private String texto;
    private boolean bold;
    private boolean italic;
    private boolean subrayado;
    private boolean strike;
    private boolean blink; //Sin implementar
    private boolean sub;
    private boolean sup;
    
    public Text(String text){
        this.texto = text;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public boolean isSubrayado() {
        return subrayado;
    }

    public void setSubrayado(boolean subrayado) {
        this.subrayado = subrayado;
    }

    public boolean isStrike() {
        return strike;
    }

    public void setStrike(boolean strike) {
        this.strike = strike;
    }

    public boolean isBlink() {
        return blink;
    }

    public void setBlink(boolean blink) {
        this.blink = blink;
    }

    public boolean isSub() {
        return sub;
    }

    public void setSub(boolean sub) {
        this.sub = sub;
    }

    public boolean isSup() {
        return sup;
    }

    public void setSup(boolean sup) {
        this.sup = sup;
    }
    
    
}
