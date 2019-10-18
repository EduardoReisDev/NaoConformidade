
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Eduardo
 */
public class Conexao {
    private final String URL;

    public Conexao() {
        this.URL = "jdbc:sqlite:"+System.getProperty("user.dir")+"\\banco\\Banco.db";
    }
    
    public Connection abreConexao(){
        try{
            //Class.forName("com.mysql.jdbc.DriverManager");
            return DriverManager.getConnection(URL);
        }
        catch (SQLException sqlex){
            System.out.println("Erro na conexão "+sqlex);
        }
        return null;
    }
    
    public Connection abreConexao(String caminhoArquivo){
        try{
            //Class.forName("com.mysql.jdbc.DriverManager");
            return DriverManager.getConnection("jdbc:sqlite:"+caminhoArquivo);
        }
        catch (SQLException sqlex){
            System.out.println("Erro na conexão "+sqlex);
        }
        return null;
    }

    public static void fechaConexao(Connection conexao){
        try{
            conexao.close();
        }
        catch(SQLException sqlex){
            System.out.println("Erro na conexão: "+sqlex);
        }
    }
    
}
