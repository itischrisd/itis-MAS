package util;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

@SuppressWarnings("unused")
public abstract class ObjectPlusPlus extends ExtensiatedObject implements Serializable {

    private static final Set<ObjectPlusPlus> allParts = new HashSet<>();
    private Map<String, Map<Object, ObjectPlusPlus>> links = new Hashtable<>();

    public ObjectPlusPlus() {
        super();
    }

    private void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier, int counter) {
        Map<Object, ObjectPlusPlus> objectLinks;

        if (counter < 1) {
            return;
        }

        if (links.containsKey(roleName)) {
            objectLinks = links.get(roleName);
        } else {
            objectLinks = new HashMap<>();
            links.put(roleName, objectLinks);
        }

        if (!objectLinks.containsKey(qualifier)) {
            objectLinks.put(qualifier, targetObject);
            targetObject.addLink(reverseRoleName, roleName, this, this, counter - 1);
        }
    }

    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier) {
        addLink(roleName, reverseRoleName, targetObject, qualifier, 2);
    }

    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject) {
        addLink(roleName, reverseRoleName, targetObject, targetObject);
    }

    public void addPart(String roleName, String reverseRoleName, ObjectPlusPlus partObject) throws Exception {
        // Check if the part exist somewhere
        if (allParts.contains(partObject)) {
            throw new Exception("The part is already connected to a whole!");
        }

        addLink(roleName, reverseRoleName, partObject);

        // Store adding the object as a part
        allParts.add(partObject);
    }

    public ObjectPlusPlus[] getLinks(String roleName) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if (!links.containsKey(roleName)) {
            // No links for the role
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);

        return objectLinks.values().toArray(new ObjectPlusPlus[0]);
    }

    public void showLinks(String roleName, PrintStream stream) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if (!links.containsKey(roleName)) {
            // No links
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);

        Collection<ObjectPlusPlus> col = objectLinks.values();

        stream.println(this.getClass().getSimpleName() + " links, role '" + roleName + "':");

        for (Object obj : col) {
            stream.println("   " + obj);
        }
    }

    public ObjectPlusPlus getLinkedObject(String roleName, Object qualifier) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if (!links.containsKey(roleName)) {
            // No links
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        if (!objectLinks.containsKey(qualifier)) {
            // No link for the qualifer
            throw new Exception("No link for the qualifer: " + qualifier);
        }

        return objectLinks.get(qualifier);
    }

    public boolean anyLink(String nazwaRoli) {
        if (!links.containsKey(nazwaRoli)) {
            return false;
        }

        Map<Object, ObjectPlusPlus> links = this.links.get(nazwaRoli);
        return !links.isEmpty();
    }

    public boolean isLink(String roleName, ObjectPlusPlus targetObject) {
        Map<Object, ObjectPlusPlus> objectLink;

        if (!links.containsKey(roleName)) {
            // No links for the role
            return false;
        }

        objectLink = links.get(roleName);

        return objectLink.containsValue(targetObject);
    }

    private void switchAssociations(ObjectPlusPlus newObject) {
        for (Map.Entry<String, Map<Object, ObjectPlusPlus>> entry : links.entrySet()) {
            for (Map.Entry<Object, ObjectPlusPlus> linkEntry : entry.getValue().entrySet()) {
                ObjectPlusPlus targetObject = linkEntry.getValue();

                for (Map.Entry<String, Map<Object, ObjectPlusPlus>> targetEntry : targetObject.links.entrySet()) {
                    for (Map.Entry<Object, ObjectPlusPlus> targetLinkEntry : targetEntry.getValue().entrySet()) {
                        if (targetLinkEntry.getValue() == this) {
                            targetLinkEntry.setValue(newObject);
                        }
                    }
                }
            }
        }
    }

    public void transferLinks(ObjectPlusPlus newObject) {
        switchAssociations2(newObject);
        newObject.links = this.links;
        this.links = new HashMap<>();
        ExtensiatedObject.removeObject(this);
    }

    private void switchAssociations2(ObjectPlusPlus newObject) {
        for (Map<Object, ObjectPlusPlus> entry : links.values()) {
            for (ObjectPlusPlus targetObject : entry.values()) {
                String reverseRoleName = targetObject.getReverseRoleName(this);
                Map<Object, ObjectPlusPlus> targetLinks = targetObject.links.get(reverseRoleName);

                // obiekt sam jest kwalifikatorem - asocjacja zwykła
                if (targetLinks.containsKey(this)) {
                    targetLinks.remove(this);
                    targetLinks.put(newObject, newObject);
                } else {
                    // asocjacja kwalifikowana - podmieniamy obiekt pod kwalfikatorem
                    targetLinks.forEach((qualifier, object) -> {
                        if (object == this) {
                            targetLinks.remove(qualifier);
                            targetLinks.put(qualifier, newObject);
                        }
                    });
                }
            }
        }
    }

    public String getReverseRoleName(ObjectPlusPlus object) {
        for (Map.Entry<String, Map<Object, ObjectPlusPlus>> entry : links.entrySet()) {
            if (entry.getValue().containsValue(object)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private void removeLink(String roleName, Object qualifier) {
        if (links.containsKey(roleName)) {
            links.get(roleName).remove(qualifier);
        }
    }
}

