package eu.heliopora.look4.commons.entities.base;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import eu.heliopora.look4.commons.persistence.IdGenerator;


public class BasePersistentObjectImpl implements PersistentObject {
	
	private String id = IdGenerator.createId();
	
    private Integer version;
    
    private Date creationTime;

    private Date modificationTime;
    
    private Boolean deleted;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreationTime() {
		return creationTime;
	}
    
    public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}
	
	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}
	
    public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public void prePersist() {
        Date now = new Date();
        this.creationTime = now;
        this.modificationTime = now;
    }
    
    public void preUpdate() {
        this.modificationTime = new Date();
    }

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof PersistentObject)) {
            return false;
        }
        PersistentObject other = (PersistentObject)o;
        if (id == null) return false;
        return id.equals(other.getId());
    }

    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return super.hashCode();
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
