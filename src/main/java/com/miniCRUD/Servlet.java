package com.miniCRUD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {


//    private CustomerService customerService = CustomerService.getInstance();
//    private CustomerForm form;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String firstName = form.getFirstName().getValue();
//        String lastName = form.getLastName().getValue();
//        String status = form.getStatus().getCaption();
//        String email = form.getEmail().getValue();
//        Date date = (Date) form.getBirthDate().getValue();
//        customerService.setdata(firstName,lastName,status,email,date);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
