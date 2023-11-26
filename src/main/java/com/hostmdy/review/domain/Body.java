package com.hostmdy.review.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Body {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String dimensions;
	private Double weight;
	private Boolean headphone_jack;
	private String usb_type;
	
	public Body() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Boolean getHeadphone_jack() {
		return headphone_jack;
	}

	public void setHeadphone_jack(Boolean headphone_jack) {
		this.headphone_jack = headphone_jack;
	}

	public String getUsb_type() {
		return usb_type;
	}

	public void setUsb_type(String usb_type) {
		this.usb_type = usb_type;
	}

	@Override
	public String toString() {
		return "Body [id=" + id + ", dimensions=" + dimensions + ", weight=" + weight +
				", headphone_jack=" + headphone_jack + ", usb_type=" + usb_type + "]";
	}

}
