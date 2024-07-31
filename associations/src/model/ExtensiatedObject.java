package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public abstract class ExtensiatedObject implements Serializable {

    private static Map<Class<? extends ExtensiatedObject>, List<ExtensiatedObject>> extents = new HashMap<>();

    @SuppressWarnings("unchecked")
    protected ExtensiatedObject() {
        Class<? extends ExtensiatedObject> type = this.getClass();

        while (type != ExtensiatedObject.class) {

            if (!extents.containsKey(type)) {
                extents.put(type, new ArrayList<>());
            }

            extents.get(type).add(this);
            type = (Class<? extends ExtensiatedObject>) type.getSuperclass();
        }
    }

    public static void writeExtents(ObjectOutputStream out) throws IOException {
        out.writeObject(extents);
    }

    @SuppressWarnings("unchecked")
    public static void readExtents(ObjectInputStream in) throws IOException, ClassNotFoundException {
        extents = (Map<Class<? extends ExtensiatedObject>, List<ExtensiatedObject>>) in.readObject();
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> getExtent(Class<T> type) throws ClassNotFoundException {
        if (extents.containsKey(type)) {
            return Collections.unmodifiableList((List<T>) extents.get(type));
        }
        throw new ClassNotFoundException(String.format("%s not found. Stored extents: %s", type.getName(), extents.keySet()));
    }

    public static void showExtent(Class<? extends ExtensiatedObject> type) throws ClassNotFoundException {
        List<ExtensiatedObject> extent;

        if (extents.containsKey(type)) {
            extent = extents.get(type);
        } else {
            throw new ClassNotFoundException(String.format("%s not found. Stored extents: %s", type.getName(), extents.keySet()));
        }

        System.out.println("Extent of the class: " + type.getSimpleName());

        for (Object object : extent) {
            System.out.println(object);
        }
    }

    public static void clearExtents() {
        extents.clear();
    }

    @SuppressWarnings("unchecked")
    public static void removeObject(ExtensiatedObject object) {
        if (object == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }

        Class<? extends ExtensiatedObject> type = object.getClass();

        while (type != ExtensiatedObject.class) {
            if (extents.containsKey(type) && extents.get(type).contains(object)) {
                extents.get(type).remove(object);
            } else {
                throw new IllegalArgumentException("Object not found in the extent of the class " + type.getName());
            }
            if (extents.get(type).isEmpty()) {
                extents.remove(type);
            }
            type = (Class<? extends ExtensiatedObject>) type.getSuperclass();
        }
    }
}
