package com.ecommerce.org.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "image_model2")
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class ImageModel2 {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String type;
	@Lob
	@Column(name="pic")
	private String pic;
	

	public ImageModel2() {
		super();
	}
	
	public ImageModel2(String name, String type, String pic) {
		super();
		this.name = name;
		this.type = type;
		this.pic = pic;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}

	@Override
	public String toString() {
		return "ImageModel2 [id=" + id + ", name=" + name + ", type=" + type + ", pic=" + pic + "]";
	}

	

}
