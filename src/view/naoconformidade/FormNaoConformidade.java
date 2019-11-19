/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.naoconformidade;

import controller.Controller;
import controller.NaoConformidadeController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import model.NaoConformidade;
import resources.Resources;
import view.Mensagens;

/**
 *
 * 
 */
public class FormNaoConformidade extends javax.swing.JDialog {
    private final Controller controller;
    private final String[] colunas = {"Descrição","Data de Acontecimento","Reincidência","Responsável","Setor","",""};
    private final ArrayList<Integer> listaIdNaoConformidade;
    /**
     * Creates new form NaoConformidade
     * @param parent
     * @param modal
     * @param controller
     */
    public FormNaoConformidade(java.awt.Frame parent, boolean modal, Controller controller) {
        super(parent, modal);
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/logo.png")));
        listaIdNaoConformidade = new ArrayList<>();
        this.controller = controller;
        criarEstruturaEListarTodos();
    }
    
    private DefaultTableModel modelo;
    
   
    private void criarEstruturaEListarTodos(){
        criarEstruturaTabela();
        controller.getNaoConformidadeController().listarTodos(this::popularTabela);
    }
    
    private void criarEstruturaEBuscar(){
        criarEstruturaTabela();
        controller.getNaoConformidadeController().listarPorDescricao(this::popularTabela, txtBusca.getText());
    }
    
    public void criarEstruturaTabela(){
        DefaultTableCellRenderer renderizador = new DefaultTableCellRenderer(){
            @Override
            public void setHorizontalAlignment(int alignment) {
                super.setHorizontalAlignment(CENTER); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
       
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for(String coluna: colunas){
            modelo.addColumn(coluna);
        }
        jtNaoConformidade.setModel(modelo);
        
        Enumeration<TableColumn> columns = jtNaoConformidade.getColumnModel().getColumns();
        while(columns.hasMoreElements()){
            TableColumn nextElement = columns.nextElement();
            nextElement.setCellRenderer(renderizador);
        }
        jtNaoConformidade.setRowHeight(30);
        jtNaoConformidade.getColumnModel().getColumn(5).setMinWidth(30);
        jtNaoConformidade.getColumnModel().getColumn(5).setMaxWidth(30);
        jtNaoConformidade.getColumnModel().getColumn(5).setCellRenderer(
                new IconeCelula(
                        new ImageIcon(
                                getClass().getResource("/imagens/me.png")
                        ),
                        "Visualizar"
                )
        );
        jtNaoConformidade.getColumnModel().getColumn(6).setMinWidth(30);
        jtNaoConformidade.getColumnModel().getColumn(6).setMaxWidth(30);
        jtNaoConformidade.getColumnModel().getColumn(6).setCellRenderer(
                new IconeCelula(
                        new ImageIcon(
                                getClass().getResource("/imagens/teste.png")
                        ),
                        "Gerar relatório"
                )
        );
        listaIdNaoConformidade.clear();
    }
    
    private int pegarIdDaLinhaSelecionada(){
        int linhaSelecionada=jtNaoConformidade.getSelectedRow();
        if(linhaSelecionada>=0){
            return listaIdNaoConformidade.get(linhaSelecionada); 
        }
        return -1;
    }
     
    private void popularTabela(NaoConformidade naoConformidade){
        modelo.addRow(new Object[]{
            naoConformidade.getDescricao(),
            Resources.dataFormatada(naoConformidade.getDataAcontecimento()),
            resources.Resources.converterBooleanoSimOuNaoMaiusculo(naoConformidade.getReincidencia()),
            naoConformidade.getResponsavel().getNome(),
            naoConformidade.getSetor().getNome()
        });
        listaIdNaoConformidade.add(naoConformidade.getId());
    }
    
    private String getNaoConformidadeSelecionada(){
        return jtNaoConformidade.getValueAt(jtNaoConformidade.getSelectedRow(), 0).toString();
    }
    
    private void excluir(){
        if (jtNaoConformidade.getSelectedRow() != -1) {
            if(Mensagens.confirmar(this,"Tem certeza que deseja excluir a não conformidade "+ getNaoConformidadeSelecionada()+"?","Mensagem",Resources.SUCESSO)){
            controller.getNaoConformidadeController().excluir(pegarIdDaLinhaSelecionada());
            criarEstruturaEListarTodos();
            }
            else{
                criarEstruturaEListarTodos();
            }
        }
        else{
            criarEstruturaEListarTodos();
            Mensagens.mensagem(null, "Selecione uma não conformormidade para excluir!","Mensagem",Resources.SUCESSO);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtBusca = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtNaoConformidade = new javax.swing.JTable();
        btnCadastrar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Não Conformidades");
        setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gerenciar Não Conformidade", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        btnEditar.setBackground(new java.awt.Color(255, 255, 255));
        btnEditar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setBackground(new java.awt.Color(255, 255, 255));
        btnExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Não Conformidades cadastradas");

        txtBusca.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Buscar Não Confomidade");

        jtNaoConformidade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Descrição", "Data de Registro", "Dt do Acontecimento", "Reincidência", "Abrangência", "Origem", "Resp. de Qualidade", "Ação de Correção"
            }
        ));
        jtNaoConformidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtNaoConformidadeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtNaoConformidade);

        btnCadastrar.setBackground(new java.awt.Color(255, 255, 255));
        btnCadastrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnCadastrar)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 223, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
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

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        //controller.abreTelaEditarNaoConformidade();
        if(jtNaoConformidade.getSelectedRow() != -1){
           controller.getNaoConformidadeController().editar(pegarIdDaLinhaSelecionada());
        }
        else {
            criarEstruturaEListarTodos();
            Mensagens.mensagem(null, "Selecione uma não conformidade para alterar!","Mensagem",Resources.SUCESSO);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        NaoConformidadeController NaoConformidadeController = new NaoConformidadeController();
        NaoConformidadeController.cadastrar();
        criarEstruturaEListarTodos();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void jtNaoConformidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtNaoConformidadeMouseClicked
        if(jtNaoConformidade.columnAtPoint(evt.getPoint()) == 5){
            controller.getNaoConformidadeController().mostrarNaoConformidade(pegarIdDaLinhaSelecionada());
        }
        if(jtNaoConformidade.columnAtPoint(evt.getPoint()) == 6){
            if(Mensagens.confirmar(this, "Gerar relatório?", "Mensagem", Resources.SUCESSO)){
            controller.getNaoConformidadeController().gerarRelatorioPorId(pegarIdDaLinhaSelecionada());
            }
        }
    }//GEN-LAST:event_jtNaoConformidadeMouseClicked

    private void txtBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyReleased
        criarEstruturaEBuscar();
    }//GEN-LAST:event_txtBuscaKeyReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        criarEstruturaEBuscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jtNaoConformidade;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables

}
class IconeCelula extends DefaultTableCellRenderer{
    private final JLabel label = new JLabel();
    private final ImageIcon icone;
    private final String textoTooltip;
    public IconeCelula(ImageIcon icone, String textoTooltip) {
        this.icone = icone;
            this.textoTooltip = textoTooltip;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        
        Color colorFondo = null;
        Color colorFondoPorDefecto = new Color(192, 192, 192);
        Color colorFondoSeleccion = new Color(140, 140, 140);
        if(selected) {
            this.setBackground(colorFondoPorDefecto);
        } 
        else{
            //Para las que no est�n seleccionadas se pinta el fondo de las celdas de blanco
            this.setBackground(Color.white);
        }
        label.setIcon(icone);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setToolTipText(textoTooltip);
        label.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        return label;
    }
}
