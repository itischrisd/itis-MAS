package model;

import association.AssociationAdapter;
import association.Linkable;

import java.util.Map;

public class MovieAssociations extends AssociationAdapter {

    public MovieAssociations(Linkable handledObject) {
        super(
                handledObject,
                Map.of(
                        MovieRoles.STARRED, Integer.MAX_VALUE,
                        MovieRoles.DIRECTED_BY, 1
                ),
                Map.of(
                        MovieRoles.STARRED, Actor.class,
                        MovieRoles.DIRECTED_BY, Actor.class
                ),
                Map.of(
                        MovieRoles.STARRED, ActorAssociations.ActorRoles.PLAYED_IN,
                        MovieRoles.DIRECTED_BY, ActorAssociations.ActorRoles.DIRECTED
                ),
                Map.of(
                        MovieRoles.STARRED, AssociationType.BASIC,
                        MovieRoles.DIRECTED_BY, AssociationType.BASIC
                )
        );
    }

    public enum MovieRoles {
        STARRED,
        DIRECTED_BY
    }
}
