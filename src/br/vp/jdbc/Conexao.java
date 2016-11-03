package br.vp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao { 
    /* Atributos est�ticos */
    private static Connection sConexao;

    /* M�todos est�ticos */
    /*public static void main(String[] args) throws ClassNotFoundException
    {

   		Class.forName("oracle.jdbc.driver.OracleDriver");
    	
        System.out.println();
        System.out.println("Obtendo a conex�o");
        getConexao();

        System.out.println();
        System.out.println("Fechando a conex�o");
        desconectar();
    }*/
    


    /* M�todos da classe */
    public static Connection getConexao() {
    	
    	String tURL = "jdbc:oracle:thin:@vendasplus.com.br:1521:XE";
    	String tUsuario = "system";
    	String tSenha = "3101wjs";
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            // Caso a conex�o j� exista, verifica se est� aberta e retorna ela
            if (sConexao != null) {
                if (!sConexao.isClosed())
                    return sConexao;
            }

            sConexao =  DriverManager.getConnection(tURL, tUsuario, tSenha);
            
            System.out.println("Conex�o aberta");
        }
        catch (SQLException | ClassNotFoundException tExcept)
        {
            mostrarErro(tExcept, "Problemas na conex�o com o banco de dados");
            System.exit(9);
        }
        return sConexao;
    }

    public static void desconectar() {
        try {
            // Desconectando do banco de dados e verificando se a desconex~]ao foi bem realizada
            DriverManager.println("|-------> Fechando a conex�o.....");
            sConexao.close();
            DriverManager.println("|-------> Verificando a conex�o ap�s fechamento");
            if (sConexao.isClosed())
            {
                DriverManager.println("|-------> Conex�o fechada");
                System.out.println("Conex�o fechada");
            }
            else
            {
                DriverManager.println("|-------> Conex�o aberta");
            }
        }
        catch (SQLException tExcept)
        {
            mostrarErro(tExcept, "Problemas no fechamento da conex�o com o banco de dados");
            System.exit(9);
        }
    }

    public static void mostrarErro(Exception pExcept, String pMsg) {
        System.out.println();
        System.out.println(pMsg);
        System.out.println("Exce��o....: " + pExcept.getClass().getName());
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
        System.out.println("Pilha de execu��o");
        pExcept.printStackTrace(System.out);
    }
    
    
}
