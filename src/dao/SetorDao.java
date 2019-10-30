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
        String query = "insert into setor (nome, idResponsavel) VALUES (?, ?)";
        try{
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, dados.getNome());
            ps.setInt(2, dados.getResponsavel().getId());
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
        String query = "select * from setor as s "
                + "INNER JOIN responsavel as r "
                + "on s.idResponsavel = r.id";
        System.out.println(query);
        Connection conexao = new Conexao().abreConexao();
        try{
            Statement stm = conexao.createStatement();
            ResultSet res = stm.executeQuery(query);
            while (res.next()){
                resultado.accept(new Setor(
                        res.getInt("id"), 
                        res.getString("nome"),
                        new Responsavel(
                                res.getInt(4),
                                res.getString(6),
                                res.getString(5)
                        )
                ));
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
        Connection conexao = new Conexao().abreConexao();
        try{
            PreparedStatement stmt = conexao.prepareStatement("select * from setor where id = ? ");
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                return(new Setor(
                        res.getInt("id"), 
                        res.getString("nome"),
                        new Responsavel(
                                res.getInt("id")
                        )
                ));
            }
        }
        catch(SQLException e){
            System.out.println("erro na listagem "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
        return null;
    }

    @Override
    public boolean editar(Setor dados) {
        Connection conexao = new Conexao().abreConexao();
        String query = "update setor set nome = ?, idResponsavel = ? where idsetor = ?";
        try{
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, dados.getNome());
            ps.setInt(2, dados.getResponsavel().getId());
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
        String query = "delete from setor where id = ?";
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
    
    @Override
    public int getLastId() {
        String query = "select max(id) as maxId from setor;";
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

    public void listarPorNome(Consumer<Setor> resultado, String nome) {
        String query = "select * from setor where nome like"+nome+"%";
        Connection conexao = new Conexao().abreConexao();
        try{
            Statement stm = conexao.createStatement();
            ResultSet res = stm.executeQuery(query);
            while (res.next()){
                resultado.accept(new Setor(
                        res.getInt("id"), 
                        res.getString("nome"),
                        new Responsavel(
                                res.getInt("id")
                        )
                ));
            }
        }
        catch(SQLException e){
            System.out.println("erro na listagem "+e.getMessage());
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
    }
    
}
