package Prosthetic.db.pojos;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table ( name = "users")
public class User implements Serializable{
	
	private static final long serialVersionUID = -1903665991251142241L;
	@Id
	@GeneratedValue(generator = "users")
	@TableGenerator(name = "users", table = "sqlite_sequence",
	         pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "users")
	private Integer id; 
	private String username;
	@ManyToOne(fetch=FetchType.EAGER)
	private Role role;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", role=" + role + "]";
	}
	
	
	
}
