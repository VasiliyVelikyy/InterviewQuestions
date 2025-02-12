package ru.moskalev.sandbox.interview_tasks.whats_print;

//что выведет
class ObjectPrint {
    public static void main(String[] args) {
        printObjToString1();
        printObjToString2();
    }

    private static void printObjToString2() {
        var objToStringMethod = new ObjToStringMethod("Obj2", 23);
        System.out.println(objToStringMethod); //вызов предопределенного метода toString()
    }

    private static void printObjToString1() {
        var objWithOutStringMethod = new ObjWithOutStringMethod("Obj1", 12);
        System.out.println(objWithOutStringMethod);
        //Если метод toString() не переопределён в классе, то его реализация будет использоваться из базового класса java.lang.Object.
        // Стандартная реализация toString() в классе Object выглядит следующим образом:
        //
        //getClass().getName() — возвращает имя класса объекта (включая полный путь пакета).
        //Integer.toHexString(hashCode()) — преобразует хэш-код объекта в шестнадцатеричное представление.
    }
}

class ObjWithOutStringMethod {
    String name;
    int age;

    public ObjWithOutStringMethod(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class ObjToStringMethod {
    String name;
    int age;

    public ObjToStringMethod(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "ObjToStringMethod{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
