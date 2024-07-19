package org.grocery.service;

import java.io.IOException;

import javax.persistence.EntityTransaction;
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



@WebServlet("/reg")
public class saveUser extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String fname=req.getParameter("fn");
		String lname=req.getParameter("sn");
		String email=req.getParameter("em");
		String password=req.getParameter("pw");
		
		Configuration cfg=new Configuration().configure();
		SessionFactory sf=cfg.buildSessionFactory();
		Session s=sf.openSession();
		
		user u=new user();
		u.setFname(fname);
		u.setSname(lname);
		u.setEmail(email);
		u.setPassword(password);
		s.save(u);
		EntityTransaction transaction=s.beginTransaction();
		transaction.commit();
		RequestDispatcher dispatcher=req.getRequestDispatcher("Login.html");
		dispatcher.forward(req, resp);
	}

	
}
