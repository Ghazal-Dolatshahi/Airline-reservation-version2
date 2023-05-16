package Data;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Data<E> {

    private final ArrayList<E> list = new ArrayList<>();

    public ArrayList <E> search(E element) {
            if (list.size() != 0) {
                return (ArrayList<E>) list.stream()
                        .filter(elements -> elements.equals(element)).collect(Collectors.toList());
        }else
             return  null;
    }
    public void add(E element) {
        list.add(element);
    }

    public boolean remove(E element) {
        return list.removeIf(elements -> elements.equals(element));
    }

//    public void update(E element1, E element2)
//    {
//        element1.update(element2);
//    }
}