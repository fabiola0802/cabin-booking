package com.ikubinfo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "attribute")
public class AttributeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	private AttributeType type;

	@ManyToMany(mappedBy = "cabinAttributes")
	private List<CabinEntity> cabins;

	@ManyToMany(mappedBy = "siteAttributes")
	private List<SiteEntity> sites;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AttributeType getType() {
		return type;
	}

	public void setType(AttributeType type) {
		this.type = type;
	}

	public List<CabinEntity> getCabins() {
		return cabins;
	}

	public void setCabins(List<CabinEntity> cabins) {
		this.cabins = cabins;
	}

	public List<SiteEntity> getSites() {
		return sites;
	}

	public void setSites(List<SiteEntity> sites) {
		this.sites = sites;
	}

	@Override
	public String toString() {
		return "AttributeEntity [id=" + id + ", name=" + name + ", type=" + type + "]";
	}

}
