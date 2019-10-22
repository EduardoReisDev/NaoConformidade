/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.usuario;

import controller.UsuarioController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author leona
 */
public class FormCadastrar extends javax.swing.JDialog {
    private boolean dadosValidos = false;
    UsuarioController usuarioController;
    MaskFormatter mascaraCpf;
    /**
     * Creates new form CadastrarUsuario
     * @param parent
     * @param modal
     */
    public FormCadastrar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        usuarioController = new UsuarioController();
        try {
            mascaraCpf = new MaskFormatter("###.###.###-##");
            mascaraCpf.setPlaceholderCharacter('_');
            txtCpf.setFormatterFactory(new DefaultFormatterFactory(mascaraCpf));
        } catch (ParseException ex) {
            Logger.getLogger(FormCadastrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @Override
    protected JRootPane createRootPane() {
        // Definindo o ActionListener
        ActionListener actionListener = (ActionEvent e) -> {
            setVisible(false);
        };
        // Definindo o KeyStroke
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        // Criando uma instancia de JRootPane
        JRootPane rootPane = new JRootPane();
        // Registrando o KeyStroke enquanto o JDialog estiver em foco
        rootPane.registerKeyboardAction(
        actionListener, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        // Retornando o novo e modificado JRootPane
        return rootPane;
    }

    private boolean validarCpf(){
        return usuarioController.validarCpf(txtCpf.getText());
    }
    
    private boolean verificarCpf(){
        if(validarCpf()){
            System.out.println(usuarioController.verificarExistenciaCpf(txtCpf.getText()));
            return !usuarioController.verificarExistenciaCpf(txtCpf.getText());
        }
        return false;
    }
    
    private boolean validarNome(){
        return usuarioController.validarNome(txtNome.getText());
    }
    
    private boolean validarUsuario(){
        return usuarioController.validarUsuario(txtUsuario.getText());
    }
    
    private boolean validarSenha(){
        return usuarioController.validarSenha(txtSenha.getPassword());
    }
    
    private boolean verificarSenha(){
        return usuarioController.verificarSenha(txtSenha.getPassword(), txtConfirmarSenha.getPassword());
    }
    
    public boolean validar(){
        return (verificarSenha() && validarNome() && validarUsuario()&& validarSenha() && validarCpf() 
                && verificarCpf() && dadosValidos);
    }
    
    private void validarEFechar(){
        boolean valido = true;
        if(validarCpf()){
            verificacaoCpf.setBackground(Color.GREEN);
        }
        else{
            verificacaoCpf.setBackground(Color.red);
            txtCpf.requestFocusInWindow();
            valido = false;
        }
        if(verificarCpf()){
            verificacaoCpf.setBackground(Color.GREEN);
        }
        else{
            verificacaoCpf.setBackground(Color.red);
            txtCpf.requestFocusInWindow();
            valido = false;
        }
        if(validarNome()){
            verificacaoNome.setBackground(Color.green);
        }
        else{
            verificacaoNome.setBackground(Color.red);
            txtNome.requestFocusInWindow();
            valido = false;
        }
        if(validarUsuario()){
            verificacaoUsuario.setBackground(Color.green);
        }
        else{
            verificacaoUsuario.setBackground(Color.red);
            txtUsuario.requestFocusInWindow();
            valido = false;
        }
        if(validarSenha()){
            verificacaoSenha.setBackground(Color.green);
        }
        else{
            verificacaoSenha.setBackground(Color.red);
            txtSenha.requestFocusInWindow();
            valido = false;
        }
        if(verificarSenha()){
            verificacaoConfirmacaoSenha.setBackground(Color.green);
        }
        else{
            verificacaoConfirmacaoSenha.setBackground(Color.red);
            txtSenha.requestFocusInWindow();
            valido = false;
        }
        if(valido){
            dadosValidos = true;
            dispose();
        }
    }
    
    private void enterCampoCpf(){
        validarEFechar();
        if(validarCpf()){
            txtNome.requestFocusInWindow();
        }
    }
    
    private void enterCampoNome(){
        validarEFechar();
        if(validarNome()){
            txtUsuario.requestFocusInWindow();
        }
    }
    
    private void enterCampoUsuario(){
        validarEFechar();
        if(validarUsuario()){
            txtSenha.requestFocusInWindow();
        }
    }
    
    private void enterCampoSenha(){
        validarEFechar();
        if(validarSenha()){
            txtConfirmarSenha.requestFocusInWindow();
        }
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
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        verificacaoNome = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        verificacaoUsuario = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        verificacaoSenha = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtConfirmarSenha = new javax.swing.JPasswordField();
        verificacaoConfirmacaoSenha = new javax.swing.JPanel();
        checkMaster = new javax.swing.JCheckBox();
        btnSalvar = new javax.swing.JButton();
        txtCpf = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        verificacaoCpf = new javax.swing.JPanel();

        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastrar Usuário", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nome:");

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNome.setToolTipText("");
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout verificacaoNomeLayout = new javax.swing.GroupLayout(verificacaoNome);
        verificacaoNome.setLayout(verificacaoNomeLayout);
        verificacaoNomeLayout.setHorizontalGroup(
            verificacaoNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        verificacaoNomeLayout.setVerticalGroup(
            verificacaoNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        txtUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Usuário:");

        javax.swing.GroupLayout verificacaoUsuarioLayout = new javax.swing.GroupLayout(verificacaoUsuario);
        verificacaoUsuario.setLayout(verificacaoUsuarioLayout);
        verificacaoUsuarioLayout.setHorizontalGroup(
            verificacaoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        verificacaoUsuarioLayout.setVerticalGroup(
            verificacaoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Senha:");

        txtSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSenhaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout verificacaoSenhaLayout = new javax.swing.GroupLayout(verificacaoSenha);
        verificacaoSenha.setLayout(verificacaoSenhaLayout);
        verificacaoSenhaLayout.setHorizontalGroup(
            verificacaoSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        verificacaoSenhaLayout.setVerticalGroup(
            verificacaoSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Confirmar senha:");

        txtConfirmarSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtConfirmarSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConfirmarSenhaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout verificacaoConfirmacaoSenhaLayout = new javax.swing.GroupLayout(verificacaoConfirmacaoSenha);
        verificacaoConfirmacaoSenha.setLayout(verificacaoConfirmacaoSenhaLayout);
        verificacaoConfirmacaoSenhaLayout.setHorizontalGroup(
            verificacaoConfirmacaoSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        verificacaoConfirmacaoSenhaLayout.setVerticalGroup(
            verificacaoConfirmacaoSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        checkMaster.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        checkMaster.setText("Usuário Master");

        btnSalvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        txtCpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCpfKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("CPF:");

        javax.swing.GroupLayout verificacaoCpfLayout = new javax.swing.GroupLayout(verificacaoCpf);
        verificacaoCpf.setLayout(verificacaoCpfLayout);
        verificacaoCpfLayout.setHorizontalGroup(
            verificacaoCpfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        verificacaoCpfLayout.setVerticalGroup(
            verificacaoCpfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(checkMaster)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSalvar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtConfirmarSenha, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSenha, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(verificacaoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(verificacaoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(verificacaoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(verificacaoConfirmacaoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(verificacaoCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 10, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verificacaoCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(verificacaoNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(verificacaoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(verificacaoSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(verificacaoConfirmacaoSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalvar)
                    .addComponent(checkMaster))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        validarEFechar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            enterCampoNome();
        }
    }//GEN-LAST:event_txtNomeKeyReleased

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            enterCampoUsuario();
        }
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void txtSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            enterCampoSenha();
        }
    }//GEN-LAST:event_txtSenhaKeyReleased

    private void txtConfirmarSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmarSenhaKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            validarEFechar();
        }
    }//GEN-LAST:event_txtConfirmarSenhaKeyReleased

    private void txtCpfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            enterCampoCpf();
        }
    }//GEN-LAST:event_txtCpfKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvar;
    public javax.swing.JCheckBox checkMaster;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPasswordField txtConfirmarSenha;
    public javax.swing.JFormattedTextField txtCpf;
    public javax.swing.JTextField txtNome;
    public javax.swing.JPasswordField txtSenha;
    public javax.swing.JTextField txtUsuario;
    private javax.swing.JPanel verificacaoConfirmacaoSenha;
    private javax.swing.JPanel verificacaoCpf;
    private javax.swing.JPanel verificacaoNome;
    private javax.swing.JPanel verificacaoSenha;
    private javax.swing.JPanel verificacaoUsuario;
    // End of variables declaration//GEN-END:variables
}
