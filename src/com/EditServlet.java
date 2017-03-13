package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet/*")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					EmpDao emp = new EmpDao();
					EmpDto object = emp.viewSpecificRecord(request.getPathInfo().replace('/',' '));
					out.print("  	  name:"+object.getName());
					out.print(" 	  email:"+object.getEmail());
					out.print(" 	  pass:"+object.getPassword());
					out.print(" 	  id:"+object.getId());
					out.print(request.getPathInfo().replace('/',' '));
					RequestDispatcher rd = request.getRequestDispatcher("/edit.html");
					rd.include(request, response);
					
					
					
					
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String name= request.getParameter("name");
		String email= request.getParameter("email");
		String pass = request.getParameter("password");
		String id=request.getPathInfo().replace('/',' ');
		EmpDao emp = new EmpDao();
		emp.update(email,name,pass,id);
		
		RequestDispatcher rd = request.getRequestDispatcher("/edit.html");
		rd.forward(request, response);
		
		
	}

}

