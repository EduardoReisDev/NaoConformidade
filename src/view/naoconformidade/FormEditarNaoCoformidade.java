package view.naoconformidade;

import controller.NaoConformidadeController;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
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
 * @author Ricardo
 */
public class FormEditarNaoCoformidade extends javax.swing.JDialog {
    private final NaoConformidadeController naoConformidadeController;
    private final ArrayList<Integer> listaIdResponsavelComboBox;
    private final ArrayList<Integer> listaIdSetorComboBox;
    private FrameImagem frameImagem;
    NaoConformidade aux;
    
    /** Creates new form EditarNaoCoformidade
     * @param parent
     * @param modal
     * @param naoConformidadeController
     * @param id */
    public FormEditarNaoCoformidade(java.awt.Frame parent, boolean modal, NaoConformidadeController naoConformidadeController,int id) {
        super(parent, modal);
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/logo.png")));
        this.naoConformidadeController = naoConformidadeController;
        listaIdResponsavelComboBox = new ArrayList<>();
        listaIdSetorComboBox = new ArrayList<>();
        inicializar();
        setarDados(id);
        mostrarIconeEscolherImagem();
        descricao.requestFocusInWindow();
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
          
    private void mostrarIconeEscolherImagem(){
        btnImagem.setIcon(naoConformidadeController.getImagem().lerImagem(
                aux.getImagem(), 
                btnImagem.getWidth()-10,
                btnImagem.getHeight()-10));
    }
    
    private void mostrarIconePadrao(){
        btnImagem.setIcon(naoConformidadeController.getImagem().lerImagem(
                getClass().getResource("/imagens/escolherImagem.png").getFile(), 
                btnImagem.getWidth()-10,
                btnImagem.getHeight()-10));
    }
    
    private void inicializar(){
        listarResponsaveis();
        listarSetores();
    }
    
    private void setarDados(int id){
        Imagem img = new Imagem();
        aux = naoConformidadeController.listarPorId(id);
        Codigo.setText(String.format("%010d",aux.getId()));
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
           btnImagem.setIcon(img.lerImagem(aux.getImagem(),btnImagem.getWidth() ,btnImagem.getHeight())); 
        }
        else{
            btnImagem.setIcon(null);
        }
        Responsavel.setSelectedIndex(listaIdResponsavelComboBox.indexOf(aux.getResponsavel().getId()));
        Setor.setSelectedIndex(listaIdSetorComboBox.indexOf(aux.getSetor().getId()));
    
    }
    
    private void listarSetores(){
        Setor.removeAllItems();
        naoConformidadeController.listarTodosSetores(this::popularComboBoxSetor);
    }
    
    private void removerImagem(){
        mostrarIconePadrao();
        new File("imagens\\nc"+String.valueOf(Integer.parseInt(Codigo.getText()))+".png").delete();
        naoConformidadeController.getImagem().removerImagem();
        
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
        else if(!naoConformidadeController.validarTexto(abrangencia.getText())){
                abrangencia.requestFocus();
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
        if(naoConformidadeController.getImagem().isImagemValida()){
            btnImagem.setIcon(naoConformidadeController.getImagem().lerImagem(btnImagem.getWidth(), btnImagem.getHeight()));
        }
        else{
            mostrarIconeEscolherImagem();
        }
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
                    null, 
                    abrangencia.getText(), 
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
        if(naoConformidadeController.getImagem().isImagemValida()){
            frameImagem = new FrameImagem(naoConformidadeController);
            frameImagem.setLocationRelativeTo(this);
            frameImagem.setVisible(true);
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
        origem = new javax.swing.JTextField();
        Responsavel = new javax.swing.JComboBox<>();
        reincidencia = new javax.swing.JCheckBox();
        btnCancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Setor = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnImagem = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        abrangencia = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        acaoCorrecao = new javax.swing.JTextArea();

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Imagem");
        jLabel5.setName("jLabel5"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Atualizar Não Conformidade");
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Atualizar Não Conformidade", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
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
        btnSalvar.setText("Atualizar");
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

    reincidencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    reincidencia.setText("É reincidência");
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
    btnImagem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnImagemActionPerformed(evt);
        }
    });
    btnImagem.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            btnImagemKeyPressed(evt);
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
    btnVisualizar.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            btnVisualizarKeyPressed(evt);
        }
    });

    btnRemover.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    btnRemover.setText("Remover Imagem");
    btnRemover.setName("btnRemover"); // NOI18N
    btnRemover.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRemoverActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addComponent(btnVisualizar)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnRemover))
        .addComponent(btnImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addComponent(btnImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnVisualizar)
                .addComponent(btnRemover)))
    );

    jScrollPane2.setName("jScrollPane2"); // NOI18N

    abrangencia.setColumns(20);
    abrangencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    abrangencia.setRows(3);
    abrangencia.setName("abrangencia"); // NOI18N
    abrangencia.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            abrangenciaKeyPressed(evt);
        }
    });
    jScrollPane2.setViewportView(abrangencia);

    jScrollPane1.setName("jScrollPane1"); // NOI18N

    acaoCorrecao.setColumns(20);
    acaoCorrecao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    acaoCorrecao.setRows(3);
    acaoCorrecao.setName("acaoCorrecao"); // NOI18N
    acaoCorrecao.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            acaoCorrecaoKeyPressed(evt);
        }
    });
    jScrollPane1.setViewportView(acaoCorrecao);

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
                            .addComponent(jLabel2)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6)
                                    .addComponent(Responsavel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8)
                                    .addComponent(reincidencia)
                                    .addComponent(Setor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(326, 326, 326))
                                    .addComponent(origem)
                                    .addComponent(jScrollPane2)
                                    .addComponent(jLabel9)
                                    .addComponent(jScrollPane1))
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnCancelar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addContainerGap())))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap(33, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(labelx, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dataAcontecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCancelar)
                        .addComponent(btnSalvar)))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(origem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel9)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel8)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Responsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Setor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(reincidencia)
                    .addGap(0, 0, Short.MAX_VALUE)))
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
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        removerImagem();
    }//GEN-LAST:event_btnRemoverActionPerformed

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

    private void ResponsavelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ResponsavelKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            Setor.requestFocusInWindow();
        }
    }//GEN-LAST:event_ResponsavelKeyPressed

    private void descricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descricaoKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            if(!naoConformidadeController.validarTexto(descricao.getText())){
                naoConformidadeController.obrigatorio(this);
            }
            else{
                origem.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_descricaoKeyPressed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(!validarDados()){
            atualizar();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        acaoCorrecao.requestFocusInWindow();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        Responsavel.requestFocusInWindow();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        abrangencia.requestFocusInWindow();
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

    private void origemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_origemKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            if(!naoConformidadeController.validarTexto(origem.getText())){
                naoConformidadeController.obrigatorio(this);
            }
            else{
                abrangencia.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_origemKeyPressed

    private void abrangenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_abrangenciaKeyPressed
         if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            if(!naoConformidadeController.validarTexto(abrangencia.getText())){
                naoConformidadeController.obrigatorio(this);
            }
            else{
                acaoCorrecao.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_abrangenciaKeyPressed

    private void acaoCorrecaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_acaoCorrecaoKeyPressed
         if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            if(!naoConformidadeController.validarTexto(acaoCorrecao.getText())){
                naoConformidadeController.obrigatorio(this);
            }
            else{
                Responsavel.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_acaoCorrecaoKeyPressed

    private void btnImagemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnImagemKeyPressed
        btnVisualizar.requestFocusInWindow();
    }//GEN-LAST:event_btnImagemKeyPressed

    private void btnVisualizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnVisualizarKeyPressed
        btnSalvar.requestFocusInWindow();
    }//GEN-LAST:event_btnVisualizarKeyPressed

    private void btnImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImagemActionPerformed
         if(naoConformidadeController.getImagem().isImagemValida()){
           btnVisualizar.requestFocusInWindow();
        }
        else{
            escolherImagem();
        }
    }//GEN-LAST:event_btnImagemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField Codigo;
    public javax.swing.JComboBox<String> Responsavel;
    public javax.swing.JComboBox<String> Setor;
    private javax.swing.JTextArea abrangencia;
    private javax.swing.JTextArea acaoCorrecao;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnImagem;
    private javax.swing.JButton btnRemover;
    public javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVisualizar;
    public datechooser.beans.DateChooserCombo dataAcontecimento;
    public datechooser.beans.DateChooserCombo dataRegistro;
    public javax.swing.JTextField descricao;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelx;
    public javax.swing.JTextField origem;
    public javax.swing.JCheckBox reincidencia;
    // End of variables declaration//GEN-END:variables
}
