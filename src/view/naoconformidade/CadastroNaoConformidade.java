/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.naoconformidade;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import datechooser.beans.DateChooserCombo;

/**
 *
 * @author Eduardo
 */
public class CadastroNaoConformidade extends javax.swing.JDialog {

    /**
     * Creates new form CadastroNaoConformidade
     * @param parent
     * @param modal
     */
    public CadastroNaoConformidade(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        Codigo = new javax.swing.JTextField();
        Descricao = new javax.swing.JTextField();
        Abrangencia = new javax.swing.JTextField();
        Origem = new javax.swing.JTextField();
        Responsavel = new javax.swing.JComboBox<>();
        AcaoCorrecao = new javax.swing.JTextField();
        btnImg = new javax.swing.JButton();
        Reincidencia = new javax.swing.JCheckBox();
        visualizaImg = new javax.swing.JLabel();
        dataRegistro = new datechooser.beans.DateChooserCombo();
        dataAcontecimento = new datechooser.beans.DateChooserCombo();
        jButton1 = new javax.swing.JButton();

        jTextField3.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro de Não Conformidade", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Código");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Descrição");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Data de Registro");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Data do Acontecimento");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Abrangência");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Origem");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Responsável de Qualidade");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Ação de Correção");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Imagem");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        btnSalvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalvar.setText("Salvar");

        Codigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CodigoKeyReleased(evt);
            }
        });

        Descricao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Descricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DescricaoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DescricaoKeyReleased(evt);
            }
        });

        Abrangencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Abrangencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AbrangenciaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AbrangenciaKeyReleased(evt);
            }
        });

        Origem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Origem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OrigemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                OrigemKeyReleased(evt);
            }
        });

        Responsavel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Responsavel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione..." }));
        Responsavel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ResponsavelKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ResponsavelKeyReleased(evt);
            }
        });

        AcaoCorrecao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AcaoCorrecao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcaoCorrecaoActionPerformed(evt);
            }
        });
        AcaoCorrecao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AcaoCorrecaoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AcaoCorrecaoKeyReleased(evt);
            }
        });

        btnImg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnImg.setText("Selecione um arquivo");
        btnImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImgActionPerformed(evt);
            }
        });

        Reincidencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Reincidencia.setText("Reincidência");
        Reincidencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ReincidenciaKeyReleased(evt);
            }
        });

        dataRegistro.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));

        dataAcontecimento.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel2)
                    .addComponent(Reincidencia)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(63, 63, 63))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(dataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(10, 10, 10))
                            .addComponent(dataAcontecimento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(86, 86, 86)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(btnImg)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Origem)
                                .addComponent(Abrangencia)
                                .addComponent(Responsavel, 0, 320, Short.MAX_VALUE)
                                .addComponent(AcaoCorrecao)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(visualizaImg, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataAcontecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Abrangencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Origem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Responsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AcaoCorrecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(visualizaImg, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Reincidencia)
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AcaoCorrecaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcaoCorrecaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AcaoCorrecaoActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        btnImg.requestFocusInWindow();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        AcaoCorrecao.requestFocusInWindow();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        Responsavel.requestFocusInWindow();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        Origem.requestFocusInWindow();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        Abrangencia.requestFocusInWindow();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        dataAcontecimento.requestFocusInWindow();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        dataRegistro.requestFocusInWindow();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        Descricao.requestFocusInWindow();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        Codigo.requestFocusInWindow();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImgActionPerformed
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i= file.showOpenDialog(null);
        if (i==1){
            System.out.println("Nada pegado!");
            visualizaImg.setIcon(null);
        } else {
            File arquivo = file.getSelectedFile();
            System.out.println((arquivo.getPath()));
            BufferedImage imagem = null;
            try {
                imagem = ImageIO.read(arquivo);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
            
            float larguraAux = imagem.getWidth() / 300;
            float alturaAux = imagem.getHeight() / 300;
            int largura = 300;
            int altura = 300;
            int larguraFinal;
            int alturaFinal;

            BufferedImage new_img = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 300, 300);

            //primeira divisão
            if(imagem.getWidth() > imagem.getHeight()){ // se a largura é maior que a altura, usa a constante de calculo da largura
                larguraFinal = (int)(imagem.getWidth()/larguraAux);
                alturaFinal = (int) (imagem.getHeight()/larguraAux);
            }
            else { //se não, usa a constante de cálculo da altura
                larguraFinal = (int)(imagem.getWidth()/alturaAux);
                alturaFinal = (int) (imagem.getHeight()/alturaAux);
            }
    
            g.drawImage(imagem, (largura - larguraFinal)/2 ,(altura - alturaFinal)/2, larguraFinal, alturaFinal, null);
            ImageIcon icon = new ImageIcon(new_img);
            visualizaImg.setIcon(icon);
            
            /*try {
                String aux= "D:\\Desktop\\teste.png";
                ImageIO.write(new_img, "png", new File(aux));
                ImageIcon icon = new ImageIcon(new_img);
                visualizaImg.setIcon(icon);
                visualizaImg.setText("");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }*/
        }
          
    }//GEN-LAST:event_btnImgActionPerformed

    private void CodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CodigoKeyReleased
         if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            Descricao.requestFocusInWindow();
        }
    }//GEN-LAST:event_CodigoKeyReleased

    private void DescricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DescricaoKeyReleased
         if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            Reincidencia.requestFocusInWindow();
        }
    }//GEN-LAST:event_DescricaoKeyReleased

    private void ReincidenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ReincidenciaKeyReleased
         if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            Abrangencia.requestFocusInWindow();
        }
    }//GEN-LAST:event_ReincidenciaKeyReleased

    private void OrigemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OrigemKeyReleased
         if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            Responsavel.requestFocusInWindow();
        }
    }//GEN-LAST:event_OrigemKeyReleased

    private void AbrangenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AbrangenciaKeyReleased
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            Origem.requestFocusInWindow();
        }
    }//GEN-LAST:event_AbrangenciaKeyReleased

    private void ResponsavelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ResponsavelKeyReleased
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            AcaoCorrecao.requestFocusInWindow();
        }
    }//GEN-LAST:event_ResponsavelKeyReleased

    private void AcaoCorrecaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AcaoCorrecaoKeyReleased
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            btnImg.requestFocusInWindow();
        }
    }//GEN-LAST:event_AcaoCorrecaoKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void DescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DescricaoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DescricaoKeyPressed

    private void AbrangenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AbrangenciaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AbrangenciaKeyPressed

    private void OrigemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OrigemKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_OrigemKeyPressed

    private void ResponsavelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ResponsavelKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ResponsavelKeyPressed

    private void AcaoCorrecaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AcaoCorrecaoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AcaoCorrecaoKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroNaoConformidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroNaoConformidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroNaoConformidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroNaoConformidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroNaoConformidade dialog = new CadastroNaoConformidade(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Abrangencia;
    private javax.swing.JTextField AcaoCorrecao;
    private javax.swing.JTextField Codigo;
    private javax.swing.JTextField Descricao;
    private javax.swing.JTextField Origem;
    private javax.swing.JCheckBox Reincidencia;
    private javax.swing.JComboBox<String> Responsavel;
    private javax.swing.JButton btnImg;
    private javax.swing.JButton btnSalvar;
    private datechooser.beans.DateChooserCombo dataAcontecimento;
    private datechooser.beans.DateChooserCombo dataRegistro;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel visualizaImg;
    // End of variables declaration//GEN-END:variables
}
