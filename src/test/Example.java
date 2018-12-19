package test;

import java.util.Collection;

public class Example {

    public static <T> T addAndReturn(T element, Collection<T> collection){
        collection.add(element);
        return element;
    }

    public static void main(String[] args){

    }
}