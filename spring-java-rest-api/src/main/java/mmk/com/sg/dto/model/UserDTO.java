package mmk.com.sg.dto.model;

import java.io.Serializable;

public class UserDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1720766463886761519L;
	
	private String status;
	private String userId;
	private String name;
	private String avator;
	private String role;
	
	private Long channel;

	public UserDTO(String status) {
		this.status=status;
	}

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(String status, String userId, String name, String avator, String role,Long channel) {
		super();
		this.status = status;
		this.userId = userId;
		this.name = name;
		this.avator = avator;
		this.role = role;
		this.channel=channel;
	}
	
	
	public Long getChannelNo() {
		return channel;
	}

	public void setChannelNo(Long channel) {
		this.channel = channel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getAvator() {
		return avator;
	}

	public void setAvator(String avator) {
		this.avator = avator;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDTO [status=" + status + ", userId=" + userId + ", name=" + name + ", avator=" + avator + ", role="
				+ role + "]";
	}
	
	
	
	
}
