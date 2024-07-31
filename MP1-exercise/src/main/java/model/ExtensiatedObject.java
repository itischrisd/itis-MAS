package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtensiatedObject implements Serializable {

    private static Map<Class<? extends ExtensiatedObject>, List<ExtensiatedObject>> extents = new HashMap<>();

    public ExtensiatedObject() {
        Class<? extends ExtensiatedObject> type = this.getClass();

        if (!extents.containsKey(type)) {
            extents.put(type, new ArrayList<>());
        }

        extents.get(type).add(this);
    }

    public static void writeExtents(ObjectOutputStream out) throws IOException {
        out.writeObject(extents);
    }

    @SuppressWarnings("unchecked")
    public static void readExtents(ObjectInputStream in) throws IOException, ClassNotFoundException {
        extents = (Map<Class<? extends ExtensiatedObject>, List<ExtensiatedObject>>) in.readObject();
    }

    @SuppressWarnings("unchecked")
    protected static <T> Iterable<T> getExtent(Class<T> type) throws ClassNotFoundException {
        if (extents.containsKey(type) && type.isAssignableFrom(ExtensiatedObject.class)) {
            return (Iterable<T>) extents.get(type);
        }
        throw new ClassNotFoundException(String.format("%s not found. Stored extents: %s", type.getName(), extents.keySet()));
    }

    public static void showExtent(Class<? extends ExtensiatedObject> type) throws Exception {
        List<ExtensiatedObject> extent;

        if (extents.containsKey(type)) {
            extent = extents.get(type);
        } else {
            throw new ClassNotFoundException(String.format("%s not found. Stored extents: %s", type.getName(), extents.keySet()));
        }

        System.out.println("Extent of the class: " + type.getSimpleName());

        for (Object obj : extent) {
            System.out.println(obj);
        }
    }
}
