package DataAccessObject;

import java.util.Date;

/**
 *
 * @author Eduardo
 */
public class NaoConformidadeValueObject {
    private int idnc;
    private String ncAbrangencia;
    private String ncAcaocorrecao;
    private String ncCodigo;
    private Date ncDataacontecimento;
    private Date ncDataregistro;
    private String ncDescricao;
    private String ncImagem;
    private String ncOrigem;
    private int ncReincidencia;
    private String ncResponsavel;
    private int setorIdSetor;

    public int getSetorIdSetor() {
        return setorIdSetor;
    }

    public void setSetorIdSetor(int setorIdSetor) {
        this.setorIdSetor = setorIdSetor;
    }
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
