package com.administrator.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet Filter implementation class administratorFilter
 */
@WebFilter("/administratorFilter")
public class administratorFilter implements Filter {

    /**
     * Default constructor. 
     */
    public administratorFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		// ����������ַ�����
		request.setCharacterEncoding("UTF-8");
		// ���÷���������ַ�����
		response.setCharacterEncoding("UTF-8");
		// ת��ServletRequestΪ HttpServletRequest
		HttpServletRequest req = (HttpServletRequest) request;
		// ת��ServletResponseΪHttpServletRequest
		HttpServletResponse res = (HttpServletResponse) response;
		// ��ȡSession
		//HttpSession session = req.getSession();
		// ��ȡSession�д洢�Ķ���
		
		// ��ȡ��ǰ�����URI
		String url = req.getRequestURI();
		// �ж�Session�еĶ����Ƿ�Ϊ�գ��ж������URI�Ƿ�Ϊ��������˵�URI
	/*	if (o == null && url.indexOf("test.jsp") < 0 && url.indexOf("comment.jsp") < 0 && url.indexOf("link.jsp") < 0) {
			chain.doFilter(request, response);
			res.setHeader("Cache-Control", "no-store");
			res.setDateHeader("Expires", 0);
			res.setHeader("Pragma", "no-cache");
			res.flushBuffer();
		}*/
	    Cookie[] cookies = req.getCookies();
        Cookie remember = null;
        String[] data=null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals("rememberAdministrator")) {
                    remember = c;
                    //�ַ����ָ�
                    data=URLDecoder.decode(remember.getValue(),"utf-8").split(",");  /*ʹ��utf-8��ȡ*/
                   	//out.print(data[2]);
                }
            }
        }
        
		if (!(url.indexOf("administrator")<0)&&data==null) {
			res.sendRedirect(req.getContextPath()+"/Login.jsp");
			//response�ύ����������ִ��  �ٴ��ύresponse�ͻᱨ�� 
			//�����Ҫ����return ��ֹ�����������ִ��
			return;
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
