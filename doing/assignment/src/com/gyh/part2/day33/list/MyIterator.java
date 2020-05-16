package com.gyh.part2.day33.list;

import java.util.Iterator;

public interface MyIterator<E> extends Iterator<E> {
    boolean hasNext();

    boolean hasPrevious();

    E next();

    E previous();

    int nextIndex();

    int previousIndex();

    void add(E e);

    void remove();

    void set(E e);
}
