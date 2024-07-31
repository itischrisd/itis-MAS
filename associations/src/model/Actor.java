package model;

import association.AssociationAdapter;
import association.Linkable;

public class Actor extends ExtensiatedObject implements Linkable {

    private final ActorAssociations associations;

    public Actor() {
        associations = new ActorAssociations(this);
    }

    @Override
    public AssociationAdapter getAssociations() {
        return associations;
    }
}
