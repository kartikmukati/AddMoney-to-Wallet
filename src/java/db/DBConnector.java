package db;

import dto.TransactionDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnector 
{
    private static Connection connection = null;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clonepaytm", "root", "root");
            System.out.println("Connection");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static int addMoneyDirect(int balance,int amount,String status){
        int i = 0;
        int newBalance = balance + amount;
        String sql = "insert into transaction_history (balance,amount,status) VALUES (?,?,?)";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,newBalance);
            ps.setInt(2, amount);
            ps.setString(3,status);
            i = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(i > 0){
            return newBalance;
        }
        return 0;
    }
    
    public static int debitMoneyDirect(int balance,int amount,String status){
        int i = 0;
        int newBalance = balance - amount;
        String sql = "insert into transaction_history (balance,amount,status) VALUES (?,?,?)";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,newBalance);
            ps.setInt(2, amount);
            ps.setString(3,status);
            i = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(i > 0){
            return newBalance;
        }
        return -1;
    }
    
    public static ResultSet getTransaction(){
        ResultSet rs = null;
        String sql = "select * from transaction_history order by time_stamp desc";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     } 
    
    
    public static boolean addMoney(TransactionDTO tdto){
        int i = 0;
        int balance = tdto.getBalance();
        int amount = tdto.getAmount();
        String status = tdto.getStatus();
        int newBalance = balance + amount;
        String sql = "insert into transaction_history (balance,amount,status) VALUES (?,?,?)";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,newBalance);
            ps.setInt(2, amount);
            ps.setString(3,status);
            i = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(i > 0){
            return true;
        }
        return false;
    }
    public static boolean debitMoney(TransactionDTO tdto){
        int i = 0;
        int balance = tdto.getBalance();
        int amount = tdto.getAmount();
        String status = tdto.getStatus();
        int newBalance = balance - amount;
        String sql = "insert into transaction_history (balance,amount,status) VALUES (?,?,?)";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,newBalance);
            ps.setInt(2, amount);
            ps.setString(3,status);
            i = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(i > 0){
            return true;
        }
        return false;
    }
    public static int getAvailableBalance(){
        int balance = 0;
        String sql = "select balance from transaction_history order by time_stamp desc";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            if(rs.next()){
                balance = rs.getInt(1);
                System.out.println(balance);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return balance; 
    }
    
}
