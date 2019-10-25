/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import model.NaoConformidade;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Responsavel;
import model.Setor;

/**
 *
 * @author leona
 */
public class NaoConformidadeDao implements Crud<NaoConformidade>{

    @Override
    public boolean criar(NaoConformidade dados) {
        Connection conexao = new Conexao().abreConexao();
        String query = "insert into naoConformidade ("
                + "descricao, dataRegistro, dataAcontecimento, reincidencia, abrangencia, "
                + "origem, acaoCorrecao, caminhoImagem, idResponsavel , idSetor) VALUES ("
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, dados.getDescricao());
            ps.setDate(2, new Date(dados.getDataAcontecimento().getYear(), dados.getDataAcontecimento().getMonth() , dados.getDataAcontecimento().getDay()));
            ps.setDate(3, new Date(dados.getDataRegistro().getYear(), dados.getDataRegistro().getMonth() , dados.getDataRegistro().getDay()));
            ps.setBoolean(4, dados.isReincidencia());
            ps.setString(5, dados.getAbrangencia());
            ps.setString(6, dados.getOrigem());
            ps.setString(7, dados.getAcaoCorrecao());
            ps.setString(8, dados.getImagem());
            ps.setInt(9, dados.getResponsavel().getId());
            ps.setInt(10, dados.getSetor().getId());
            return ps.executeUpdate()>0;
        }
        catch(SQLException sqlex){
            System.out.println("erro na inserção "+sqlex.getMessage());
            return false;
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
    }

    
    //ATENÇÂO"!!!! esse é o unico método que está funcionando corretamente!
    
    @Override
    public void listarTodos(Consumer<? super NaoConformidade> resultado) {
        String query = "select * from naoConformidade AS nc "
                + "INNER JOIN setor AS s "
                + "INNER JOIN  responsavel AS r "
                + "INNER JOIN responsavel AS rs "
                + "ON nc.idResponsavel=r.id AND "
                + "s.idResponsavel = rs.id AND "
                + "nc.idSetor = s.id;";
        Connection conexao = new Conexao().abreConexao();
        NaoConformidade result;
        try{
            Statement stm = conexao.createStatement();
            ResultSet res = stm.executeQuery(query);
            while (res.next()){
                System.out.println(res.toString());
                result = new NaoConformidade(
                        res.getInt("id"),
                        res.getString("abrangencia"),
                        res.getString("acaoCorrecao"),
                        res.getDate("dataAcontecimento"),
                        res.getDate("dataRegistro"),
                        res.getString("descricao"),
                        res.getString("caminhoImagem"),
                        res.getString("origem"),
                        res.getBoolean("reincidencia"),
                        new Setor(
                                res.getInt(12),//id do setor
                                res.getString(13), //nome do setor
                                new Responsavel(
                                        res.getInt(16),//id do responsavel pelo setor 
                                        res.getString(18),//nome do responsavel pelo setor
                                        res.getString(17)//cpf do responsável pelo setor
                                )
                        ),
                        new Responsavel(
                                res.getInt(18),//id do responsavel pelo não conformidade
                                res.getString(20),//nome do do responsavel pelo não conformidade
                                res.getString(19)//cpf do responsavel pelo não conformidade
                        )
                );
                resultado.accept(result);
                
            }
        }
        catch(SQLException e){
            System.out.println("erro na listagem "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
    }

    @Override
    public NaoConformidade listarPorId( int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editar(NaoConformidade dados) {
    Connection conexao = new Conexao().abreConexao();
        String query = "update naoConformidade set descricao = ?, dataRegistro = ?, dataAcontecimento = ?, reincidencia = ?, abrangencia = ?, "
                + "origem = ?, acaoCorrecao = ?, caminhoImagem = ?, idResponsavel = ?, idSetor = ? where id = ?";
        try{
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, dados.getDescricao());
            ps.setDate(2, new Date(dados.getDataRegistro().getYear(), dados.getDataRegistro().getMonth() , dados.getDataRegistro().getDay()));
            ps.setDate(3, new Date(dados.getDataAcontecimento().getYear(), dados.getDataAcontecimento().getMonth() , dados.getDataAcontecimento().getDay()));
            ps.setBoolean(4, dados.isReincidencia());
            ps.setString(5, dados.getAbrangencia());
            ps.setString(6, dados.getOrigem());
            ps.setString(7, dados.getAcaoCorrecao());
            ps.setString(8, dados.getImagem());
            ps.setInt(9, dados.getIdResponsavel());
            ps.setInt(10, dados.getIdSetor());
            ps.setInt(11, dados.getId());
            
            return ps.executeUpdate()>0;
        }
        catch(SQLException e){
            System.out.println("erro na alteração "+e.getMessage());
            return false;
        }
        finally{
            Conexao.fechaConexao(conexao);
        }    
    }

    @Override
    public boolean excluir(int id) {
        Connection conexao = new Conexao().abreConexao();
        String query = "delete from naoConformidade where id = ?";
        try{
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate()>0;
        }
        catch(SQLException e){
            System.out.println("erro na exclusao "+e.getMessage());
            return false;
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
    }
    
    public int listarUltimoId(){
        String query = "select max(id) as maxId from naoConformidade;";
        Connection conexao = new Conexao().abreConexao();
        try{
            Statement stm = conexao.createStatement();
            ResultSet res = stm.executeQuery(query);
            while (res.next()){
                return res.getInt("maxId");
            }
        }
        catch(SQLException e){
            System.out.println("erro na listagem "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
        return 0; 
    }
    
     public int getLastId() {
        String query = "select max(id) as maxId from naoConformidade;";
        Connection conexao = new Conexao().abreConexao();
        try{
            Statement stm = conexao.createStatement();
            ResultSet res = stm.executeQuery(query);
            while (res.next()){
                return res.getInt("maxId");
            }
        }
        catch(SQLException e){
            System.out.println("erro na listagem "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
        return 0; 
    }
     
    public ArrayList<NaoConformidade> buscaResponsaveis() {
        ArrayList<NaoConformidade> responsaveis = new ArrayList<>();
        Connection conexao = new Conexao().abreConexao();
                    try (PreparedStatement stmt = conexao.prepareStatement("select * from responsavel")) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    NaoConformidade naoConformidade = new NaoConformidade();
                    naoConformidade.setIdResponsavel(rs.getInt(1));
                    naoConformidade.setResponsavel(rs.getString(2));
                    responsaveis.add(naoConformidade);
                }
                 Conexao.fechaConexao(conexao);
            }
            catch(SQLException e){
            System.out.println("erro na listagem "+e.getMessage());
        }
        return responsaveis;
    }
    
     public ArrayList<NaoConformidade> buscasetores() {
        ArrayList<NaoConformidade> setores = new ArrayList<>();
        Connection conexao = new Conexao().abreConexao();
                    try (PreparedStatement stmt = conexao.prepareStatement("select * from setor")) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    NaoConformidade naoConformidade = new NaoConformidade();
                    naoConformidade.setIdSetor(rs.getInt(1));
                    naoConformidade.setSetorN(rs.getString(2));
                    setores.add(naoConformidade);
                }
                 Conexao.fechaConexao(conexao);
            }
            catch(SQLException e){
            System.out.println("erro na listagem "+e.getMessage());
        }
        return setores;
    }

     public List<NaoConformidade> read() {

        Connection conexao = new Conexao().abreConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<NaoConformidade> contatos = new ArrayList<>();

        try {
            stmt = conexao.prepareStatement("select n.id,n.descricao,n.dataRegistro,n.dataAcontecimento,n.reincidencia,n.abrangencia,n.origem, n.acaoCorrecao,n.caminhoImagem,s.nome,r.nome FROM naoConformidade AS n INNER JOIN responsavel AS r INNER JOIN setor AS s WHERE n.idSetor=s.id AND n.idResponsavel=r.id;");
            rs = stmt.executeQuery();

            while (rs.next()) {

                NaoConformidade contato = new NaoConformidade();

                    contato.setId(rs.getInt("id"));
                    contato.setDescricao(rs.getString("descricao"));
                    contato.setDataRegistro(rs.getDate("dataRegistro"));
                    contato.setDataAcontecimento(rs.getDate("DataAcontecimento"));
                    contato.setReincidencia(rs.getBoolean("reincidencia"));
                    contato.setReincidencia(rs.getBoolean("reincidencia"));
                    contato.setAbrangencia(rs.getString("abrangencia"));
                    contato.setAcaoCorrecao(rs.getString("acaoCorrecao"));
                    contato.setOrigem(rs.getString("origem"));
                    contato.setImagem(rs.getString("caminhoImagem"));
                    contato.setSetorN(rs.getString("nome"));//nome do setor
                    contato.setResponsavel(rs.getString("nome"));//nome do do responsavel pelo não conformidade
                    
                contatos.add(contato);
            }

        } catch (SQLException ex) {
            Logger.getLogger(NaoConformidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }

        return contatos;

    }
    
}
