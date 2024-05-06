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
	private Integer price;
	private String size;
	private List<Option> options;
	private Need need;
	private Material material;
	private Company company;
	private Patient patient;
	
	
		public Prosthetic() {
		super();
		this.options = new ArrayList<Option>();		
	}

		public Prosthetic(Integer ID, Integer price, String size, Need need, Material material,
				Company company, Patient patient) {
			super();
			this.ID = ID;
			this.price = price;
			this.size = size;
			this.options = new ArrayList<Option>();
			this.need = need;
			this.material = material;
			this.company = company;
			this.patient = patient;
		}
		

		public Integer getID() {
			return ID;
		}

		public void setID(Integer iD) {
			ID = iD;
		}

		public Integer getPrice() {
			return price;
		}

		public void setPrice(Integer price) {
			this.price = price;
		}

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

		public List<Option> getOptions() {
			return options;
		}

		public void setOptions(List<Option> options) {
			this.options = options;
		}

		public Need getNeed() {
			return need;
		}

		public void setNeed(Need need) {
			this.need = need;
		}

		public Material getMaterial() {
			return material;
		}

		public void setMaterial(Material material) {
			this.material = material;
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
			return Objects.equals(ID, other.ID);
		}
		
		

		@Override
		public String toString() {
			return "Prosthetic [ID=" + ID + ", price=" + price + ", size=" + size + ", options=" + options + ", need="
					+ need + ", material=" + material + ", company=" + company + ", patient=" + patient + "]";
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
	

}
