package view.naoconformidade;

import controller.NaoConformidadeController;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import model.NaoConformidade;
import model.Responsavel;
import model.Setor;
/**
 *
 * @author Ricardo
 */
public class FormCadastroNaoCoformidade extends javax.swing.JDialog {
    private final NaoConformidadeController naoConformidadeController;
    private final ArrayList<Integer> listaIdResponsavelComboBox;
    private final ArrayList<Integer> listaIdSetorComboBox;
    private FrameImagem frameImagem;
    
    /** Creates new form cadatroNaoCoformidade
     * @param parent
     * @param modal
     * @param naoConformidadeController */
    public FormCadastroNaoCoformidade(java.awt.Frame parent, boolean modal, NaoConformidadeController naoConformidadeController) {
        super(parent, modal);
        initComponents();
        this.naoConformidadeController = naoConformidadeController;
        listaIdResponsavelComboBox = new ArrayList<>();
        listaIdSetorComboBox = new ArrayList<>();
        inicializar();
        mostrarIconeEscolherImagem();
        descricao.requestFocusInWindow();
    }
        
    private void mostrarIconeEscolherImagem(){
        btnImagem.setIcon(naoConformidadeController.getImagem().lerImagem(
                getClass().getResource("/imagens/escolherImagem.png").getFile(), 
                btnImagem.getWidth()-10,
                btnImagem.getHeight()-10));
        naoConformidadeController.getImagem().removerImagem();//remove a referência do icone
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
        naoConformidadeController.getImagem().escolherImagem();
        btnImagem.setIcon(naoConformidadeController.getImagem().lerImagem(btnImagem.getWidth(), btnImagem.getHeight()));
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
                    "imagens\\nc"+Codigo.getText()+".png", 
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
    
    private void mostrarImagem(){
        frameImagem = new FrameImagem(naoConformidadeController);
        frameImagem.setLocationRelativeTo(this);
        frameImagem.setVisible(true);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelx = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        Codigo = new javax.swing.JTextField();
        dataRegistro = new datechooser.beans.DateChooserCombo();
        dataAcontecimento = new datechooser.beans.DateChooserCombo();
        descricao = new javax.swing.JTextField();
        abrangencia = new javax.swing.JTextField();
        origem = new javax.swing.JTextField();
        Responsavel = new javax.swing.JComboBox<>();
        acaoCorrecao = new javax.swing.JTextField();
        reincidencia = new javax.swing.JCheckBox();
        btnCancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Setor = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnImagem = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Imagem");
        jLabel5.setName("jLabel5"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Não Conformidade");
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro de Não Conformidade", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(784, 574));

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
        jLabel8.setText("Responsável ");
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
        Codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Codigo.setName("Codigo"); // NOI18N

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
    dataRegistro.setNavigateFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
    dataRegistro.setCurrentNavigateIndex(0);
    dataRegistro.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);

    dataAcontecimento.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
    dataAcontecimento.setNavigateFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
    dataAcontecimento.setCurrentNavigateIndex(0);
    dataAcontecimento.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);

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

    reincidencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    reincidencia.setText("Essa Não Conformidade está em reincidência?");
    reincidencia.setName("reincidencia"); // NOI18N
    reincidencia.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            reincidenciaKeyPressed(evt);
        }
    });

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

    Setor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    Setor.setName("Setor"); // NOI18N
    Setor.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            SetorKeyPressed(evt);
        }
    });

    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Imagem", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
    jPanel2.setName("jPanel2"); // NOI18N

    btnImagem.setMaximumSize(new java.awt.Dimension(0, 0));
    btnImagem.setMinimumSize(new java.awt.Dimension(0, 0));
    btnImagem.setName("btnImagem"); // NOI18N
    btnImagem.setPreferredSize(new java.awt.Dimension(0, 0));
    btnImagem.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            btnImagemMouseClicked(evt);
        }
    });

    btnVisualizar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    btnVisualizar.setText("Visualizar Imagem");
    btnVisualizar.setName("btnVisualizar"); // NOI18N
    btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnVisualizarActionPerformed(evt);
        }
    });

    jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButton1.setText("Remover Imagem");
    jButton1.setName("jButton1"); // NOI18N
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addComponent(btnVisualizar)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1))
        .addComponent(btnImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addComponent(btnImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnVisualizar)
                .addComponent(jButton1)))
    );

    jButton2.setText("teset");
    jButton2.setName("jButton2"); // NOI18N
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelx)
                        .addComponent(dataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(dataAcontecimento, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addContainerGap())))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(descricao)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel9)
                                .addComponent(acaoCorrecao, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Responsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(reincidencia)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Setor, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(337, 337, 337)))
                                .addComponent(abrangencia, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(326, 326, 326))
                                    .addComponent(origem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnCancelar)
                            .addGap(96, 96, 96)
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap(34, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(labelx, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dataAcontecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(origem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(abrangencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(31, 31, 31)
                    .addComponent(jLabel9)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(acaoCorrecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel8)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Responsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Setor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(reincidencia))
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnCancelar)
                .addComponent(btnSalvar)
                .addComponent(jButton2))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
            .addContainerGap())
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        mostrarImagem();
    }//GEN-LAST:event_btnVisualizarActionPerformed

    private void btnImagemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImagemMouseClicked
        escolherImagem();
    }//GEN-LAST:event_btnImagemMouseClicked

    private void SetorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SetorKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            reincidencia.requestFocusInWindow();
        }
    }//GEN-LAST:event_SetorKeyPressed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void reincidenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reincidenciaKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            btnImagem.requestFocusInWindow();
        }
    }//GEN-LAST:event_reincidenciaKeyPressed

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

    private void ResponsavelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ResponsavelKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            acaoCorrecao.requestFocusInWindow();
        }
    }//GEN-LAST:event_ResponsavelKeyPressed

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

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        System.out.println(dataRegistro.getCurrent().getTime());
        if(!validarDados()){
            salvar();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        acaoCorrecao.requestFocusInWindow();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        Responsavel.requestFocusInWindow();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        origem.requestFocusInWindow();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        abrangencia.requestFocusInWindow();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        dataAcontecimento.requestFocusInWindow();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void labelxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelxMouseClicked
        labelx.requestFocusInWindow();
    }//GEN-LAST:event_labelxMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        descricao.requestFocusInWindow();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.out.println(naoConformidadeController.getImagem().isImagemValida());
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Codigo;
    private javax.swing.JComboBox<String> Responsavel;
    private javax.swing.JComboBox<String> Setor;
    private javax.swing.JTextField abrangencia;
    private javax.swing.JTextField acaoCorrecao;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnImagem;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVisualizar;
    private datechooser.beans.DateChooserCombo dataAcontecimento;
    private datechooser.beans.DateChooserCombo dataRegistro;
    private javax.swing.JTextField descricao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelx;
    private javax.swing.JTextField origem;
    private javax.swing.JCheckBox reincidencia;
    // End of variables declaration//GEN-END:variables
}
