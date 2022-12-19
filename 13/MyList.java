public class MyList {
    private String[] strings = new String[10];
    private int lastNotFreeIndex = 0;

    public String get(int index){
        return strings[index];
    }

    public int size(){
        if (lastNotFreeIndex == 0) {
            return 0;
        }
        return lastNotFreeIndex;
    }

    public boolean add(String s){
        if (lastNotFreeIndex + 1 >= strings.length){
            String[] newArray = new String[strings.length+10];
            copyArrays(strings,newArray);
            strings = newArray;
        }
        strings[lastNotFreeIndex] = s;
        lastNotFreeIndex++;
        return true;
    }

    public boolean addAll(MyList otherList) {
        if (lastNotFreeIndex + otherList.size() >= strings.length) {
            String[] newArray = new String[strings.length + otherList.size() + 1];
            copyArrays(strings,newArray);
            strings = newArray;
        }

        for (int i = 0; i < otherList.size(); i++) {
            strings[lastNotFreeIndex] = otherList.get(i);
            lastNotFreeIndex++;
        }
        return true;
    }

    public boolean contains(String s) {
        for (int i = 0; i < size(); i++) {
            if (strings[i].equals(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(MyList otherList) {
        int len = size() - otherList.size() + 1;
        for (int i = 0; i < len; i++) {
            boolean check = true;
            for (int j = 0; j < otherList.size(); j++) {
                if ((get(i) != null && otherList.get(j) != null && get(i).equals(otherList.get(j))) || (get(i) == null && otherList.get(j) == null))  {
                    for (int k = 0; k < otherList.size(); k++) {
                        int iK = i + k;
                        int jK = j + k;
                        if ((get(iK) != null && otherList.get(jK) != null && (!(get(iK).equals(otherList.get(jK))))) || (get(iK) != null && otherList.get(jK) == null) || (get(iK) == null && otherList.get(jK) != null)) {
                            check = false;
                            break;
                        }
                        if ((get(iK) != null && otherList.get(jK) != null && get(iK).equals(otherList.get(jK)) || (get(iK) == null && otherList.get(jK) == null)) && j+1 == otherList.size()) {
                            return true;
                        }
                    }
                }
                if (!check) {
                    break;
                }
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < size(); i++) {
            strings[i] = null;
        }
        lastNotFreeIndex = 0;
    }

    public boolean isEmpty() {
        if (lastNotFreeIndex == 0) {
            return true;
        }
        return false;
    }

    public int indexOf(String s) {
        int index = 0;
        for (int i = 0; i < size(); i++) {
            if (strings[i].equals(s)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public String remove(int index) {
        String s = get(index);

        int len = size() - 1;
        for (int i = index; i < len; i++) {
            strings[i] = strings[i+1];
        }
        strings[len] = null;
        lastNotFreeIndex--;
        return s;
    }

    public boolean remove(String s) {
        int index = indexOf(s);
        remove(index);
        return true;
    }

    public boolean removeAll(MyList otherList) {
        for (int i = 0; i < otherList.size(); i++) {
            for (int j = 0; j < lastNotFreeIndex; j++) {
                if (get(j).equals(otherList.get(i))) {
                    remove(j);
                    j--;
                }
            }
        }
        return true;
    }

    private void copyArrays(String[] source, String[] target){
        for (int i = 0; i < source.length; i++) {
            target[i] = source[i];
        }
    }

    public boolean equals(MyList otherList) {
        if (size() == otherList.size()) {
            for (int i = 0; i < size(); i++) {
                if (!(get(i).equals(otherList.get(i)))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String s = "{" + get(0);
        for (int i = 1; i < size(); i++) {
            s += ", " + get(i);
        }
        return s + "}";
    }
}