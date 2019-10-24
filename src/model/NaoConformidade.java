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
    private int idResponsavel;
    private int idSetor;
    private String responsavel;

    public String getresponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public NaoConformidade(int id, String abrangencia, String acaoCorrecao, Date dataAcontecimento, Date dataRegistro, String descricao, String imagem, String origem, boolean reincidencia, int idResponsavel, int idSetor){
        this.id = id;
        this.abrangencia = abrangencia;
        this.acaoCorrecao = acaoCorrecao;
        this.dataAcontecimento = dataAcontecimento;
        this.dataRegistro = dataRegistro;
        this.descricao = descricao;
        this.imagem = imagem;
        this.origem = origem;
        this.reincidencia = reincidencia;
        this.idResponsavel = idResponsavel;
        this.idSetor = idSetor;
    }
    
    public NaoConformidade(){
        
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

    public int getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(int idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

    public boolean getReincidencia() {
       return this.reincidencia;
    }



   
}
