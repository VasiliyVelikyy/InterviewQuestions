package ru.moskalev.sandbox.my_examples.lambda.callback;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        SimpleTask task = new SimpleTask();
        task.executeWith(() -> System.out.println(("I'm done now.")));

        task.throwException();

    }
}
