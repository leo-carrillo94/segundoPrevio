package co.segundoParcial.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import co.segundoParcial.dao.BillDao;
import co.segundoParcial.modelo.Bill;

/**
 * Servlet implementation class BillServlet
 */
@WebServlet("/login")
public class BillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BillDao billDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		billDao = new BillDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getServletPath();
		
	try {
	switch(action) {
	case "/new":
		showNewForm(request, response);
		break;
	case "/insert":
		insertarBill(request, response);
		break;
	case "/delete":
		eliminarBill(request, response);
		break;
	case "/edit":
		showEditForm(request, response);
		break;
	case "/update":
		actualizarBill(request, response);
		break;
	
	 default:
		listBill(request, response);
		break;
	}
	}catch(SQLException e) {
		throw new ServletException(e);
		
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("billetera.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void insertarBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException, ParseException {
		// TODO Auto-generated method stub
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formato.parse(request.getParameter("date"));
		Integer user_id = Integer.parseInt(request.getParameter("user_id"));
		Double value = Double.parseDouble(request.getParameter("valude"));
		Integer type = Integer.parseInt(request.getParameter("type"));
		String observation = request.getParameter("observation");
		
		Bill bill = new Bill(date, user_id, value, type, observation);
		billDao.insert(bill);
		response.sendRedirect("login");
		
	}
	
	private void eliminarBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));		
		billDao.delete(id);
		response.sendRedirect("login");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
		// TODO Auto-generated method stub
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Bill billActual = billDao.select(id);
		request.setAttribute("bill", billActual);
		RequestDispatcher dispatcher = request.getRequestDispatcher("billetera.jsp");
		dispatcher.forward(request, response);
	}
	
	private void actualizarBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException, ParseException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formato.parse(request.getParameter("date"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		Double value = Double.parseDouble(request.getParameter("value"));
		int type = Integer.parseInt(request.getParameter("type"));
		String observation = request.getParameter("observation");
		
	
		Bill bill = new Bill(id, date, user_id, value,type, observation);
		billDao.update(bill);
		response.sendRedirect("login");
	}
	
	private void listBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
		// TODO Auto-generated method stub
		
		List <Bill> listBills  = billDao.selectAll();
		request.setAttribute("listBills", listBills);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

}
