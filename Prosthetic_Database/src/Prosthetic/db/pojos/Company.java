package Prosthetic.db.pojos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Company implements Serializable {
	
	private static final long serialVersionUID = -8993394672311262018L;
	private Integer id;
	private String name;
	private String location;
	private List <Prosthetic> prosthetics;
	
	
	public Company () {
		super();
		this.prosthetics=new ArrayList<Prosthetic> ();
		
	}
	
	


	public Company(Integer id, String name, String location) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
	}




	public Company(Integer id, String name, String location, List<Prosthetic> prosthetics) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.prosthetics = new ArrayList<Prosthetic> ();
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
		Company other = (Company) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", location=" + location + ", prosthetics=" + prosthetics + "]";
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public List<Prosthetic> getProsthetics() {
		return prosthetics;
	}


	public void setProsthetics(List<Prosthetic> prosthetics) {
		this.prosthetics = prosthetics;
	}
	
	//method to add to a list 
	
	public void addProsthetic (Prosthetic prosthetic) {
		if(!prosthetics.contains(prosthetic)) {
			this.prosthetics.add(prosthetic);
		}
	}
	//method to remove from a list 
	
	public void removeProsthetic (Prosthetic prosthetic) {
		if(prosthetics.contains(prosthetic)) {
			this.prosthetics.remove(prosthetic);
		}
	}
}
