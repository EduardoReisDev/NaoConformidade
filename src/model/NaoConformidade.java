package model;

import java.util.Date;

/**
 *
 * @author Eduardo
 */
public class NaoConformidade {
    private int id;
    private String abrangencia;
    private String acaoCorrecao;
    private Date dataAcontecimento;
    private Date dataRegistro;
    private String descricao;
    private String imagem;
    private String origem;
    private boolean reincidencia;
    
    private Responsavel responsavel;
    private Setor setor;
    private int IdResponsavel;
    private String Responsavel;
    private int IdSetor;
    private String Setor;

    public NaoConformidade(int id, String abrangencia, String acaoCorrecao, Date dataAcontecimento, Date dataRegistro, String descricao, String imagem, String origem, boolean reincidencia, Setor setor, Responsavel responsavel){
        this.id = id;
        this.abrangencia = abrangencia;
        this.acaoCorrecao = acaoCorrecao;
        this.dataAcontecimento = dataAcontecimento;
        this.dataRegistro = dataRegistro;
        this.descricao = descricao;
        this.imagem = imagem;
        this.origem = origem;
        this.reincidencia = reincidencia;
        this.responsavel = responsavel;
        this.setor = setor;
        
    }
    
    public NaoConformidade(){
        
    }
                         //"Código","Descrição","DataRegistro","DataAcontecimento","Reincidencia","Abrangencia","Origem","Responsavel","AcaoCorrecao","Setor"
    public NaoConformidade(Object id, Object descricao, Object dataRegistro, Object dataAcontecimento, Object reincidencia, Object abrangencia, Object origem,Object responsavel, Object acaoCorrecao,Object setor) {
        this.id =(int) id;
        this.abrangencia = (String) abrangencia;
        this.acaoCorrecao = (String) acaoCorrecao;
        this.dataAcontecimento = (Date) dataAcontecimento;
        this.dataRegistro = (Date) dataRegistro;
        this.descricao = (String) descricao;
        this.origem = (String) origem;
        this.reincidencia = (boolean) reincidencia;
        this.responsavel = (model.Responsavel) responsavel;
        this.setor = (model.Setor) setor;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbrangencia() {
        return abrangencia;
    }

    public void setAbrangencia(String abrangencia) {
        this.abrangencia = abrangencia;
    }

    public String getAcaoCorrecao() {
        return acaoCorrecao;
    }

    public void setAcaoCorrecao(String acaoCorrecao) {
        this.acaoCorrecao = acaoCorrecao;
    }

    public Date getDataAcontecimento() {
        return dataAcontecimento;
    }

    public void setDataAcontecimento(Date DataAcontecimento) {
        this.dataAcontecimento = DataAcontecimento;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public boolean isReincidencia() {
        return reincidencia;
    }

    public void setReincidencia(boolean reincidencia) {
        this.reincidencia = reincidencia;
    }

    public boolean getReincidencia() {
       return this.reincidencia;
    }

    public void setIdResponsavel(int Responsavel) {
        this.IdResponsavel=Responsavel;
    }
    public void setResponsavel(String Responsavel){
        this.Responsavel=Responsavel;
    }
    public String getResponsavelN(){
        return this.Responsavel;
    }
    public int getIdResponsavel() {
       return this.IdResponsavel;
    }

    public int getIdSetor() {
        return this.IdSetor;
    }

    public void setIdSetor(int Setor ) {
        this.IdSetor=Setor;
    }
    public void setSetorN(String Setor){
        this.Setor=Setor;
    }

    public String getSetorN() {
       return this.Setor;
    }
    
   
}
