package ru.moskalev.sandbox.my_examples.collections.linkedlist;

import java.util.LinkedList;
import java.util.Optional;

import static java.util.List.of;

class ExLinkedList {
    public static void main(String[] args) {
        LinkedList<Integer> linkedListEmpty = new LinkedList<>();
        LinkedList<Integer> linkedList =new LinkedList <>(
                of( 1,2,3)
        );

        Integer valFromList = Optional.ofNullable(linkedList.getLast()) //исключения не будет
                .orElse(0);

        Integer val = Optional.ofNullable(linkedListEmpty.getLast()) //выкинет исключение NoSuchElementException
                .orElse(0);

        //лучше обернуть в фильтр
        Integer val2 = Optional.of(linkedListEmpty)
                .filter(list -> !list.isEmpty())
                .map(LinkedList::getLast)
                .orElse(0);
    }
}
