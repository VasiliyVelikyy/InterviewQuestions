package ru.moskalev.sandbox.courses.stepic.java_functional_programming_OLD_2017.four_functional_data_processin.twelve;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String number;
    private int balance;
    private List<Transaction> transactions=new ArrayList<>();

    public Account(String number, int balance) {
        this.number = number;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransactions(Transaction transaction) {
      transactions.add(transaction);
    }
}
