package com.ikubinfo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.ikubinfo.enums.AttributeType;

@Entity
@Table(name = "attribute")

public class AttributeEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", columnDefinition = "varchar")
	private AttributeType type;

	@ManyToMany(mappedBy = "cabinAttributes")
	private List<CabinEntity> cabins;

	@ManyToMany(mappedBy = "siteAttributes")
	private List<SiteEntity> sites;

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

	@Override
	public Integer getId() {

		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;

	}

}
