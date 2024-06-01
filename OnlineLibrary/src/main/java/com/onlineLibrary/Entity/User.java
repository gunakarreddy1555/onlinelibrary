package com.onlineLibrary.Entity;

import java.util.HashSet;
import java.util.Set;
import com.onlineLibrary.Entity.Books;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String gender;
	private String email;
	private String password;
	private String Address;
	@ManyToMany
	private Set<Books> listofbooks=new HashSet<>();
	public User(int id, String name, String gender, String email, String password, String address,
			Set<Books> listofbooks) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.password = password;
		Address = address;
		this.listofbooks = listofbooks;
	}
	
	public User() {}

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Set<Books> getListofbooks() {
		return listofbooks;
	}

	public void setListofbooks(Set<Books> listofbooks) {
		this.listofbooks = listofbooks;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", gender=" + gender + ", email=" + email + ", password="
				+ password + ", Address=" + Address + ", listofbooks=" + listofbooks + "]";
	}
	
	
	
}

