/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import resources.Resources;

/**
 *
 * @author Ricardo
 */
public class FormAjuda extends javax.swing.JDialog {

    /**
     * Creates new form Help
     */
    public FormAjuda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/logo.png")));
        setLocationRelativeTo(parent);
    }
    
    @Override
    protected JRootPane createRootPane() {
        // Definindo o ActionListener
        ActionListener acaoEsc = (ActionEvent e) -> {
            dispose();
        };
        // Definindo o KeyStroke
        KeyStroke strokeEsc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        // Criando uma instancia de JRootPane
        JRootPane rootPane = new JRootPane();
        // Registrando o KeyStroke enquanto o JDialog estiver em foco
        rootPane.registerKeyboardAction(acaoEsc, strokeEsc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        // Retornando o novo e modificado JRootPane
        return rootPane;
    }
    
    private void cadastrar(String lbl, boolean generoMensagem){
        if(generoMensagem){
            Mensagens.mensagem(
                    this, 
                    "Para cadastrar uma " +lbl+ " no sistema, é necessário que você acesse o menu principal do sistema e vá em "+lbl+","
                            + "\nfeito isso, em Cadastrar "+lbl+", faça o preenchimento dos dados e clique em Salvar.", 
                    "Mensagem", 
                    Resources.SUCESSO
            );
        }
        else{
            Mensagens.mensagem(
                    this, 
                    "Para cadastrar um " +lbl+ " no sistema, é necessário que você acesse o menu principal do sistema e vá em "+lbl+","
                            + "\nfeito isso, em Cadastrar "+lbl+", faça o preenchimento dos dados e clique em Salvar.", 
                    "Mensagem", 
                    Resources.SUCESSO
            );
        }
    }
    
    private void editar(String lbl,  boolean generoMensagem){
        if(generoMensagem){
            Mensagens.mensagem(
                    this, 
                    "Para editar uma "+lbl+" no sistema, é necessário que você acesse o menu principal do sistema e vá em "+lbl+", "
                            + "\nfeito isso, selecione a "+lbl+" desejada na tabela de listagem e clique em editar. Depois, faça a edição desejada e clique em Salvar.", 
                    "Mensagem", 
                    Resources.SUCESSO
            );
        }
        else{
             Mensagens.mensagem(
                    this, 
                    "Para editar um "+lbl+" no sistema, é necessário que você acesse o menu principal do sistema e vá em "+lbl+", "
                            + "\nfeito isso, selecione o "+lbl+" desejado na tabela de listagem e clique em editar. Depois, faça a edição desejada e clique em Salvar.", 
                    "Mensagem", 
                    Resources.SUCESSO
            );
        }
    }
    
    private void excluir(String lbl, boolean generoMensagem){
        if(generoMensagem){
            Mensagens.mensagem(
                    this, 
                    "Para excluir uma "+lbl+" no sistema, é necessário que você acesse o menu principal do sistema e vá em "+lbl+", "
                            + "\nfeito isso, selecione a "+lbl+" desejada na tabela de listagem e clique em excluir.", 
                    "Mensagem", 
                    Resources.SUCESSO
            );
        }
        else{
            Mensagens.mensagem(
                    this, 
                    "Para excluir um "+lbl+" no sistema, é necessário que você acesse o menu principal do sistema e vá em "+lbl+", "
                            + "\nfeito isso, selecione o "+lbl+" desejado na tabela de listagem e clique em excluir.", 
                    "Mensagem", 
                    Resources.SUCESSO
            );
        }
    }
    
    private void individual(){
        Mensagens.mensagem(
                this, 
                "Para gerar um relatório individual no sistema, é necessário que você acesse o menu principal do sistema e vá em Não Conformidade, "
                        + "\nfeito isso, escolha uma Não Conformidade na tabela de listagem, clique no ícone gerar relatório e confirme.\n",
                "Mensagem", 
                Resources.SUCESSO
        );
    }
    
     private void periodo(){
        Mensagens.mensagem(
                this, 
                "Para gerar relatórios por período no sistema, é necessário que você acesse o menu principal do sistema e vá em relatórios,"
                        + "\nfeito isso, escolha um período e clique em Gerar relatório.", 
                "Mensagem", 
                Resources.SUCESSO
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        altUsu = new javax.swing.JButton();
        altRes = new javax.swing.JButton();
        altSet = new javax.swing.JButton();
        altNc = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        RelPer = new javax.swing.JButton();
        RelInd = new javax.swing.JButton();
        edtUsu = new javax.swing.JButton();
        edtRes = new javax.swing.JButton();
        edtNc = new javax.swing.JButton();
        edtSet = new javax.swing.JButton();
        delUsu = new javax.swing.JButton();
        delRes = new javax.swing.JButton();
        delNc = new javax.swing.JButton();
        delSet = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");
        jLabel1.setName("jLabel1"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ajuda");
        setResizable(false);

        jPanel1.setName("jPanel1"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Gerenciar Usuários(F1)");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Gerenciar Responsáveis(F2)");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Gerenciar Não Conformidades(F3)");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Gerenciar Setores(F4)");
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Gerar Relatórios(F5)");
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Sair ou fechar a tela atual(ESC)");
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Como Cadastrar?");
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Como Excluir?");
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Como Editar?");
        jLabel10.setName("jLabel10"); // NOI18N

        altUsu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        altUsu.setText("Usuário");
        altUsu.setName("altUsu"); // NOI18N
        altUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altUsuActionPerformed(evt);
            }
        });

        altRes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        altRes.setText("Responsável");
        altRes.setName("altRes"); // NOI18N
        altRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altResActionPerformed(evt);
            }
        });

        altSet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        altSet.setText("Setor");
        altSet.setName("altSet"); // NOI18N
        altSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altSetActionPerformed(evt);
            }
        });

        altNc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        altNc.setText("Não Conformidade");
        altNc.setName("altNc"); // NOI18N
        altNc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altNcActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Como Gerar Relatórios?");
        jLabel11.setName("jLabel11"); // NOI18N

        RelPer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        RelPer.setText("Por período");
        RelPer.setName("RelPer"); // NOI18N
        RelPer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RelPerActionPerformed(evt);
            }
        });

        RelInd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        RelInd.setText("Individual");
        RelInd.setName("RelInd"); // NOI18N
        RelInd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RelIndActionPerformed(evt);
            }
        });

        edtUsu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edtUsu.setText("Usuário");
        edtUsu.setName("edtUsu"); // NOI18N
        edtUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtUsuActionPerformed(evt);
            }
        });

        edtRes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edtRes.setText("Responsável");
        edtRes.setName("edtRes"); // NOI18N
        edtRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtResActionPerformed(evt);
            }
        });

        edtNc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edtNc.setText("Não Conformidade");
        edtNc.setName("edtNc"); // NOI18N
        edtNc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtNcActionPerformed(evt);
            }
        });

        edtSet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edtSet.setText("Setor");
        edtSet.setName("edtSet"); // NOI18N
        edtSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtSetActionPerformed(evt);
            }
        });

        delUsu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        delUsu.setText("Usuário");
        delUsu.setName("delUsu"); // NOI18N
        delUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delUsuActionPerformed(evt);
            }
        });

        delRes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        delRes.setText("Responsável");
        delRes.setName("delRes"); // NOI18N
        delRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delResActionPerformed(evt);
            }
        });

        delNc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        delNc.setText("Não Conformidade");
        delNc.setName("delNc"); // NOI18N
        delNc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delNcActionPerformed(evt);
            }
        });

        delSet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        delSet.setText("Setor");
        delSet.setName("delSet"); // NOI18N
        delSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delSetActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Teclas de atalho:");
        jLabel12.setName("jLabel12"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(altUsu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(altRes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(altNc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(altSet))
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(edtUsu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtRes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtNc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtSet))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(RelInd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(delUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(delRes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(RelPer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delNc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delSet))
                    .addComponent(jLabel12))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {altNc, altRes, altSet, altUsu});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {delNc, delRes, delSet, delUsu});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {edtNc, edtRes, edtSet, edtUsu});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(altUsu)
                    .addComponent(altRes)
                    .addComponent(altNc)
                    .addComponent(altSet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtUsu)
                    .addComponent(edtRes)
                    .addComponent(edtNc)
                    .addComponent(edtSet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delUsu)
                    .addComponent(delRes)
                    .addComponent(delNc)
                    .addComponent(delSet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RelInd)
                    .addComponent(RelPer))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void RelPerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RelPerActionPerformed
        periodo();
    }//GEN-LAST:event_RelPerActionPerformed

    private void altUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altUsuActionPerformed
        cadastrar(altUsu.getText(), false);
    }//GEN-LAST:event_altUsuActionPerformed

    private void altResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altResActionPerformed
        cadastrar(altRes.getText(), false);
    }//GEN-LAST:event_altResActionPerformed

    private void altNcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altNcActionPerformed
        cadastrar(altNc.getText(), true);
    }//GEN-LAST:event_altNcActionPerformed

    private void altSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altSetActionPerformed
        cadastrar(altSet.getText(), false);
    }//GEN-LAST:event_altSetActionPerformed

    private void edtUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtUsuActionPerformed
        editar(edtUsu.getText(), false);
    }//GEN-LAST:event_edtUsuActionPerformed

    private void edtResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtResActionPerformed
        editar(edtRes.getText(), false);
    }//GEN-LAST:event_edtResActionPerformed

    private void edtNcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtNcActionPerformed
        editar(edtNc.getText(), true);
    }//GEN-LAST:event_edtNcActionPerformed

    private void edtSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtSetActionPerformed
        editar(edtSet.getText(), false);
    }//GEN-LAST:event_edtSetActionPerformed

    private void delUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delUsuActionPerformed
        excluir(delUsu.getText(), false);
    }//GEN-LAST:event_delUsuActionPerformed

    private void delResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delResActionPerformed
        excluir(delRes.getText(), false);
    }//GEN-LAST:event_delResActionPerformed

    private void delNcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delNcActionPerformed
        excluir(delNc.getText(), true);
    }//GEN-LAST:event_delNcActionPerformed

    private void delSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delSetActionPerformed
        excluir(delSet.getText(), false);
    }//GEN-LAST:event_delSetActionPerformed

    private void RelIndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RelIndActionPerformed
        individual();
    }//GEN-LAST:event_RelIndActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RelInd;
    private javax.swing.JButton RelPer;
    private javax.swing.JButton altNc;
    private javax.swing.JButton altRes;
    private javax.swing.JButton altSet;
    private javax.swing.JButton altUsu;
    private javax.swing.JButton delNc;
    private javax.swing.JButton delRes;
    private javax.swing.JButton delSet;
    private javax.swing.JButton delUsu;
    private javax.swing.JButton edtNc;
    private javax.swing.JButton edtRes;
    private javax.swing.JButton edtSet;
    private javax.swing.JButton edtUsu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
