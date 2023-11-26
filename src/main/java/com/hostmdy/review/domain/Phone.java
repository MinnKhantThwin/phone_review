package com.hostmdy.review.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Phone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String brand;
	private String model;
	private LocalDate launch;
	private Double price;
	private String memory;
	
	@Enumerated(EnumType.STRING)
	private List<Sensors> sensors;
	
	@Lob
	private byte[] image;
	
	public Phone() {
		// TODO Auto-generated constructor stub
	}
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "platform_id")
	private Platform platform;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "body_id")
	private Body body;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "battery_id")
	private Battery battery;
	
	@OneToMany(mappedBy = "phone", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Display> displays = new ArrayList<>();
	
	@OneToMany(mappedBy = "phone", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Camera> cameras = new ArrayList<>();
	
	@OneToMany(mappedBy = "phone", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Review> reviews = new ArrayList<>();

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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public LocalDate getLaunch() {
		return launch;
	}

	public void setLaunch(LocalDate launch) {
		this.launch = launch;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public List<Sensors> getSensors() {
		return sensors;
	}

	public void setSensors(List<Sensors> sensors) {
		this.sensors = sensors;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public Battery getBattery() {
		return battery;
	}

	public void setBattery(Battery battery) {
		this.battery = battery;
	}

	public List<Display> getDisplays() {
		return displays;
	}

	public void setDisplays(List<Display> displays) {
		this.displays = displays;
	}

	public List<Camera> getCameras() {
		return cameras;
	}

	public void setCameras(List<Camera> cameras) {
		this.cameras = cameras;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", name=" + name + ", brand=" + brand + ", model=" + model + ", launch=" + launch
				+ ", price=" + price + ", memory=" + memory  + ", image=" + Arrays.toString(image) + "]";
	}

}
