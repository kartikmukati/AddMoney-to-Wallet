
package controller;

import db.DBConnector;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TransactionModel;

public class AddMoney extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int balance = Integer.parseInt(req.getParameter("balance"));
        int amount = Integer.parseInt(req.getParameter("amount"));
        PrintWriter out = resp.getWriter();
        
        int b = DBConnector.addMoneyDirect(balance, amount, "Credit");
        
        if(b > 0){
            out.println(b);
        }
//        boolean b = TransactionModel.addMoney(balance, amount, "credit");
//        if(b == true){
//            out.println("expense.jsp");
//        }
//        else{
//            out.println("index.jsp");
//        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

   
}
