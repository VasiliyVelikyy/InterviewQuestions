package ru.moskalev.sandbox.my_examples.lambda.functional_interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

//часть кода взято с ресурса https://www.youtube.com/watch?v=-d4_gtRH5uY
public class AnonymousInnerClassTest {
    static int var = 20;

    public static void main(String[] args) {
        //длинная запись
        new Thread(new Runnable() { //создание анонимного класса посредством функционального интерфейса
            @Override
            public void run() {
                System.out.println("A thread created and running...");
            }
        }).start();

        //короткая запись
        new Thread(() -> System.out.println("A thread created THIS IS SMALL RECORD..."))
                .start();

        //длинная запись
        Button button = new Button("Press me!");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "presss");
            }
        });

        //короткая запись
        button.addActionListener(e -> JOptionPane.showMessageDialog(null, "presss"));

        //можно и так указать
        button.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(null, "press");
        });

        //захват переменной из внешнего контекста. Это называеться замыкание
        int i = 10;
        new Thread(() -> System.out.println("A thread Variable capture... i=" + i))
                .start();

        //переменная должна быть не изменяемая или final
        // i=5;
        // new Thread(()-> System.out.println("Variable used in lambda expression should be final or effectively final... i=" + i))
        //      .start();

        //если переменная статическая то ее можно изменяит и исп в лямде
        var = 40;
        new Thread(() -> System.out.println("Variable is static and can use in lambda and anonim class... var=" + var))
                .start();

        //вместо локальный неизменных либо final переменных можно использовать атомики
        // и его содержимое может меняться
        AtomicInteger atomic=new AtomicInteger(15);
        atomic.set(25);
        new Thread(() -> System.out.println("Variable is atomic and changed and can use in lambda " +
                "and anonim class... atomic=" + atomic.toString()))
                .start();
    }


}
