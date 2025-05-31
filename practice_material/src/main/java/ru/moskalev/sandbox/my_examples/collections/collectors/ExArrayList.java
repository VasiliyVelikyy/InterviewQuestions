package ru.moskalev.sandbox.my_examples.collections.collectors;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class ExArrayList {
    public static void main(String[] args) {
        //объединение двух листов
        List<String> product = Arrays.asList("product1", "product2", "product3");
        List<String> tags = Arrays.asList("tags1", "tags2", "tags3");

        //Stream.concat() - тяжелая операция, как и Optional.of
        List<String> mergeList = Stream.concat(Optional.ofNullable(product).orElse(List.of()).stream(),
                        Optional.ofNullable(tags).orElse(List.of()).stream())
                .collect(Collectors.toList());
        System.out.println("mergeList= " + mergeList);

        //лучше переписать так
        List<String> rightVarMerge = new ArrayList<>();

        if (!CollectionUtils.isEmpty(product)) {
            rightVarMerge.addAll(product);
        }
        if (!CollectionUtils.isEmpty(tags)) {
            rightVarMerge.addAll(tags);
        }

        System.out.println("rightVarMerge= " + rightVarMerge);
    }
}
