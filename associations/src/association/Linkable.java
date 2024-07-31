package association;

import model.ExtensiatedObject;

import java.util.List;

public interface Linkable {

    AssociationAdapter getAssociations();

    default void addLink(Enum<?> role, Linkable targetObject) {
        getAssociations().addLink(role, targetObject);
    }

    default void addLink(Enum<?> role, Linkable targetObject, Object qualifier) {
        getAssociations().addLink(role, targetObject, qualifier);
    }

    default List<ExtensiatedObject> getLinks(Enum<?> role) {
        return getAssociations().getLinks(role);
    }

    default ExtensiatedObject getByQualifier(Enum<?> role, Object qualifier) {
        return getAssociations().getByQualifier(role, qualifier);
    }

    default void removeLink(Enum<?> role, Linkable targetObject) {
        getAssociations().removeLink(role, targetObject);
    }

    default void removeEveryLink() {
        getAssociations().removeEveryLink();
    }
}
