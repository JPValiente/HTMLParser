/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Actions.HtmlAction;
import Backend.EmbedCode.Action;
import Backend.Objects.ErrorI;
import Backend.SymbolTable.SymbolTable;
import Parser.EmbedCode.Analizador_EmbedCode;
import Parser.EmbedCode.parser;
import Parser.HTML.Analizador_HTML;
import Parser.HTML.parserHTML;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.BadLocationException;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

/**
 *
 * @author anclenius
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    private JTextArea currentjText;
    private UndoManager undoManager = new UndoManager();
    private File currentEditingFile = null;
    public static String currentFile = "Pestaña 1";
    public static String newText = "";
    public MainFrame() {
        initComponents();
        
        jCheckBoxMenuItem1.setSelected(false);
        this.setVisible(true);
        JTextArea area = new JTextArea();
        currentjText = area;
        JScrollPane asd = new JScrollPane(area);
        Tabs.addTab("Pestaña 1", asd);
        addUndoRedoFunctionality();
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e); //To change body of generated methods, choose Tools | Templates.
                int ans = JOptionPane.showConfirmDialog(rootPane, "Desea Guardar?", " ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (ans == JOptionPane.YES_OPTION) {
                    saveFile();
                }
            }});
        
        currentjText.addCaretListener( new CaretListener() {
            public void caretUpdate( CaretEvent e ) {
            int pos = e.getDot();
                try {
                    int row = currentjText.getLineOfOffset( pos ) + 1;
                    int col = pos - currentjText.getLineStartOffset( row - 1 ) + 1;
                    jLabel1.setText("Línea: " + row + " Columna: " + col );
                }
                catch( BadLocationException exc ){
                    System.out.println(exc);
                }
            }
        });
    
    }
        
        public MainFrame(File file) {
        initComponents();
        this.setLocationRelativeTo(null);

        currentEditingFile = file;
        //FileNameExtensionFilter filtro = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        //fileOpener.setFileFilter(filtro);
        readFile(file);
        addUndoRedoFunctionality();

        try {
            this.setIconImage(ImageIO.read(MainFrame.class.getResource("/proton/text/editor/resources/proton.png")));
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        addUndoRedoFunctionality();
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e); //To change body of generated methods, choose Tools | Templates.
                int ans = JOptionPane.showConfirmDialog(rootPane, "Desea Guardar?", " ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (ans == JOptionPane.YES_OPTION) {
                    saveFile();
                }
            }});
        
        currentjText.addCaretListener( new CaretListener() {
            public void caretUpdate( CaretEvent e ) {
            int pos = e.getDot();
                try {
                    int row = currentjText.getLineOfOffset( pos ) + 1;
                    int col = pos - currentjText.getLineStartOffset( row - 1 ) + 1;
                    jLabel1.setText("Línea: " + row + " Columna: " + col );
                }
                catch( BadLocationException exc ){
                    System.out.println(exc);
                }
            }
        });

    }
        
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jFileChooser1 = new javax.swing.JFileChooser();
        Tabs = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Electron IDE 1.0");
        setBackground(new java.awt.Color(65, 97, 150));

        Tabs.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                TabsStateChanged(evt);
            }
        });

        jTextPane1.setEditable(false);
        jScrollPane2.setViewportView(jTextPane1);

        jToolBar1.setBackground(new java.awt.Color(49, 49, 49));
        jToolBar1.setRollover(true);

        jButton1.setBackground(new java.awt.Color(49, 49, 49));
        jButton1.setForeground(new java.awt.Color(242, 241, 240));
        jButton1.setText("Nuevo  ");
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(49, 49, 49)));
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton7.setBackground(new java.awt.Color(49, 49, 49));
        jButton7.setForeground(new java.awt.Color(249, 249, 248));
        jButton7.setText("Cerrar Pestaña  ");
        jButton7.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(49, 49, 49)));
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);

        jButton2.setBackground(new java.awt.Color(49, 49, 49));
        jButton2.setForeground(new java.awt.Color(242, 241, 240));
        jButton2.setText("Abrir  ");
        jButton2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(49, 49, 49)));
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton3.setBackground(new java.awt.Color(49, 49, 49));
        jButton3.setForeground(new java.awt.Color(242, 241, 240));
        jButton3.setText("Guardar  ");
        jButton3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(49, 49, 49)));
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton4.setBackground(new java.awt.Color(49, 49, 49));
        jButton4.setForeground(new java.awt.Color(242, 241, 240));
        jButton4.setText("Undo  ");
        jButton4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(49, 49, 49)));
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jButton5.setBackground(new java.awt.Color(49, 49, 49));
        jButton5.setForeground(new java.awt.Color(242, 241, 240));
        jButton5.setText("Redo  ");
        jButton5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(49, 49, 49)));
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jButton6.setBackground(new java.awt.Color(49, 49, 49));
        jButton6.setForeground(new java.awt.Color(242, 241, 240));
        jButton6.setText("Exec  ");
        jButton6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(49, 49, 49)));
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jButton8.setBackground(new java.awt.Color(49, 49, 49));
        jButton8.setForeground(new java.awt.Color(242, 241, 240));
        jButton8.setText(" Exec All  ");
        jButton8.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(49, 49, 49)));
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton8);

        jMenu1.setText("Reporte de Errores");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Por Error");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Por tipo de Error");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Por archivo analizado");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Debug");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("Activar Debug");
        jCheckBoxMenuItem1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxMenuItem1StateChanged(evt);
            }
        });
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(Tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int index = Tabs.getSelectedIndex();
        try{
            Tabs.remove(index);
        }catch(Exception ex){
            System.out.println("Controlada");
        }
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int index = Tabs.getTabCount() + 1;
        Tabs.addTab("Pestaña " + index, new JScrollPane(new JTextArea()));
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TabsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_TabsStateChanged
        JScrollPane a = ((JScrollPane) Tabs.getSelectedComponent());
        currentjText = ((JTextArea) a.getViewport().getView());
        ((JTextPane)this.jScrollPane2.getViewport().getView()).setText("");
        addUndoRedoFunctionality();
        
        currentFile = Tabs.getTitleAt(Tabs.getSelectedIndex());
        currentjText.addCaretListener( new CaretListener() {
            public void caretUpdate( CaretEvent e ) {
            int pos = e.getDot();
                try {
                    int row = currentjText.getLineOfOffset( pos ) + 1;
                    int col = pos - currentjText.getLineStartOffset( row - 1 ) + 1;
                    jLabel1.setText("Línea: " + row + " Columna: " + col );
                }
                catch( BadLocationException exc ){
                    System.out.println(exc);
                }
            }
        });
        
        
    }//GEN-LAST:event_TabsStateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String text = currentjText.getText();
        String nuevoText = "";
        

        ErrorI.cleanTable();
        JTextPane a = ((JTextPane)this.jScrollPane2.getViewport().getView());
        while(true){
            boolean flag = false;
            try{
                SymbolTable.cleanTable();
                newText = "";
                nuevoText = text.substring(text.indexOf("<%"),text.indexOf("%>") + 2);
                flag = true;
                StringReader reader = new StringReader(nuevoText);
                Analizador_EmbedCode miLexer = new Analizador_EmbedCode(reader);
                parser otroParser = new parser(miLexer) {};
                try {
                    System.out.println("Empezando a parser");
                    otroParser.parse();
                } catch (Exception ex) {
                    System.out.println("Comando desconocido.");
                    ex.printStackTrace();
                } 
                
                if(flag){
                    text = text.replace(nuevoText, newText);
                }
                
                System.out.println("La salida es: " + text);
                

            } catch(Exception ex){
                System.out.println("No hay más codigo embebido que leer o falta alguna parte");
                text = text.replace("<%", "");
                text = text.replace("%>", "");
                break;
            }
        }
        
            a.setContentType("text/html");
            StringReader reader2 = new StringReader(text);
                Analizador_HTML miOtroLexer = new Analizador_HTML(reader2);
                parserHTML miParser = new parserHTML(miOtroLexer) {};
                try {
                    System.out.println("Empezando a parser");
                    miParser.parse();
                    HtmlAction.executeActions(text);
                    a.setText(text);
                } catch (Exception ex) {
                    System.out.println("Comando desconocido.");
                    ex.printStackTrace();
                } 
            if(ErrorI.isEmpty()){    
                JOptionPane.showMessageDialog(null, "Se ejecuto todo y no se encontraron errores en los parseos");
            } else {
                JOptionPane.showMessageDialog(null, "Se encontraron errores, por favor consultar en el reporte");
            }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        openFile();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        saveFile();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            undoManager.undo();
        } catch (CannotRedoException ex) {
            ex.printStackTrace();
        }
        jButton4.setEnabled(undoManager.canUndo());
        jButton5.setEnabled(undoManager.canRedo());
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            undoManager.redo();
        } catch (CannotRedoException cre) {
            cre.printStackTrace();
        }
        jButton4.setEnabled(undoManager.canUndo());
        jButton5.setEnabled(undoManager.canRedo());
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new Errores(ErrorI.salidaPorError());
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

    private void jCheckBoxMenuItem1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1StateChanged
        if(jCheckBoxMenuItem1.isSelected()){
            Action.enableDebug();
        } else {
            Action.disableDebug();
        }
    }//GEN-LAST:event_jCheckBoxMenuItem1StateChanged

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       new Errores(ErrorI.porTipoError());
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new Errores(ErrorI.porArchivoAnalizado());
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       //EXEC ALL
        ErrorI.cleanTable();
        for(int k = 0;k<Tabs.getTabCount();k++){
            JScrollPane b = ((JScrollPane) Tabs.getComponentAt(k));
            currentFile = Tabs.getTitleAt(k);
            String text =  ((JTextArea) b.getViewport().getView()).getText();
            String nuevoText = "";
        

        
        JTextPane a = ((JTextPane)this.jScrollPane2.getViewport().getView());
        while(true){
            boolean flag = false;
            try{
                SymbolTable.cleanTable();
                newText = "";
                nuevoText = text.substring(text.indexOf("<%"),text.indexOf("%>") + 2);
                StringReader reader = new StringReader(nuevoText);
                Analizador_EmbedCode miLexer = new Analizador_EmbedCode(reader);
                parser otroParser = new parser(miLexer) {};
                try {
                    System.out.println("Empezando a parser");
                    otroParser.parse();
                    flag = true;
                } catch (Exception ex) {
                    ErrorI.addError(new ErrorI(0,0,"Comando desconocido, Quizas la variable no retorno nada",MainFrame.currentFile,'c'));
  
                    ex.printStackTrace();
                } 
                
                if(flag){
                    text = text.replace(nuevoText, newText);
                }
                
                System.out.println("La salida es: " + text);
                

            } catch(Exception ex){
                System.out.println("No hay más codigo embebido que leer o falta alguna parte");
                text = text.replace("<%", "");
                text = text.replace("%>", "");
                break;
            }
        }
        
            
            StringReader reader2 = new StringReader(text);
                Analizador_HTML miOtroLexer = new Analizador_HTML(reader2);
                parserHTML miParser = new parserHTML(miOtroLexer) {};
                try {
                    System.out.println("Empezando a parser");
                    miParser.parse();

                } catch (Exception ex) {
                    ErrorI.addError(new ErrorI(0,0,"Comando desconocido, Quizas la variable no retorno nada",MainFrame.currentFile,'c'));
                    ex.printStackTrace();
                } 
            
        }
        
        if(ErrorI.isEmpty()){    
                JOptionPane.showMessageDialog(null, "Se ejecuto todo y no se encontraron errores en los parseos");
            } else {
                JOptionPane.showMessageDialog(null, "Se encontraron errores, por favor consultar en el reporte");
            }
        
        
       
    }//GEN-LAST:event_jButton8ActionPerformed

    public static JTextPane getPane(){
        return jTextPane1;
    }
    
    public void openFile() {
        int status = jFileChooser1.showOpenDialog(rootPane);
        if (status == JFileChooser.APPROVE_OPTION) {
            if (currentEditingFile != null) {
                // A file is opened and is being edited. Open the new file in new window  
                MainFrame newWindow = new MainFrame(jFileChooser1.getSelectedFile());
                newWindow.setVisible(true);
                newWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                newWindow.pack();
                return;
            }
            currentEditingFile = jFileChooser1.getSelectedFile();
            System.out.println("archivo = " + jFileChooser1.getSelectedFile().getName());

            try {
                java.util.Scanner scan = new java.util.Scanner(new FileInputStream(currentEditingFile));
                String buffer = "";
                while (scan.hasNext()) {
                    buffer += scan.nextLine() + "\n";
                }
                JTextArea area = new JTextArea();
                area.setText(buffer);
                JScrollPane scrolie = new JScrollPane(area);
                currentFile = jFileChooser1.getSelectedFile().getName();
                Tabs.addTab(jFileChooser1.getSelectedFile().getName(), scrolie);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
    public void readFile(File file) {
        try {
            java.util.Scanner scan = new java.util.Scanner(file);
            String buffer = "";
            while (scan.hasNext()) {
                buffer += scan.nextLine() + "\n";
            }
            currentjText.setText(buffer);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveFile() {
        if (currentEditingFile != null) {
            try {
                PrintWriter printWriter = new PrintWriter(currentEditingFile);
                printWriter.write(currentjText.getText());
                printWriter.close();
                JOptionPane.showMessageDialog(rootPane, "Guardado", "Estado", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            saveAsFile();
        }
    }

    public void saveAsFile() {
        int status = jFileChooser1.showOpenDialog(rootPane);
        if (status == JFileChooser.APPROVE_OPTION) {
            String fileName = jFileChooser1.getSelectedFile().toString();
            if (!fileName.contains(".txt")) {
                fileName += ".txt";
            }
            File f = new File(fileName);
            System.out.println(f.getAbsolutePath());
            if (f.exists()) {
                JOptionPane.showMessageDialog(rootPane, "El archivo ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                
                saveAsFile();
            } else {
                try {
                    f.createNewFile();
                    PrintWriter printWriter = new PrintWriter(f);
                    printWriter.write(currentjText.getText());
                    printWriter.close();
                    JOptionPane.showMessageDialog(rootPane, "Guardado", "Estado", JOptionPane.INFORMATION_MESSAGE);
                    currentEditingFile = f;
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void addUndoRedoFunctionality() {
        currentjText.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
                jButton4.setEnabled(undoManager.canUndo());
                jButton5.setEnabled(undoManager.canRedo());
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Tabs;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    public static javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JTextPane jTextPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
