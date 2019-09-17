package modal.bean;

import java.util.Date;

/**
 *
 * @author Eduardo
 */
public class Naoconformidade {
    private int idnc;
    private String ncCodigo;
    private String ncDescricao;
    private Date ncDataregistro;
    private Date ncDataacontecimento;
    private int ncReincidencia;
    private String ncAbrangencia;
    private String ncOrigem;
    private String ncResponsavel;
    private String ncAcaocorrecao;
    private String ncImagem;

    public int getIdnc() {
        return idnc;
    }

    public void setIdnc(int idnc) {
        this.idnc = idnc;
    }

    public String getNcCodigo() {
        return ncCodigo;
    }

    public void setNcCodigo(String ncCodigo) {
        this.ncCodigo = ncCodigo;
    }

    public String getNcDescricao() {
        return ncDescricao;
    }

    public void setNcDescricao(String ncDescricao) {
        this.ncDescricao = ncDescricao;
    }

    public Date getNcDataregistro() {
        return ncDataregistro;
    }

    public void setNcDataregistro(Date ncDataregistro) {
        this.ncDataregistro = ncDataregistro;
    }

    public Date getNcDataacontecimento() {
        return ncDataacontecimento;
    }

    public void setNcDataacontecimento(Date ncDataacontecimento) {
        this.ncDataacontecimento = ncDataacontecimento;
    }

    public int getNcReincidencia() {
        return ncReincidencia;
    }

    public void setNcReincidencia(int ncReincidencia) {
        this.ncReincidencia = ncReincidencia;
    }

    public String getNcAbrangencia() {
        return ncAbrangencia;
    }

    public void setNcAbrangencia(String ncAbrangencia) {
        this.ncAbrangencia = ncAbrangencia;
    }

    public String getNcOrigem() {
        return ncOrigem;
    }

    public void setNcOrigem(String ncOrigem) {
        this.ncOrigem = ncOrigem;
    }

    public String getNcResponsavel() {
        return ncResponsavel;
    }

    public void setNcResponsavel(String ncResponsavel) {
        this.ncResponsavel = ncResponsavel;
    }

    public String getNcAcaocorrecao() {
        return ncAcaocorrecao;
    }

    public void setNcAcaocorrecao(String ncAcaocorrecao) {
        this.ncAcaocorrecao = ncAcaocorrecao;
    }

    public String getNcImagem() {
        return ncImagem;
    }

    public void setNcImagem(String ncImagem) {
        this.ncImagem = ncImagem;
    }
}
