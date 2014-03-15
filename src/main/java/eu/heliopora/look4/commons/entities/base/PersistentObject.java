package eu.heliopora.look4.commons.entities.base;

public interface PersistentObject {

    public String getId();

    public void setId(String id);

    public Integer getVersion();

    public void setVersion(Integer version);

    public Boolean getDeleted();

    public void setDeleted(Boolean deleted);

}
