package com.gyh.part2.day33.list;

public interface MyList<E> extends Iterable<E> {
    boolean add(E e);

    void add(int index, E e);

    void clear();

    boolean contains(Object obj);

    E get(int index);

    int indexOf(Object obj);

    boolean isEmpty();

    MyIterator iterator();

    MyIterator iterator(int index);

    int lastIndexOf(Object obj);

    E remove(int index);

    boolean remove(Object obj);

    E set(int index, E e);

    int size();
}
