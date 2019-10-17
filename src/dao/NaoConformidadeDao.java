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
import java.util.function.Consumer;

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
            ps.setInt(9, dados.getIdResponsavel());
            ps.setInt(10, dados.getIdSetor());
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

    @Override
    public void lerTodos(Consumer<? super NaoConformidade> resultado) {
        String query = "select * from naoConformidade ";
        Connection conexao = new Conexao().abreConexao();
        NaoConformidade result;
        try{
            Statement stm = conexao.createStatement();
            ResultSet res = stm.executeQuery(query);
            while (res.next()){
                result = new NaoConformidade();
                result.setId(res.getInt("id"));
                result.setDescricao(res.getString("descricao"));
                //result.set
                result.setIdResponsavel(res.getInt("idResponsavel"));
                
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
    public NaoConformidade lerPorId( int id) {
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
    
}
