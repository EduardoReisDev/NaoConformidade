/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.naoconformidade;

import controller.NaoConformidadeController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import model.NaoConformidade;
import model.Responsavel;
import model.Setor;
import resources.Imagem;

/**
 *
 * @author Eduardo
 */
public class FormEditarNaoConformidade extends javax.swing.JDialog {
    NaoConformidadeController naoConformidadeController;
    NaoConformidade aux;
    private ArrayList<Integer> listaIdResponsavelComboBox;
    private ArrayList<Integer> listaIdSetorComboBox;
    /**
     * Creates new form EditarNaoConformidade
     */
    public FormEditarNaoConformidade(java.awt.Frame parent, boolean modal,NaoConformidadeController naoConformidadecontroller, int id){
        super(parent, modal);
        initComponents();
        this.naoConformidadeController = naoConformidadecontroller;
        listaIdResponsavelComboBox = new ArrayList<>();
        listaIdSetorComboBox = new ArrayList<>();
        inicializar();
        setarDados(id);
        descricao.requestFocusInWindow();
    }
    private void setarDados(int id){
        Imagem img = new Imagem();
        aux = naoConformidadeController.listarPorId(id);
        Codigo.setText(String.valueOf(aux.getId()));
        descricao.setText(aux.getDescricao());
        abrangencia.setText(aux.getAbrangencia());
        origem.setText(aux.getOrigem());
        acaoCorrecao.setText(aux.getAcaoCorrecao());
        reincidencia.setSelected(aux.isReincidencia());
        Calendar cal = Calendar.getInstance(TimeZone.getDefault()); 
        cal.set(aux.getDataAcontecimento().getYear(), aux.getDataAcontecimento().getMonth(), aux.getDataAcontecimento().getDate());
        dataAcontecimento.setSelectedDate(cal);
        cal.set(aux.getDataRegistro().getYear(), aux.getDataRegistro().getMonth(), aux.getDataRegistro().getDate());
        dataRegistro.setSelectedDate(cal);
        if(aux.getImagem()!=null){
           visualizaImg.setIcon(img.lerImagem(aux.getImagem(),visualizaImg.getWidth() ,visualizaImg.getHeight())); 
        }
        else{
            visualizaImg.setIcon(null);
        }
        Responsavel.setSelectedIndex(listaIdResponsavelComboBox.indexOf(aux.getResponsavel().getId()));
        Setor.setSelectedIndex(listaIdSetorComboBox.indexOf(aux.getSetor().getId()));
    
    }
    private void inicializar(){
        listarResponsaveis();
        listarSetores();
    }
    
    private void listarSetores(){
        Setor.removeAllItems();
        naoConformidadeController.listarTodosSetores(this::popularComboBoxSetor);
    }
    
    private void listarResponsaveis(){
        Responsavel.removeAllItems();
        naoConformidadeController.listarTodosResponsaveis(this::popularComboboxResponsavel);
    }
    
    
    private void popularComboBoxSetor(Setor setor){
        Setor.addItem(setor.getNome());
        listaIdSetorComboBox.add(setor.getId());
    }
    
    private void popularComboboxResponsavel(Responsavel responsavel){
        Responsavel.addItem(responsavel.getNome());
        listaIdResponsavelComboBox.add(responsavel.getId());
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
    
    private int pegarIdSetor(){
        return listaIdSetorComboBox.get(Setor.getSelectedIndex());
    }
    
    private int pegarIdResponsavel(){
        return listaIdResponsavelComboBox.get(Responsavel.getSelectedIndex());
    }
    
    public boolean validarDados(){
        if(!naoConformidadeController.validarTexto(descricao.getText())){
                descricao.requestFocusInWindow();
                naoConformidadeController.obrigatorio(this);
                return true;
            }
        else if(!naoConformidadeController.validarTexto(abrangencia.getText())){
                abrangencia.requestFocusInWindow();
                naoConformidadeController.obrigatorio(this);
                return true;
            }
        else if(!naoConformidadeController.validarTexto(origem.getText())){
                origem.requestFocusInWindow();
                naoConformidadeController.obrigatorio(this);
                return true;
            }
        else if(!naoConformidadeController.validarTexto(acaoCorrecao.getText())){
                acaoCorrecao.requestFocusInWindow();
                naoConformidadeController.obrigatorio(this);
                return true;
            }
        return false;
    }  
    
    public void atualizar(){
        if(!validarDados()){
            naoConformidadeController.atualizar(new NaoConformidade(
                    Integer.parseInt(Codigo.getText()), //o id não será usado
                    abrangencia.getText(), 
                    acaoCorrecao.getText(), 
                    dataAcontecimento.getCurrent().getTime(), 
                    dataRegistro.getCurrent().getTime(), 
                    descricao.getText(), 
                    System.getProperty("user.dir")+"\\imagens\\nc"+Codigo.getText()+".png", 
                    origem.getText(), 
                    reincidencia.isSelected(), 
                    new Setor(
                            pegarIdSetor()
                    ), 
                    new Responsavel(
                            pegarIdResponsavel()
                    )
            ));
        }
    }
    
     private void escolherImagem(){
        naoConformidadeController.getImagem().escolherImagem();
        visualizaImg.setIcon(
                naoConformidadeController.getImagem().lerImagem(
                        visualizaImg.getWidth(), 
                        visualizaImg.getHeight())
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnAtualizar = new javax.swing.JButton();
        Codigo = new javax.swing.JTextField();
        descricao = new javax.swing.JTextField();
        abrangencia = new javax.swing.JTextField();
        origem = new javax.swing.JTextField();
        Responsavel = new javax.swing.JComboBox<>();
        acaoCorrecao = new javax.swing.JTextField();
        btnImg = new javax.swing.JButton();
        reincidencia = new javax.swing.JCheckBox();
        visualizaImg = new javax.swing.JLabel();
        dataRegistro = new datechooser.beans.DateChooserCombo();
        dataAcontecimento = new datechooser.beans.DateChooserCombo();
        btnCancelar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        Setor = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Editar Não Conformidade", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

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

        label3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label3.setText("Data de Registro");
        label3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label3MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Data do Acontecimento");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Abrangência");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Origem");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Responsável de Qualidade");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Ação de Correção");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Imagem");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        btnAtualizar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        Codigo.setEditable(false);
        Codigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        descricao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                descricaoKeyPressed(evt);
            }
        });

        abrangencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        abrangencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                abrangenciaKeyPressed(evt);
            }
        });

        origem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        origem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                origemKeyPressed(evt);
            }
        });

        Responsavel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Responsavel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione...", "teste", "talvez" }));
        Responsavel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResponsavelActionPerformed(evt);
            }
        });
        Responsavel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ResponsavelKeyPressed(evt);
            }
        });

        acaoCorrecao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        acaoCorrecao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                acaoCorrecaoKeyPressed(evt);
            }
        });

        btnImg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnImg.setText("Selecione um arquivo");
        btnImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImgActionPerformed(evt);
            }
        });
        btnImg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnImgKeyPressed(evt);
            }
        });

        reincidencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        reincidencia.setText("Reincidência");
        reincidencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                reincidenciaKeyPressed(evt);
            }
        });

        dataRegistro.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    dataRegistro.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));

    dataAcontecimento.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));

    btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    btnCancelar.setText("Cancelar");
    btnCancelar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCancelarActionPerformed(evt);
        }
    });

    jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel10.setText("Setor");

    Setor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    Setor.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            SetorKeyPressed(evt);
        }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(296, 296, 296)
            .addComponent(btnAtualizar)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btnCancelar)
            .addGap(0, 0, Short.MAX_VALUE))
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(35, 35, 35)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Setor, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8)
                        .addComponent(jLabel6)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(label3)
                            .addGap(59, 59, 59)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dataAcontecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)))
                        .addComponent(jLabel2)
                        .addComponent(dataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel1)
                        .addComponent(acaoCorrecao, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(origem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addComponent(abrangencia, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descricao, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Codigo, javax.swing.GroupLayout.Alignment.LEADING))
                        .addComponent(Responsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(visualizaImg, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 38, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9)
                            .addGap(171, 171, 171))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(86, 86, 86)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(reincidencia)
                                .addComponent(btnImg))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(reincidencia))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel2)
                .addComponent(jLabel9))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dataAcontecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(abrangencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(origem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Responsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel8)
                    .addGap(3, 3, 3)
                    .addComponent(acaoCorrecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel10)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Setor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(visualizaImg, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(64, 64, 64)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAtualizar)
                .addComponent(btnCancelar)))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        descricao.requestFocusInWindow();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void label3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label3MouseClicked
        dataRegistro.requestFocusInWindow();
    }//GEN-LAST:event_label3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        dataAcontecimento.requestFocusInWindow();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        abrangencia.requestFocusInWindow();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        origem.requestFocusInWindow();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        Responsavel.requestFocusInWindow();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        acaoCorrecao.requestFocusInWindow();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        btnImg.requestFocusInWindow();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        if(!validarDados()){
            atualizar();
        }
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void descricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descricaoKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            if(!naoConformidadeController.validarTexto(descricao.getText())){
                naoConformidadeController.obrigatorio(this);
            }
            else{
                abrangencia.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_descricaoKeyPressed

    private void abrangenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_abrangenciaKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            if(!naoConformidadeController.validarTexto(abrangencia.getText())){
                naoConformidadeController.obrigatorio(this);
            }
            else{
                origem.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_abrangenciaKeyPressed

    private void origemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_origemKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            if(!naoConformidadeController.validarTexto(origem.getText())){
                naoConformidadeController.obrigatorio(this);
            }
            else{
                Responsavel.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_origemKeyPressed

    private void ResponsavelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ResponsavelKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            acaoCorrecao.requestFocusInWindow();
        }
    }//GEN-LAST:event_ResponsavelKeyPressed

    private void acaoCorrecaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_acaoCorrecaoKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            if(!naoConformidadeController.validarTexto(acaoCorrecao.getText())){
                naoConformidadeController.obrigatorio(this);
            }
            else{
                Setor.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_acaoCorrecaoKeyPressed

    private void btnImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImgActionPerformed
        escolherImagem();
    }//GEN-LAST:event_btnImgActionPerformed

    private void btnImgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnImgKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            btnAtualizar.requestFocusInWindow();
        }
    }//GEN-LAST:event_btnImgKeyPressed

    private void reincidenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reincidenciaKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            acaoCorrecao.requestFocusInWindow();
        }
    }//GEN-LAST:event_reincidenciaKeyPressed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void ResponsavelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResponsavelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ResponsavelActionPerformed

    private void SetorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SetorKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            reincidencia.requestFocusInWindow();
        }
    }//GEN-LAST:event_SetorKeyPressed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField Codigo;
    public javax.swing.JComboBox<String> Responsavel;
    public javax.swing.JComboBox<String> Setor;
    public javax.swing.JTextField abrangencia;
    public javax.swing.JTextField acaoCorrecao;
    public javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnImg;
    public datechooser.beans.DateChooserCombo dataAcontecimento;
    public datechooser.beans.DateChooserCombo dataRegistro;
    public javax.swing.JTextField descricao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel label3;
    public javax.swing.JTextField origem;
    public javax.swing.JCheckBox reincidencia;
    private javax.swing.JLabel visualizaImg;
    // End of variables declaration//GEN-END:variables
}
