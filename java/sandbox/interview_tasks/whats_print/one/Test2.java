package sandbox.interview_tasks.whats_print.one;

 class Test2 {
    class A {
        String str = "ab";

        A() {
            print();
        }

        void print() {
            System.out.println(str.length());
        }
    }

    class B extends A {
        String str = "abc";

        @Override
        void print() {
            System.out.println(str.length());

        }
    }

    public static void main(String[] args) {
        new Test2().new B().print();//
        // при создании объекта класса B вызывается конструктор класса A (так как он неявно вызывает конструктор суперкласса).
        // вызовется метод B.print() внутри конструктора А, а так как класс В еще не создан (конструктор не отработал до конца, будет nullpointerexception)
    }
}


//nullPointer потому что вызовется конструктор