/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import model.Setor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;
import model.Responsavel;

/**
 *
 * @author leona
 */
public class SetorDao implements Crud<Setor>{

    @Override
    public boolean criar(Setor dados) {
        Connection conexao = new Conexao().abreConexao();
        String query = "insert into setor ("
                + "id, nome, idResponsavel) VALUES ( ?, ?, ?)";
        try{
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, dados.getCodigo());
            ps.setString(2, dados.getNome());
            ps.setString(3, dados.getResponsavel0());
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
    public void listarTodos(Consumer<? super Setor> resultado) {
        String query = "select * from setor";
        Connection conexao = new Conexao().abreConexao();
        Setor result;
        try{
            Statement stm = conexao.createStatement();
            ResultSet res = stm.executeQuery(query);
            while (res.next()){
                result = new Setor();
                result.setNome(res.getString("nome"));
                result.setId(res.getInt("id"));
                result.setResponsavel(new Responsavel(res.getInt("id")));
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
    public Setor listarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editar(Setor dados) {
        Connection conexao = new Conexao().abreConexao();
        String query = "update setor set setorNome = ?, setorCodigo = ?, setorResponsavel = ? where idsetor = ?";
        try{
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, dados.getNome());
            ps.setString(2, dados.getCodigo());
            ps.setString(3, dados.getResponsavel0());
            ps.setInt(4, dados.getId());
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

    @Override
    public boolean excluir(int id) {
        Connection conexao = new Conexao().abreConexao();
        String query = "delete from setor where idsetor = ?";
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
    
}
