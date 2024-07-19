package org.grocery.service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.grocery.entity.user;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
@WebServlet("/log")
public class FindUser extends HttpServlet{


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String email=req.getParameter("em");
		String password=req.getParameter("pw");
		
		Configuration cfg=new Configuration().configure();
		SessionFactory sf=cfg.buildSessionFactory();
		Session s=sf.openSession();
		
		Query<user> q=s.createQuery("select u from user u where u.email=?1 and u.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
		user us=q.getSingleResult();
		System.out.println(us);
		if(us!=null) {
			RequestDispatcher dispatcher=req.getRequestDispatcher("Home.html");
			dispatcher.forward(req, resp);
		}
		}catch(Exception e){
			RequestDispatcher dispatcher=req.getRequestDispatcher("Login.html");
			PrintWriter writer=resp.getWriter();
			writer.print("<div >Enter valid Username And Password</div>");
			
			dispatcher.include(req, resp);
		}
	
		
	}
}
