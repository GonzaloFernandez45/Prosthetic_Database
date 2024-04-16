package Prosthetic.db.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Need implements Serializable {
	private Integer id;
	private String type;
	private Prosthetic prosthetic;
	
	public Need() {
		super();
		
	}
	
	public Need(Integer id, String type, Prosthetic prosthetic) {
		super();
		this.id = id;
		this.type = type;
		this.prosthetic = prosthetic;
		
	}
	
	public Integer getId() {
		return id;
		
	}
	public String getType() {
		return type;
		
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setType(String type) {
		this.type = type;
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
		Need other = (Need) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
		
	}
	
	@Override
	public String toString() {
		return "Need [id = " + id + ", type= " +type+ "]";
	}
	
	//Quizas esta bien poner metodos para añadir y quitar needs de la lista de needs que se usa luego

}
