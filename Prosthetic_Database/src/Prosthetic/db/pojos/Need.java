package Prosthetic.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "needs")
@XmlType(propOrder = {"type"})
public class Need implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3018484467545691840L;
	private Integer id;
	@XmlAttribute
	private String type;
	@XmlTransient
	private List<Prosthetic> prosthetics;
	@XmlTransient
	private List<Patient> patients;
	
	public Need() {
		super();
		this.prosthetics = new ArrayList<Prosthetic>();
		this.patients = new ArrayList<Patient>();
	}
	
	
	public Need(String type) {
		super();
		this.type = type;
		this.prosthetics = new ArrayList<Prosthetic>();
		this.patients = new ArrayList<Patient>();

	}


	public Need(Integer id, String type) {
		super();
		this.id = id;
		this.type = type;
		this.prosthetics = new ArrayList<Prosthetic>();
		this.patients = new ArrayList<Patient>();

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
	
	
	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
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
		return "Need [id=" + id + ", type=" + type +"]";
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
