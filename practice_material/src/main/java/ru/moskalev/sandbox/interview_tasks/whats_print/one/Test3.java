package ru.moskalev.sandbox.interview_tasks.whats_print.one;

class Test3 {
    public static void show() {
        System.out.println("static method called");
    }

    public static void main(String[] args) {
        Test3 t3 = null;
        t3.show();// потому что метод статический и он относится к классу а не к экземпляру
    }
}
