package week2.assignment.practice;
/*
* 제로베이스 35기 서요한
* */

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class BankAccount {
    private String accountNumber;
    private int balance;

    public BankAccount(String accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("출금액은 0보다 커야합니다.");
        }

        if (balance < amount) {
            throw new InsufficientBalanceException("잔액이 부족합니다. 현재 잔액 : " + balance + "원");
        }

        balance -= amount;
        System.out.println(amount + "원이 출금되었습니다.");
        System.out.println("현재 잔액 : " + balance + "원");

    }

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount("1234-5678", 100);
        bankAccount.withdraw(200);

    }
}
