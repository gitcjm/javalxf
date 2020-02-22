package com.str;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class InstrumentedHashSet<E> extends HashSet<E> {
    private  int addCount = 0;

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public static void main(String[] args) {
        InstrumentedHashSet<String> s = new InstrumentedHashSet<String>();
        s.addAll(Arrays.asList("Piano", "Flute", "Drum", "violin"));
        System.out.println(s.addCount);
        System.out.println(s.size());
        System.out.println(s);
    }
}
