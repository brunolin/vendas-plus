package br.vp.dao.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import br.vp.dto.ProdutoDTO;
import br.vp.hibernate.HibernateUtil;
import br.vp.jdbc.Conexao;
import br.vp.model.Produto;

public class VendedorDaoHibernate {
	
	public VendedorDaoHibernate() {
		
	}
	
	@SuppressWarnings("unchecked")
    public List<Produto> getCampanhas() {

        List<Produto> tLista = new ArrayList<>();

        try {
            SessionFactory tFactory = HibernateUtil.getSessionFactory();
            Session tSessao = tFactory.getCurrentSession();
            
    		/*@SuppressWarnings("deprecation")
			SessionFactory sessions = new AnnotationConfiguration().configure().buildSessionFactory();
    		Session session = sessions.openSession();*/
            
            Query tQuery = tSessao.createQuery("FROM Produto");
            tLista = tQuery.list();

        } catch (HibernateException tExcept) {
        	mostrarErro(tExcept, "Erro no método de recuperação da lista de Livros");
        }
        return tLista;
    }
	
	public static void mostrarErro(Exception pExcept, String pMsg)
    {
        System.out.println();
        System.out.println(pMsg);
        System.out.println("ExceÃ§Ã£o... : " + pExcept.getClass().getName());
        System.out.println("Mensagem.. : " + pExcept.getMessage());
        if (pExcept instanceof SQLException)
        {
            SQLException tExcept = (SQLException) pExcept;
            System.out.println("SQLState.. :" + tExcept.getSQLState());
            System.out.println("Error Code :" + tExcept.getErrorCode());
            DriverManager.println("SQLState.. :" + tExcept.getSQLState());
            DriverManager.println("Error Code :" + tExcept.getErrorCode());
        }
        Throwable tCausa = pExcept.getCause();
        while (tCausa != null)
        {
            System.out.println("Causa..... : " + tCausa.getMessage());
            tCausa = tCausa.getCause();
        }
        System.out.println("Pilha de execuÃ§Ã£o");
        pExcept.printStackTrace(System.out);
    }
}
