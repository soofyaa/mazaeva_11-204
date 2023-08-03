import java.lang.reflect.Array;
import java.util.*;

public class ModifiableCollection<T> extends AbstractCollection<T> {
    public T[] array;
    public Class<T> tClass;

    public ModifiableCollection(Class<T> tClass, Collection<T> tCollection) {
        this.tClass = tClass;
        this.array = (T[]) Array.newInstance(tClass, tCollection.size());
        Iterator<T> iterator = tCollection.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            this.array[index] = iterator.next();
            index++;
        }
    }

    public ModifiableCollection() {

    }

    public boolean add(T elem) {
        T[] newArray;
        if (array != null) {
            newArray = (T[]) Array.newInstance(elem.getClass(), size()+1);
            copyArrays(array, newArray);
        } else {
            newArray = (T[]) Array.newInstance(elem.getClass(), 1);
        }
        newArray[newArray.length-1] = elem;
        array = newArray;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(array);
    }

    @Override
    public int size() {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                return i;
            }
        }
        return array.length;
    }

    private void copyArrays(T[] source, T[] target){
        for (int i = 0; i < source.length; i++) {
            if (source[i] == null) {
                break;
            }
            target[i] = source[i];
        }
    }

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
            /*
            current--;
            int indexToRemove = current;
            T[] newArray = (T[]) Array.newInstance(tClass, size()-1);
            for (int i = 0; i < indexToRemove; i++) {
                newArray[i] = array[i];
            }

            for (int i = indexToRemove; i < array.length-1; i++) {
                newArray[i] = array[i+1];
            }
            array = newArray;
             */

            current--;
            int indexToRemove = current;
            for (int i = indexToRemove; i < array.length-1; i++) {
                array[i] = array[i+1];
            }
            array[array.length-1] = null;
        }
    }
}
