   package controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {
		 public void init() {
			 
		 }
		 
		 public void doPost(HttpServletRequest request, HttpServletResponse response) {
			 doGet(request, response);
		 }
		 
		 public void doGet(HttpServletRequest request, HttpServletResponse response) {
			 
		 }
}
