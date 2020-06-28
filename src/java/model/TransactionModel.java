/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import db.DBConnector;
import dto.TransactionDTO;

/**
 *
 * @author Kartik Mukati
 */
public class TransactionModel 
{
    public static boolean addMoney(int balance,int amount,String status){
        TransactionDTO tdto = new TransactionDTO(balance, amount, status);
        
        return DBConnector.addMoney(tdto);
    }
    public static boolean debitMoney(int balance,int amount,String status){
        TransactionDTO tdto = new TransactionDTO(balance, amount, status);
        
        return DBConnector.debitMoney(tdto);
    }
}
