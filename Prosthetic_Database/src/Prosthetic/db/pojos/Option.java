package Prosthetic.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Option implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1528975214796942946L;
	private Integer id;
	private String type;
	private List<Prosthetic> prosthetics;
	
	public Option() {
		super();
		this.prosthetics = new ArrayList<Prosthetic>();
	}

	

	public Option(Integer id, String type) {
		super();
		this.id = id;
		this.type = type;
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
		Option other = (Option) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Option [id=" + id + ", type=" + type + ", prosthetics=" + prosthetics + "]";
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
