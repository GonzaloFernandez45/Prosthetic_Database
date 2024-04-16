package Prosthetic.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Prosthetic implements Serializable {
	
	private Integer ID;
	private Integer Price;
	private String Size;
	private List<Need> needs;
	private List<Option> options;
	
	public Prosthetic() {
		super();
		this.needs = new ArrayList<Need>();
		this.options = new ArrayList<Option>();
		// TODO Auto-generated constructor stub
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public String getSize() {
		return Size;
	}

	public void setSize(String size) {
		Size = size;
	}

	public List<Need> getNeeds() {
		return needs;
	}

	public void setNeeds(List<Need> needs) {
		this.needs = needs;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prosthetic other = (Prosthetic) obj;
		return ID == other.ID;
	}

	@Override
	public String toString() {
		return "Prosthetic [ID=" + ID + ", Price=" + Price + ", Size=" + Size + ", needs=" + needs + ", options="
				+ options + "]";
	}
	
	
	
	

}
