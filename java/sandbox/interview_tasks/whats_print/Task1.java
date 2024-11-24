package sandbox.interview_tasks.whats_print;

class Task1 {
    public static void main(String[] args) {
        //что выведет?
        method1();
        //что выведет?
        OurDemoClass ourDemoClass = new OurDemoClass();
        //Какая проблема тут возникнет?
        //method2();
    }


    static void method1() {
        String m = "Hello";
        System.out.print(m);
        bar(m);
        System.out.print(m);

    }

    static void bar(String m) {
        m += " World!";
    }

// Знание иерархии. Не скомилируется потому что FileNotFoundException наследуется от IOException. И в этот кетч никогда не попадает.
//Все исключения будет перехватывать IOException
//    static void method2() {
//        try {
//            foo();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//        public static void foo() throws IOException, FileNotFoundException {
//
//        }
//    }


}

abstract class OurAbstractClass {
    public OurAbstractClass() {
        System.out.println("This is abstract class constructor");
    }

}

class OurDemoClass extends OurAbstractClass {

    public OurDemoClass() {
        System.out.println("This is demo class constructor");
    }

}
