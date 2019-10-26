package view.naoconformidade;

import controller.NaoConformidadeController;
import dao.NaoConformidadeDao;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import model.NaoConformidade;
import model.Responsavel;
import model.Setor;
/**
 *
 * @author Ricardo
 */
public class CadatroNaoCoformidade extends javax.swing.JDialog {
    NaoConformidadeController naoConformidadeController;
    NaoConformidadeDao naoConformidadeDao = new NaoConformidadeDao();
    
    private ArrayList<Integer> listaIdResponsavelComboBox;
    private ArrayList<Integer> listaIdSetorComboBox;
    
    
    
    /** Creates new form cadatroNaoCoformidade
     * @param parent
     * @param modal
     * @param naoConformidadecontroller */
    public CadatroNaoCoformidade(java.awt.Frame parent, boolean modal, NaoConformidadeController naoConformidadecontroller) {
        super(parent, modal);
        initComponents();
        this.naoConformidadeController = naoConformidadecontroller;
        listaIdResponsavelComboBox = new ArrayList<>();
        listaIdSetorComboBox = new ArrayList<>();
        //prencheer();
        inicializar();
    }
    ArrayList<NaoConformidade> ResponsavelNC = new ArrayList<>();

    public ArrayList<NaoConformidade> getResponsavelNaoConformidade() {
        return ResponsavelNC;
    }
   
    public void setResponsavel(ArrayList<NaoConformidade> ResponsavelNaoConformidade) {
        this.ResponsavelNC = ResponsavelNaoConformidade;
    }
    
    ArrayList<NaoConformidade> SetorNC = new ArrayList<>();

    public ArrayList<NaoConformidade> getSetorNaoConformidade() {
        return SetorNC;
    }
   
    public void setSetor(ArrayList<NaoConformidade> SetorNaoConformidade) {
        this.SetorNC = SetorNaoConformidade;
    }
    
    
    
    private void inicializar(){
        listarResponsaveis();
        listarSetores();
        Codigo.setText(String.format("%010d", naoConformidadeController.getLastId()+1));
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
    
    
    private int pegarIdSetor(){
        return listaIdSetorComboBox.get(Setor.getSelectedIndex());
    }
    
    private int pegarIdResponsavel(){
        return listaIdResponsavelComboBox.get(Responsavel.getSelectedIndex());
    }
    
    public boolean validarDados(){
        if(!naoConformidadeController.validarTexto(descricao.getText())){
                descricao.requestFocus();
                naoConformidadeController.obrigatorio(this);
                return true;
            }
        else if(!naoConformidadeController.validarTexto(abrangencia.getText())){
                abrangencia.requestFocus();
                naoConformidadeController.obrigatorio(this);
                return true;
            }
        else if(!naoConformidadeController.validarTexto(origem.getText())){
                origem.requestFocus();
                naoConformidadeController.obrigatorio(this);
                return true;
            }
        else if(!naoConformidadeController.validarTexto(acaoCorrecao.getText())){
                acaoCorrecao.requestFocus();
                naoConformidadeController.obrigatorio(this);
                return true;
            }
        return false;
    }  
    
    private void escolherImagem(){
        naoConformidadeController.getImagemController().escolherImagem();
        visualizaImg.setIcon(
                naoConformidadeController.getImagemController().lerImagem(
                        visualizaImg.getWidth(), 
                        visualizaImg.getHeight())
        );
        
    }
    
    public void salvar(){
        if(!validarDados()){
            naoConformidadeController.salvar(new NaoConformidade(
                    0, //o id não será usado
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
    
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelx = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
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
        jLabel3 = new javax.swing.JLabel();
        Setor = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro de Não Conformidade", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Código");
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Descrição");
        jLabel2.setName("jLabel2"); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        labelx.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelx.setText("Data de Registro");
        labelx.setName("labelx"); // NOI18N
        labelx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelxMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Data do Acontecimento");
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Abrangência");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Origem");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Responsável de Qualidade");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Ação de Correção");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Imagem");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        btnSalvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setName("btnSalvar"); // NOI18N
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        Codigo.setEditable(false);
        Codigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Codigo.setName("Codigo"); // NOI18N

        descricao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descricao.setName("descricao"); // NOI18N
        descricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                descricaoKeyPressed(evt);
            }
        });

        abrangencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        abrangencia.setName("abrangencia"); // NOI18N
        abrangencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                abrangenciaKeyPressed(evt);
            }
        });

        origem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        origem.setName("origem"); // NOI18N
        origem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                origemKeyPressed(evt);
            }
        });

        Responsavel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Responsavel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione...", "teste", "talvez" }));
        Responsavel.setName("Responsavel"); // NOI18N
        Responsavel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ResponsavelKeyPressed(evt);
            }
        });

        acaoCorrecao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        acaoCorrecao.setName("acaoCorrecao"); // NOI18N
        acaoCorrecao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                acaoCorrecaoKeyPressed(evt);
            }
        });

        btnImg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnImg.setText("Selecione um arquivo");
        btnImg.setName("btnImg"); // NOI18N
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
        reincidencia.setName("reincidencia"); // NOI18N
        reincidencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                reincidenciaKeyPressed(evt);
            }
        });

        visualizaImg.setName("visualizaImg"); // NOI18N

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
    btnCancelar.setName("btnCancelar"); // NOI18N
    btnCancelar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCancelarActionPerformed(evt);
        }
    });

    jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel3.setText("Setor");
    jLabel3.setName("jLabel3"); // NOI18N

    Setor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    Setor.setName("Setor"); // NOI18N

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(35, 35, 35)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel9)
                .addComponent(jLabel7)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(labelx)
                    .addGap(59, 59, 59)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dataAcontecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addComponent(jLabel2)
                .addComponent(dataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel8)
                .addComponent(jLabel1)
                .addComponent(acaoCorrecao, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(origem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(abrangencia, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descricao, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Codigo, javax.swing.GroupLayout.Alignment.LEADING))
                .addComponent(Responsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(reincidencia))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addGap(205, 205, 205))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(51, 51, 51)
                                    .addComponent(btnImg)))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Setor, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(visualizaImg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                            .addContainerGap(38, Short.MAX_VALUE))))))
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(296, 296, 296)
            .addComponent(btnSalvar)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btnCancelar)
            .addGap(0, 0, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel10))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2)
                    .addGap(4, 4, 4)
                    .addComponent(descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelx)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dataAcontecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(abrangencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(origem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel8)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Responsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel9)
                    .addGap(3, 3, 3)
                    .addComponent(acaoCorrecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(visualizaImg, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(reincidencia)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Setor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(59, 59, 59)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnSalvar)
                .addComponent(btnCancelar)))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        descricao.requestFocusInWindow();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void labelxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelxMouseClicked
        labelx.requestFocusInWindow();
    }//GEN-LAST:event_labelxMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        dataAcontecimento.requestFocusInWindow();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        abrangencia.requestFocusInWindow();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        origem.requestFocusInWindow();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        Responsavel.requestFocusInWindow();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        acaoCorrecao.requestFocusInWindow();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        btnImg.requestFocusInWindow();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        System.out.println(dataRegistro.getCurrent().getTime());
        if(!validarDados()){
           salvar();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

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
            reincidencia.requestFocusInWindow();
        }
    }//GEN-LAST:event_ResponsavelKeyPressed

    private void acaoCorrecaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_acaoCorrecaoKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            if(!naoConformidadeController.validarTexto(acaoCorrecao.getText())){
                naoConformidadeController.obrigatorio(this);
            }
            else{
                btnImg.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_acaoCorrecaoKeyPressed

    private void btnImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImgActionPerformed
        escolherImagem();
    }//GEN-LAST:event_btnImgActionPerformed

    private void btnImgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnImgKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            btnSalvar.requestFocusInWindow();
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField Codigo;
    public javax.swing.JComboBox<String> Responsavel;
    public javax.swing.JComboBox<String> Setor;
    public javax.swing.JTextField abrangencia;
    public javax.swing.JTextField acaoCorrecao;
    private javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnImg;
    public javax.swing.JButton btnSalvar;
    public datechooser.beans.DateChooserCombo dataAcontecimento;
    public datechooser.beans.DateChooserCombo dataRegistro;
    public javax.swing.JTextField descricao;
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
    private javax.swing.JLabel labelx;
    public javax.swing.JTextField origem;
    public javax.swing.JCheckBox reincidencia;
    private javax.swing.JLabel visualizaImg;
    // End of variables declaration//GEN-END:variables
}
