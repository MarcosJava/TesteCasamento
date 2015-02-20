package br.com.mfs.casamento.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mfs.casamento.managed.UsuarioSessionBean;

@WebFilter("/*")
public class AutorizacaoFilter implements Filter {
	
	@Inject
	private UsuarioSessionBean usuario;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		
		if (!usuario.isLogado()	
				&& !request.getRequestURI().endsWith("/login.xhtml")
				&& !request.getRequestURI().contains("/javax.faces.resource/")) {
			
			response.sendRedirect(request.getContextPath() + "/login.xhtml");
			
		} else {
			chain.doFilter(req, res);
		}
	}
	

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}