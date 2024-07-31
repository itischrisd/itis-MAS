package model;

import association.AssociationAdapter;
import association.Linkable;

public class Movie extends ExtensiatedObject implements Linkable {

    private final MovieAssociations associations;

    public Movie() {
        associations = new MovieAssociations(this);
    }

    @Override
    public AssociationAdapter getAssociations() {
        return associations;
    }
}
