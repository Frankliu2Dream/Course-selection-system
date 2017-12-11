package Filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
//字符编码过滤器，将所有request的字符串编码都改为UTF-8
public class CharacterFilter implements Filter {


	public void destroy() {
		System.out.println("destroy filter");
	}


	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException,ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		request.setCharacterEncoding("UTF-8");
		arg2.doFilter(arg0,arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("init filter");

	}

}
