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
	private List<Need> needs;
	private List<Option> options;
	private Material material;
	
	
		public Prosthetic() {
		super();
		this.needs = new ArrayList<Need>();
		this.options = new ArrayList<Option>();
		// TODO Auto-generated constructor stub
	}
		

		public Prosthetic(Integer iD, Integer price, String size, List<Need> needs, List<Option> options,
				Material material) {
			super();
			ID = iD;
			Price = price;
			Size = size;
			this.needs = needs;
			this.options = options;
			this.material = material;
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
	
	
		public void addNeed (Need need) {
			if(!needs.contains(need)) {
				this.needs.add(need);
			}
		}
		
		public void removeNeed (Need need) {
			if(needs.contains(need)) {
				this.needs.remove(need);
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
