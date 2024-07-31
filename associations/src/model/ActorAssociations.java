package model;

import association.AssociationAdapter;
import association.Linkable;

import java.util.Map;


public final class ActorAssociations extends AssociationAdapter {

    public ActorAssociations(Linkable object) {
        super(
                object,
                Map.of(
                        ActorRoles.PLAYED_IN, Integer.MAX_VALUE,
                        ActorRoles.DIRECTED, Integer.MAX_VALUE
                ),
                Map.of(
                        ActorRoles.PLAYED_IN, Movie.class,
                        ActorRoles.DIRECTED, Movie.class
                ),
                Map.of(
                        ActorRoles.PLAYED_IN, MovieAssociations.MovieRoles.STARRED,
                        ActorRoles.DIRECTED, MovieAssociations.MovieRoles.DIRECTED_BY
                ),
                Map.of(
                        ActorRoles.PLAYED_IN, AssociationType.BASIC,
                        ActorRoles.DIRECTED, AssociationType.BASIC
                )
        );
    }

    public enum ActorRoles {
        PLAYED_IN,
        DIRECTED
    }
}
