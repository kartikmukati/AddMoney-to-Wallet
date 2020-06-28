
package dto;

public class TransactionDTO 
{
    private int balance;
    private int amount;
    private String status;

    
    public TransactionDTO(int balance, int amount, String status) {
        this.balance = balance;
        this.amount = amount;
        this.status = status;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
