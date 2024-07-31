package association;

import model.ExtensiatedObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AssociationAdapter extends ExtensiatedObject {

    private final Linkable handledObject;
    private final Map<Enum<?>, Integer> cardinalities;
    private final Map<Enum<?>, Class<? extends Linkable>> allowedClasses;
    private final Map<Enum<?>, Enum<?>> reverseRoles;
    private final Map<Enum<?>, AssociationType> associationTypes;
    private final Map<Enum<?>, List<Linkable>> basicLinks;
    private final Map<Enum<?>, Map<Object, Linkable>> qualifiedLinks;
    private final Map<Enum<?>, List<Linkable>> allPartObjects;

    public AssociationAdapter(
            Linkable handledObject,
            Map<Enum<?>, Integer> cardinalities,
            Map<Enum<?>, Class<? extends Linkable>> allowedClasses,
            Map<Enum<?>, Enum<?>> reverseRoles,
            Map<Enum<?>, AssociationType> associationTypes
    ) {
        super();
        this.handledObject = handledObject;
        this.cardinalities = cardinalities;
        this.allowedClasses = allowedClasses;
        this.reverseRoles = reverseRoles;
        this.associationTypes = associationTypes;
        this.basicLinks = new HashMap<>();
        this.qualifiedLinks = new HashMap<>();
        this.allPartObjects = new HashMap<>();
    }

    public void addLink(Enum<?> role, Linkable targetObject) {
        if (!allowedClasses.get(role).isInstance(targetObject)) {
            throw new IllegalArgumentException("Target object is not allowed for this role");
        }
        switch (associationTypes.get(role)) {
            case BASIC:
            case COMPOSITION_PART:
                addBasicLink(role, targetObject);
                break;
            case BAG:
                addBagLink(role, targetObject);
                break;
            case COMPOSITION_WHOLE:
                addCompositionLink(role, targetObject);
                break;
            default:
                throw new IllegalArgumentException("Association type not supported by this method");
        }
    }

    private void addBasicLink(Enum<?> role, Linkable targetObject) {
        if (!basicLinks.containsKey(role)) {
            basicLinks.put(role, new ArrayList<>());
        }
        if (basicLinks.get(role).size() >= cardinalities.get(role)) {
            throw new IllegalArgumentException("Cardinality exceeded");
        }
        if (!isLinked(role, targetObject)) {
            basicLinks.get(role).add(targetObject);
            targetObject.addLink(reverseRoles.get(role), handledObject);
        }
    }

    private void addBagLink(Enum<?> role, Linkable targetObject) {
        if (!basicLinks.containsKey(role)) {
            basicLinks.put(role, new ArrayList<>());
        }
        if (basicLinks.get(role).size() >= cardinalities.get(role)) {
            throw new IllegalArgumentException("Cardinality exceeded");
        }
        basicLinks.get(role).add(targetObject);
        if (basicLinks.get(role).stream().filter(link -> link.equals(targetObject)).count() !=
                targetObject.getLinks(reverseRoles.get(role)).stream().filter(link -> link.equals(handledObject))
                        .count()) {
            targetObject.addLink(reverseRoles.get(role), handledObject);
        }
    }

    private void addCompositionLink(Enum<?> role, Linkable targetObject) {
        if (!basicLinks.containsKey(role)) {
            basicLinks.put(role, new ArrayList<>());
        }
        if (basicLinks.get(role).size() >= cardinalities.get(role)) {
            throw new IllegalArgumentException("Cardinality exceeded");
        }
        if (allPartObjects.containsKey(role)) {
            if (allPartObjects.get(role).contains(targetObject)) {
                throw new IllegalArgumentException("Object is already a part of a whole");
            }
        } else {
            allPartObjects.put(role, new ArrayList<>());
        }
        basicLinks.get(role).add(targetObject);
        targetObject.addLink(reverseRoles.get(role), handledObject);
    }

    public void addLink(Enum<?> role, Linkable targetObject, Object qualifier) {
        if (associationTypes.get(role) != AssociationType.QUALIFIED) {
            throw new IllegalArgumentException("Association type is not qualified");
        }
        if (!allowedClasses.get(role).isInstance(targetObject)) {
            throw new IllegalArgumentException("Target object is not allowed for this role");
        }
        if (!qualifiedLinks.containsKey(role)) {
            qualifiedLinks.put(role, new HashMap<>());
        }
        if (qualifiedLinks.get(role).size() >= cardinalities.get(role)) {
            throw new IllegalArgumentException("Cardinality exceeded");
        }
        qualifiedLinks.get(role).put(qualifier, targetObject);
        targetObject.addLink(reverseRoles.get(role), handledObject);
    }

    private boolean isLinked(Enum<?> role, Linkable targetObject) {
        return switch (associationTypes.get(role)) {
            case BASIC, BAG, COMPOSITION_WHOLE, COMPOSITION_PART -> basicLinks.get(role).contains(targetObject);
            case QUALIFIED -> qualifiedLinks.get(role).containsValue(targetObject);
        };
    }

    public List<ExtensiatedObject> getLinks(Enum<?> role) {
        switch (associationTypes.get(role)) {
            case BASIC, BAG, COMPOSITION_WHOLE, COMPOSITION_PART -> {
                if (!basicLinks.containsKey(role)) {
                    throw new IllegalArgumentException("No links for this role");
                }
                return basicLinks.get(role).stream().map(link -> (ExtensiatedObject) link).toList();
            }
            case QUALIFIED -> {
                if (!qualifiedLinks.containsKey(role)) {
                    throw new IllegalArgumentException("No links for this role");
                }
                return qualifiedLinks.get(role).values().stream().map(link -> (ExtensiatedObject) link).toList();
            }
            default -> throw new IllegalArgumentException("Association type not supported");
        }
    }

    public ExtensiatedObject getByQualifier(Enum<?> role, Object qualifier) {
        if (associationTypes.get(role) != AssociationType.QUALIFIED) {
            throw new IllegalArgumentException("Association type is not qualified");
        }
        if (!qualifiedLinks.containsKey(role)) {
            throw new IllegalArgumentException("No links for this role");
        }
        return (ExtensiatedObject) qualifiedLinks.get(role).get(qualifier);
    }

    public void removeLink(Enum<?> role, Linkable targetObject) {
        if (!allowedClasses.get(role).isInstance(targetObject)) {
            throw new IllegalArgumentException("Target object is not allowed for this role");
        }
        switch (associationTypes.get(role)) {
            case BASIC, BAG, COMPOSITION_PART -> {
                if (!basicLinks.containsKey(role)) {
                    throw new IllegalArgumentException("No links for this role");
                }
                if (!basicLinks.get(role).contains(targetObject)) {
                    throw new IllegalArgumentException("Link not found");
                }
                if (basicLinks.get(role).contains(targetObject)) {
                    basicLinks.get(role).remove(targetObject);
                    targetObject.removeLink(reverseRoles.get(role), handledObject);
                }
            }
            case QUALIFIED -> {
                if (!qualifiedLinks.containsKey(role)) {
                    throw new IllegalArgumentException("No links for this role");
                }
                if (!qualifiedLinks.get(role).containsValue(targetObject)) {
                    throw new IllegalArgumentException("Link not found");
                }
                if (basicLinks.get(role).contains(targetObject)) {
                    basicLinks.get(role).remove(targetObject);
                    targetObject.removeLink(reverseRoles.get(role), handledObject);
                }
            }
            case COMPOSITION_WHOLE -> {
                if (!basicLinks.containsKey(role)) {
                    throw new IllegalArgumentException("No links for this role");
                }
                if (!basicLinks.get(role).contains(targetObject)) {
                    throw new IllegalArgumentException("Link not found");
                }
                if (basicLinks.get(role).contains(targetObject)) {
                    basicLinks.get(role).remove(targetObject);
                    targetObject.removeEveryLink();
                }
            }
        }
    }

    public void removeEveryLink() {
        basicLinks.forEach((role, links) -> links.forEach(link -> link.removeLink(reverseRoles.get(role), handledObject)));
        basicLinks.clear();
        qualifiedLinks.forEach((role, links) -> links.values()
                .forEach(link -> link.removeLink(reverseRoles.get(role), handledObject)));
        qualifiedLinks.clear();
    }

    public enum AssociationType {
        BASIC,
        BAG,
        QUALIFIED,
        COMPOSITION_WHOLE,
        COMPOSITION_PART
    }
}
