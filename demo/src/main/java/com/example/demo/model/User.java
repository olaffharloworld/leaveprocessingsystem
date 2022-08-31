package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class User {
 
    @Id
    @NotEmpty
    @Column(name = "userid")
    private String userId; 
    
    @NotEmpty
	@Column(name = "name")
    private String name;
    
	@Column(name = "password")
    private String password;

    public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email")
    private String email;
    @Column(name = "mobileNo")
    private String mobileNo;
    @Column(name = "enabled")
    private boolean enabled;
     
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "userrole",
            joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "roleid")
            )
    private Set<Role> roles = new HashSet<>();
    
    public User()
    {}


	public User(String userId, @NotEmpty String name, @NotEmpty String password, 
			String email, String mobileNo, boolean enabled, Set<Role> roles) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;

		this.email = email;
		this.mobileNo = mobileNo;
		this.enabled = enabled;
		this.roles = roles;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
 

 
    // remaining getters and setters are not shown for brevity
}