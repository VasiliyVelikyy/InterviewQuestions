package sandbox.my_examples.lambda.callback;


import java.sql.SQLException;

public final class SimpleTask extends Task {

    @Override
    public void execute() {
        System.out.println("Perform some important activity and after call the callback method.");
    }

    public void throwException() throws SQLException {
        throw new SQLException();
    }

}