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
//�����Ϣ������
//������ʵ�¼ҳ�棬����ִ�й�������
//������ʷǵ�¼���棬userNameΪ�յ��Զ���ת����¼ҳ����е�¼
public class LoginFilter implements Filter {


	public void destroy() {
		System.out.println("��¼��֤����������");
		
	}


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpSession session=req.getSession();
		String login=(String)session.getAttribute("userName");
		//��¼ҳ�����ִ��
		if("/CourseSystem/pages/login/doLogin.jsp".equals(req.getRequestURI())
			||"/CourseSystem/pages/login/login.jsp".equals(req.getRequestURI())){
			chain.doFilter(request,response);
		}
		else
		{	//�û���Ϊ���Զ���ת����¼ҳ��
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
		System.out.println("��¼��֤����������");
	}

}
