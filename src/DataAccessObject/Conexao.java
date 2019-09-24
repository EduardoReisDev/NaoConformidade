
package DataAccessObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Eduardo
 */
public class Conexao {
    private static final String DRIVER= "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/naoconformidade";
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    
    public static Connection abreConexao(){
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        }
        catch (SQLException sqlex){
            System.out.println("Erro na conexão "+sqlex);
        }
        catch (ClassNotFoundException cnfex){
            System.out.println("Não carregou o driver "+ cnfex);
        }
        return null;
    }

    public void fechaConexao(Connection conexao){
        try{
            conexao.close();
        }
        catch(SQLException sqlex){
            System.out.println("Erro na conexão: "+sqlex);
        }
    }
}
