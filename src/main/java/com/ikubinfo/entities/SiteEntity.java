package com.ikubinfo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "site")
public class SiteEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private int code;

	@Column
	private String description;

	@Column
	private String location;

	@OneToMany(mappedBy = "site")
	private List<CabinEntity> cabins;

	@ManyToMany
	@JoinTable(name = "site_attribute", joinColumns = @JoinColumn(name = "site_id"), inverseJoinColumns = @JoinColumn(name = "attribute_id"))
	private List<AttributeEntity> siteAttributes;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<CabinEntity> getCabins() {
		return cabins;
	}

	public void setCabins(List<CabinEntity> cabins) {
		this.cabins = cabins;
	}

	public List<AttributeEntity> getSiteAttributes() {
		return siteAttributes;
	}

	public void setSiteAttributes(List<AttributeEntity> siteAttributes) {
		this.siteAttributes = siteAttributes;
	}

	@Override
	public String toString() {
		return "SiteEntity [id=" + id + ", code=" + code + ", description=" + description + ", location=" + location
				+ "]";
	}

	@Override
	public int getId() {

		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

}
