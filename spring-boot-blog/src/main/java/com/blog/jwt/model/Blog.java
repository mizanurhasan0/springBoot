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
    private int id;

    private String title;
    @Column(columnDefinition="TEXT")
    private String content;
    private Date created=new Date();
    private String useremail;
    private boolean active=true;
    private String userid;

    public Blog() {  }

    public Blog(String title, String content,String useremail,String userid ) {
        this.setTitle(title);
        this.setContent(content);
        this.setUseremail(useremail);
        this.setUserid(userid);
    }

    public Blog(int id, String title, String content,String useremail,boolean active,String userid) {
        this.setId(id);
        this.setTitle(title);
        this.setContent(content);
        this.setUseremail(useremail);
        this.setActive(active);
        this.userid=userid;
    }

    public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", content=" + content + ", created=" + created + ", useremail="
				+ useremail + ", active=" + active + "]";
	}



   

}

