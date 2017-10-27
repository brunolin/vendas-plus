package br.vp.bean;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 
 * @author Brunolin
 *	Classe responsável pela parte do filtro das páginas,
 *	ela só da acesso as páginas do usuário somente se o mesmo estiver logado
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.html" })
public class AuthFilter implements Filter {

	public AuthFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();
			if (reqURI.indexOf("/login.xhtml") >= 0
					|| (ses != null && ses.getAttribute("username") != null)
					|| reqURI.indexOf("/public/") >= 0
					|| reqURI.contains("javax.faces.resource"))
				chain.doFilter(request, response);
			else
				resp.sendRedirect(reqt.getContextPath() + "/view/pages/login.xhtml");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void destroy() {

	}
}