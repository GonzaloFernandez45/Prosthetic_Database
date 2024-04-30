package Prosthetic.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Prosthetic implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6478906863426691556L;
	private Integer ID;
	private Integer Price;
	private String Size;
	private Need need;
	private List<Option> options;
	private Material material;
	private Company company;
	private Patient patient;
	
	
		public Prosthetic() {
		super();
		this.options = new ArrayList<Option>();
		// TODO Auto-generated constructor stub
	}
		

		public Prosthetic(Integer iD, String size,Company company, Patient patient,Need need,Integer price,Material material) {
			super();
			ID = iD;
			Price = price;
			Size = size;
			this.need = need;
			this.material = material;
			this.company = company;
			this.patient=patient;
		}





		public Prosthetic(Integer iD, String size, Need need, List<Option> options,Company company,Integer price,Material material) {
			super();
			ID = iD;
			Price = price;
			Size = size;
			this.need = need;
			this.options = options;
			this.material = material;
			this.company = company;
		}





		public void addOption (Option option) {
			if(!options.contains(option)) {
				this.options.add(option);
			}
		}
		
		public void removeOption (Option option) {
			if(options.contains(option)) {
				this.options.remove(option);
			}
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
	

	public Need getNeed() {
		return need;
	}





	public void setNeed(Need need) {
		this.need = need;
	}





	public Company getCompany() {
		return company;
	}





	public void setCompany(Company company) {
		this.company = company;
	}





	public Patient getPatient() {
		return patient;
	}





	public void setPatient(Patient patient) {
		this.patient = patient;
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
		return "Prosthetic [ID=" + ID + ", Price=" + Price + ", Size=" + Size + ", needs=" + need + ", options="
				+ options + "]";
	}
	
	
	
	

}
