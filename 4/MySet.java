import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class MySet<T> extends AbstractSet<T> implements SortedSet<T> {
    private T[] array;
    private Comparator<? super T> comparator;
    private Class<T> tClass;

    public MySet(Comparator<? super T> comparator, Class<T> tClass) {
        this.comparator = comparator;
        this.tClass = tClass;
        this.array = (T[]) Array.newInstance(tClass, 0);
    }

    public MySet(Collection<? extends T> collection, Comparator<? super T> comparator, Class<T> tClass) {
        this.comparator = comparator;
        this.tClass = tClass;
        this.array = (T[]) Array.newInstance(tClass, 0);
        List<T> list = collection.stream().sorted(comparator).collect(Collectors.toList());
        addAll(list);
    }

    @Override
    public boolean add(T elem) {
        for (int i = 0; i < size(); i++) {
            if (array[i] == elem) {
                return true;
            }
        }

        if (size() > 0 && array[array.length-1] == null) {
            array[array.length-1] = elem;
            boolean added = (array[array.length-1] == elem);
            array = (T[]) Arrays.stream(array).sorted(comparator).toArray();
            return added;
        }

        T[] newArray = (T[]) Array.newInstance(tClass, array.length+1);
        copyArrays(array, newArray);
        newArray[newArray.length-1] = elem;
        boolean added = (newArray[newArray.length-1] == elem);
        newArray = (T[]) Arrays.stream(newArray).sorted(comparator).toArray();
        array = newArray;
        return added;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(array);
    }

    @Override
    public int size() {
        int size = 0;
        for (T a : array) {
            if (a != null) {
                size++;
            }
        }
        return size;
    }

    @Override
    public Comparator<? super T> comparator() {
        return comparator;
    }

    @Override
    public MySet<T> subSet(T fromElement, T toElement) {
        int from = index(fromElement);
        int to = index(toElement);
        MySet newSet = new MySet(comparator, first().getClass());
        for (int i = from; i < to; i++) {
            newSet.add(array[i]);
        }
        return newSet;
    }

    @Override
    public MySet<T> headSet(T toElement) {
        int to = index(toElement);
        MySet newSet = new MySet(comparator, first().getClass());
        for (int i = 0; i < to; i++) {
            newSet.add(array[i]);
        }
        return newSet;
    }

    @Override
    public MySet<T> tailSet(T fromElement) {
        int from = index(fromElement);
        MySet newSet = new MySet(comparator, first().getClass());
        for (int i = from; i < size(); i++) {
            newSet.add(array[i]);
        }
        return newSet;
    }

    @Override
    public String toString() {
        String s = "{";
        if (size()>1) {
            for (int i = 0; i < size()-2; i++) {
                s += array[i] + ", ";
            }
            s += array[size()-2];
            if (array[size()-1] != null) {
                s += ", " + array[size()-1] + "}";
            } else {
                s += "}";
            }
        } else if (size() == 1) {
            s += array[size()-1] + "}";
        } else {
            s += "}";
        }
        return s;
    }


    @Override
    public T first() {
        if (size() == 0) {
            throw new NoSuchElementException();
        } else {
            return array[0];
        }
    }

    @Override
    public T last() {
        if (size() == 0) {
            throw new NoSuchElementException();
        } else {
            return array[size() - 1];
        }
    }

    private void copyArrays(T[] source, T[] target){
        for (int i = 0; i < source.length; i++) {
            target[i] = source[i];
        }
    }

    private int index(T elem) {
        for (int i = 0; i < size(); i++) {
            if (array[i] == elem) {
                return i;
            }
        }
        return -1;
    }


    class MyIterator<T> implements Iterator<T> {

        private T[] array;
        private int current;

        public MyIterator(T[] arr) {
            this.array = arr;
        }

        @Override
        public boolean hasNext() {
            return current < array.length;
        }

        @Override
        public T next() {
            return array[current++];
        }

        @Override
        public void remove() {
            current--;
            int indexToRemove = current;
            for (int i = indexToRemove; i < array.length-1; i++) {
                array[i] = array[i+1];
            }
            array[array.length-1] = null;
        }
    }
}
