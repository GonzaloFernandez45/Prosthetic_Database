package Prosthetic.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Patient implements Serializable{
	
	private static final long serialVersionUID = -8363292112398466611L;
	private Integer id;
	private String name;
	private String surname;
	private String sex;
	private Date dob;
	private Integer dni;
	private String report;
	private List<Prosthetic> prosthetics;
	private List<Need> needs;
	
	

	public Patient() {
		super();
		this.prosthetics = new ArrayList<Prosthetic>();
		this.needs = new ArrayList<Need>();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Patient(Integer id, String name, String surname) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.prosthetics = new ArrayList<Prosthetic>();
		this.needs = new ArrayList<Need>();
	}



	public Patient(Integer id, String name, String surname, String sex, Date dob, Integer dni, String report) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.dob = dob;
		this.dni = dni;
		this.report = report;
		this.prosthetics = new ArrayList<Prosthetic>();
		this.needs = new ArrayList<Need>();

	}

	public Patient(String name, String surname, String sex, Date dob, Integer dni, String report) {
		super();
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.dob = dob;
		this.dni = dni;
		this.report = report;
		this.prosthetics = new ArrayList<Prosthetic>();
		this.needs = new ArrayList<Need>();

	}


	public Patient(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
		this.prosthetics = new ArrayList<Prosthetic>();
		this.needs = new ArrayList<Need>();
	}


	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	
	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
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
		Patient other = (Patient) obj;
		return Objects.equals(id, other.id);
	}



	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", surname=" + surname + ", sex=" + sex + ", dob=" + dob
				+ ", dni=" + dni + ", report=" + report + ", prosthetics=" + prosthetics + ", needs=" + needs + "]";
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

		public List<Need> getNeeds() {
			return needs;
		}

		public void setNeeds(List<Need> needs) {
			this.needs = needs;
		}
	

}
