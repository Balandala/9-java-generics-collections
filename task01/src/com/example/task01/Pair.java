package com.example.task01;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;

public class Pair<T,U> {

    private T first;
    private U second;

    private Pair(T first,U second){
        this.first = first;
        this.second = second;
    }

    public static <T,U> Pair<T,U> of(T first, U second){
        return new Pair<T, U>(first, second);
    }

    public T getFirst(){
        return first;
    }

    public U getSecond(){
        return second;
    }

    public int hashCode(){
        int hash = Objects.hash(first, second);
        return hash;
    }

    public boolean equals(Object object){
        if (object.getClass() == this.getClass()){
            Pair<?,?> objPair = (Pair<?,?>) object;
            return objPair.first == this.first && objPair.second == this.second;
        }
        return  false;
    }

    public void ifPresent(BiConsumer <? super T, ? super U> consumer){
        if (first != null && second != null)
            consumer.accept(first,second);
    }

}
