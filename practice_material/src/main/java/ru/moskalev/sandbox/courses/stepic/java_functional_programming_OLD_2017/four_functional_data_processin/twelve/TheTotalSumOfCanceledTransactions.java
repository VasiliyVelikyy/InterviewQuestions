package ru.moskalev.sandbox.courses.stepic.java_functional_programming_OLD_2017.four_functional_data_processin.twelve;


import java.util.List;

/*4.12 The total sum of canceled transactions
0 из 1 шага пройдено
0 из 1 баллa  получено
You have two classes:

Transaction: uuid: String,  state: State (CANCELED, FINISHED, PROCESSING), sum: Long, created: Date
Account: number: String, balance: Long, transactions: List<Transaction>
Both classes have getters for all fields with the corresponding names (getState(), getSum(), getTransactions() and so on).
Write a method using Stream API that calculates the total sum of canceled transactions for all non-empty accounts (balance > 0). Perhaps, flatMap method can help you to implement it.


Classes Transaction, Account and enum State will be available during testing. You can define your own classes for local testing.

Important. Use the given template for your method. Pay attention to type of an argument and the returned value. Please, use only Stream API, no cycles.

Examples: there are 2 accounts (in JSON notation) below. The result is 10 000.

[
  {
    "number": "1001",
    "balance": 0,
    "transactions": [
      {
        "uuid": "774cedda-9cde-4f53-8bc2-5b4d4859772a",
        "state": "CANCELED",
        "sum": 1000,
        "created": "2016.12.12 18:30:05"
      }
    ]
  },
  {
    "number": "1002",
    "balance": 8000,
    "transactions": [
      {
        "uuid": "337868a7-f469-43c0-9042-3422ce37ee26a",
        "state": "FINISHED",
        "sum": 8000,
        "created": "2016.12.12 17:30:55"
      },
      {
        "uuid": "f8047f86-89e7-4226-8b75-74c55a4d7e31",
        "state": "CANCELED",
        "sum": 10000,
        "created": "2016.12.12 18:10:18"
      }
    ]
  }
]*/
public class TheTotalSumOfCanceledTransactions {

    /**
     * Calculates the general sum of canceled transactions for all non empty accounts in the list
     */
    public static long calcSumOfCanceledTransOnNonEmptyAccounts(List<Account> accounts) {
        return accounts.stream()
                .filter(account -> account.getBalance() > 0)
                .flatMap(account -> account.getTransactions().stream())
                .filter(transaction -> transaction.getState().equals("CANCELED"))
                .map(Transaction::getSum)
                .reduce(0L, (a, b) -> a + b, Long::sum);
    }

    public static void main(String[] args) {
        System.out.println("ok");
    }
}
