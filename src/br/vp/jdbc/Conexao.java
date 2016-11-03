package br.vp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao { 
    /* Atributos estáticos */
    private static Connection sConexao;

    /* Métodos estáticos */
    /*public static void main(String[] args) throws ClassNotFoundException
    {

   		Class.forName("oracle.jdbc.driver.OracleDriver");
    	
        System.out.println();
        System.out.println("Obtendo a conexão");
        getConexao();

        System.out.println();
        System.out.println("Fechando a conexão");
        desconectar();
    }*/
    


    /* Métodos da classe */
    public static Connection getConexao() {
    	
    	String tURL = "jdbc:oracle:thin:@vendasplus.com.br:1521:XE";
    	String tUsuario = "system";
    	String tSenha = "3101wjs";
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            // Caso a conexão já exista, verifica se está aberta e retorna ela
            if (sConexao != null) {
                if (!sConexao.isClosed())
                    return sConexao;
            }

            sConexao =  DriverManager.getConnection(tURL, tUsuario, tSenha);
            
            System.out.println("Conexão aberta");
        }
        catch (SQLException | ClassNotFoundException tExcept)
        {
            mostrarErro(tExcept, "Problemas na conexão com o banco de dados");
            System.exit(9);
        }
        return sConexao;
    }

    public static void desconectar() {
        try {
            // Desconectando do banco de dados e verificando se a desconex~]ao foi bem realizada
            DriverManager.println("|-------> Fechando a conexão.....");
            sConexao.close();
            DriverManager.println("|-------> Verificando a conexão após fechamento");
            if (sConexao.isClosed())
            {
                DriverManager.println("|-------> Conexão fechada");
                System.out.println("Conexão fechada");
            }
            else
            {
                DriverManager.println("|-------> Conexão aberta");
            }
        }
        catch (SQLException tExcept)
        {
            mostrarErro(tExcept, "Problemas no fechamento da conexão com o banco de dados");
            System.exit(9);
        }
    }

    public static void mostrarErro(Exception pExcept, String pMsg) {
        System.out.println();
        System.out.println(pMsg);
        System.out.println("Exceção....: " + pExcept.getClass().getName());
        System.out.println("Mensagem...: " + pExcept.getMessage());
        if (pExcept instanceof SQLException)
        {
            SQLException tExcept = (SQLException) pExcept;
            System.out.println("SQLState...:" + tExcept.getSQLState());
            System.out.println("Error Code.:" + tExcept.getErrorCode());
            DriverManager.println("SQLState...:" + tExcept.getSQLState());
            DriverManager.println("Error Code.:" + tExcept.getErrorCode());
        }
        Throwable tCausa = pExcept.getCause();
        while (tCausa != null)
        {
            System.out.println("Causa.....: " + tCausa.getMessage());
            tCausa = tCausa.getCause();
        }
        System.out.println("Pilha de execução");
        pExcept.printStackTrace(System.out);
    }
    
    
}
