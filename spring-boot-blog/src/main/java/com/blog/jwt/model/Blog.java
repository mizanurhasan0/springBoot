package com.blog.jwt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition="TEXT")
    private String content;
    private Date created=new Date();
    private String userid;
    private boolean active=true;

    public Blog() {  }

    public Blog(String title, String content,String userid ) {
        this.setTitle(title);
        this.setContent(content);
        this.setUserid(userid);
    }

    public Blog(Long id, String title, String content,String userId,boolean active) {
        this.setId(id);
        this.setTitle(title);
        this.setContent(content);
        this.setUserid(userId);
        this.setActive(active);
    }

    public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", content=" + content + ", created=" + created + ", userid="
				+ userid + "]";
	}

   

}

