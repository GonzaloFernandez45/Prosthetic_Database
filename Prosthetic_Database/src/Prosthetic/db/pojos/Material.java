package Prosthetic.db.pojos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Material implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7934186549735789454L;
	private Integer id;
	private String type;
	private String availability;
	private List<Prosthetic> prosthetics;
	
	public Material() {
		super();
		this.prosthetics = new ArrayList<Prosthetic>();		
		
		
	}

	public Material(Integer id, String type, String availability) {
		super();
		this.id = id;
		this.type = type;
		this.availability = availability;
		this.prosthetics = new ArrayList<Prosthetic>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public List<Prosthetic> getProsthetics() {
		return prosthetics;
	}

	public void setProsthetics(List<Prosthetic> prosthetics) {
		this.prosthetics = prosthetics;
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
		Material other = (Material) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Material [id=" + id + ", type=" + type+"]";
	}
	
	
	public void addProsthetic (Prosthetic prosthetic) {
		if(!prosthetics.contains(prosthetic)) {
			this.prosthetics.add(prosthetic);
		}
	}
	
	public void removeProsthetic (Prosthetic prosthetic) {
		if(prosthetics.contains(prosthetic)) {
			this.prosthetics.remove(prosthetic);
		}
	}
	

}
