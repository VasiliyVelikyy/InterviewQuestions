package ru.moskalev.sandbox.my_examples.lambda.callback;

import java.util.Optional;

public abstract class Task {

    final void executeWith(Callback callback) {
        execute();
        Optional.ofNullable(callback).ifPresent(Callback::call);
    }

    public abstract void execute();
}