package com.edu;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class DefaultCustomArrayList<E> implements CustomArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;  // Начальный размер массива
    private Object[] elements;                       // Массив для хранения элементов
    private int size;

    public DefaultCustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void increaseCapacity() {
        int newCapacity = elements.length * 2;
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }


    @Override
    public boolean add(E element) {
        if (size == elements.length) {
            increaseCapacity();
        }
        elements[size++] = element;

        return true;
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return true;
    }

    @Override
    public boolean remove(E element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                elements[--size] = null;
                return true;  // Элемент найден и удалён
            }
        }
        return false;  // Элемент не найден
    }


    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        // Проверка индекса
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) elements[index];
    }

    @Override
    public int size() {

            return size;
        }


    @Override
    public boolean isEmpty() {

            return size == 0;
        }


    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) elements[currentIndex++];
            }
        };
    }
}
