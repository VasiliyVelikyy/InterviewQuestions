package sandbox.interview_tasks;

import java.util.ArrayList;
import java.util.List;

public class Nums {
    public static void main(String[] args) {
        // верна ли такая запись
        // List<Number> numList=new ArrayList<Integer>();
        //неверна, так как мы должны слева и справа привести к 1 знаминателю
        List<Number> numList = new ArrayList<Number>();
        //или не указывать справа тип
        List<Integer> numList2 = new ArrayList<>();
        //	но если мы обьявляем лист намберов, мы можем засунуть туда интежер - так как он дочерний элемент Number
        numList.add(new Integer(5));
        System.out.println("numList= " + numList);

    }
}
