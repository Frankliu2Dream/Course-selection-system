package Filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//身份信息过滤器
//如果访问登录页面，继续执行过滤器链
//如果访问非登录界面，userName为空的自动跳转到登录页面进行登录
public class LoginFilter implements Filter {


	public void destroy() {
		System.out.println("登录验证过滤器销毁");
		
	}


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpSession session=req.getSession();
		String login=(String)session.getAttribute("userName");
		//登录页面继续执行
		if("/CourseSystem/pages/login/doLogin.jsp".equals(req.getRequestURI())
			||"/CourseSystem/pages/login/login.jsp".equals(req.getRequestURI())){
			chain.doFilter(request,response);
		}
		else
		{	//用户名为空自动跳转到登录页面
			if(login==null||"".equals(login))
			{
				request.getRequestDispatcher("login.jsp").forward(request,response);
			}
			else
			{
				chain.doFilter(request,response);
			}
		}

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("登录验证过滤器启动");
	}

}
