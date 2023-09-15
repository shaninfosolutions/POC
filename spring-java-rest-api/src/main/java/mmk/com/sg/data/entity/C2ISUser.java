package mmk.com.sg.data.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "C2IS_USER")
public class C2ISUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5800577473079530117L;
	@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
	
	@GeneratedValue(generator="C2IS_USER_SEQ")
	@SequenceGenerator(name="C2IS_USER_SEQ",sequenceName="C2IS_USER_SEQ", allocationSize=1)
	private long id;
	
	@Column(name = "userid")
	private String userId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "avatar")
	private String avatar;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "channel")
	private Long channel;
	
	

	public C2ISUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	
	public Long getChannel() {
		return channel;
	}

	public void setChannel(Long channel) {
		this.channel = channel;
	}
	
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", avatar=" + avatar + ", role=" + role + ", email=" + email + "]";
	}
	
	
}
