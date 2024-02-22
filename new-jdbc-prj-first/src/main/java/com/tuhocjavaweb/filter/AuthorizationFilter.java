package com.tuhocjavaweb.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tuhocjavaweb.constant.SystemConstant;
import com.tuhocjavaweb.model.UserModel;
import com.tuhocjavaweb.utils.SessionUtil;

public class AuthorizationFilter implements Filter{
	
	private ServletContext context;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse reponse = (HttpServletResponse) servletResponse;
		String url = request.getRequestURI();
		if(url.startsWith("/admin")) {
			UserModel model = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
			if(model != null) {
				if(model.getRole().getCode().equals(SystemConstant.ADMIN)) {
					filterChain.doFilter(servletRequest, servletResponse);
				}else if(model.getRole().getCode().equals(SystemConstant.USER)) {
					reponse.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=not_permission&alert=danger");
				}
			}else {
				reponse.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=not_login&alert=danger");
			}
		}else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void destroy() {
	}

}
