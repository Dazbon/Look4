package eu.heliopora.look4.commons.exceptions;

import java.io.Serializable;

public class EntityNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    /** id that could not be found in the database */
    final private Serializable entityId;
    final private String message;

    public EntityNotFoundException(Class<?> entityClass, Serializable entityId) {
        this(entityClass, entityId, null);
    }

    public EntityNotFoundException(Class<?> entityClass, Serializable entityId, Throwable cause) {
        this.message = "Instance of " + entityClass.getName() + " with primary key " + entityId + " cannot be found.";
        this.entityId = entityId;
    }

    public Serializable getEntityId() {
        return entityId;
    }

    @Override
    public String toString() {
        return message;
    }
}
