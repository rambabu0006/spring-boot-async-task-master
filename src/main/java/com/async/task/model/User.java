package com.async.task.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class User {

    private String name;
    private String blog;

    public User(String name, String blog) {
		super();
		this.name = name;
		this.blog = blog;
	}

	public String getName() {
        return name;
    }

    public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setName(String name) {
        this.name = name;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", blog=" + blog + "]";
    }

}
