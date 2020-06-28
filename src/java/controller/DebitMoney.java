package controller;

import db.DBConnector;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TransactionModel;

public class DebitMoney extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        
        System.out.println("dopost debitmoney-start");
        
        int balance = Integer.parseInt(req.getParameter("balance"));
        int amount = Integer.parseInt(req.getParameter("amount"));

        
        System.out.println("dopost debitmoney-getParameter");
        
        String status = "Debit";

        int newBalance = balance - amount;
        
        int b = DBConnector.debitMoneyDirect(balance, amount, "Debit");
        
        if(b >= 0){
            out.println(b);
        }
        

//        boolean b = TransactionModel.debitMoney(balance, amount, status);
//
//        if (b == true) {
//            out.println(newBalance);
//        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        resp.sendRedirect("index.jsp");
    }

}
