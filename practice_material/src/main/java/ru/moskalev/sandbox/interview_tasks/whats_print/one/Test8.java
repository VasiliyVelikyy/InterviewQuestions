package ru.moskalev.sandbox.interview_tasks.whats_print.one;

//Что будет если запустить код
class Test8 {
    public static void main(String[] args) {
        User user = new User();
        user.setReferal(112L);
    }

    static class User {
        long id;
        User referal = new User();

        public void setReferal(long referalId) {
            this.referal.id = referalId;
        }
    }
}

//java.lang.StackOverflowError постоянно создается User