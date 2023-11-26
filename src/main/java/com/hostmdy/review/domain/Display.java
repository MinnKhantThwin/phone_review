package com.hostmdy.review.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Display {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private DisplayType type;
	
	private String name;
	private String resolution;
	private String size;
	private String refresh_rate;
	private String aspect_ratio;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "phone_id")
	private Phone phone;
	
	public Display() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DisplayType getType() {
		return type;
	}

	public void setType(DisplayType type) {
		this.type = type;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getRefresh_rate() {
		return refresh_rate;
	}

	public void setRefresh_rate(String refresh_rate) {
		this.refresh_rate = refresh_rate;
	}

	public String getAspect_ratio() {
		return aspect_ratio;
	}

	public void setAspect_ratio(String aspect_ratio) {
		this.aspect_ratio = aspect_ratio;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Display [id=" + id + ", name=" + name + ", type=" + type + ", resolution=" + resolution + ", size=" + size
				+ ", refresh_rate=" + refresh_rate + ", aspect_ratio=" + aspect_ratio
				+ "]";
	}

}
