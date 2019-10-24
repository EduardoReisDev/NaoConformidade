/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
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
public class ResponsavelDao implements Crud<Responsavel>{

    public Responsavel listarPorCpf(String cpf){
        Connection conexao = new Conexao().abreConexao();
        Responsavel responsavel = null;
        try{
            PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM responsavel WHERE cpf = ? ");
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                responsavel = new Responsavel(
                        rs.getInt("id"),
                        rs.getString("cpf"),
                        rs.getString("nome")
                );
            }
            return responsavel;
        }
        catch (SQLException e){
            System.out.println("erro na busca "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
        return responsavel;
    }
    
    @Override
    public boolean criar(Responsavel dados) {
        Connection conexao = new Conexao().abreConexao();
        String query = "insert into responsavel ("
                + "nome, cpf) VALUES (?, ?)";
        try{
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, dados.getNome());
            ps.setString(2, dados.getCpf());
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
    public void listarTodos(Consumer<? super Responsavel> resultado) {
        Connection conexao = new Conexao().abreConexao();
        Responsavel responsavel = null;
        try{
            PreparedStatement stmt = conexao.prepareStatement("select * from responsavel");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                responsavel = new Responsavel(
                        rs.getInt("id"), 
                        rs.getString("cpf"),
                        rs.getString("nome")
                );
                resultado.accept(responsavel);
            }
        }
        catch (SQLException e){
            System.out.println("erro na busca "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
    }
    
    public void listarPorNome(Consumer<? super Responsavel> resultado, String nome) {
        String query = "SELECT * FROM responsavel WHERE nome like '"+nome+"%'";
        Connection conexao = new Conexao().abreConexao();
        Responsavel responsavel = null;
        try{
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                responsavel = new Responsavel(
                        rs.getInt("id"), 
                        rs.getString("cpf"),
                        rs.getString("nome")
                );
                resultado.accept(responsavel);
            }
        }
        catch (SQLException e){
            System.out.println("erro na busca "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
    }

    @Override
    public Responsavel listarPorId(int id) {
        Connection conexao = new Conexao().abreConexao();
        Responsavel responsavel = null;
        try{
            PreparedStatement stmt = conexao.prepareStatement("select * from responsavel where id = ? ");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                responsavel = new Responsavel(
                        rs.getInt("id"), 
                        rs.getString("cpf"),
                        rs.getString("nome")
                );
            }
            return responsavel;
        }
        catch (SQLException e){
            System.out.println("erro na busca "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
        return responsavel;    
    }

    @Override
    public boolean editar(Responsavel dados) {
        Connection conexao = new Conexao().abreConexao();
        String query = "update responsavel set nome = ?, cpf = ? where id = ?";
        try{
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, dados.getNome());
            ps.setString(2, dados.getCpf());
            ps.setInt(3, dados.getId());
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
        String query = "delete from responsavel where id = ?";
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

    public int getLastId() {
        String query = "select max(id) as maxId from responsavel;";
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
