package br.com.empresasopenbar.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.empresasopenbar.entidades.Usuario;


@WebFilter({"/FiltroAutenticacao", "/forms/*", "/template/*", "/index.xhtml"})
public class FiltroAutenticacao implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpSession sessao = httpReq.getSession();
		Usuario usuarioLogado = (Usuario)sessao.getAttribute("usuarioLogado");
		
		if (usuarioLogado != null) 
			chain.doFilter(request, response);
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/login.xhtml");
			rd.forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
