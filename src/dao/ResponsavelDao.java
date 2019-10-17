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
import java.util.function.Consumer;
import model.Responsavel;

/**
 *
 * @author leona
 */
public class ResponsavelDao implements Crud<Responsavel>{

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
        Responsavel responsavel;
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
            ps.setString(3, dados.getCpf());
            ps.setInt(6, dados.getId());
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
    
}
