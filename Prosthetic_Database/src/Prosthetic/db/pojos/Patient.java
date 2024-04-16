package Prosthetic.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Patient implements Serializable{
	
	private Integer id;
	private String name;
	private String surname;
	private String sex;
	private Date dob;
	private String condition;
	private String report;
	private List<Prosthetic> prosthetics;
	
	

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Patient(Integer id, String name, String surname, String sex, Date dob, String condition, String report,
			List<Prosthetic> prosthetics) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.dob = dob;
		this.condition = condition;
		this.report = report;
		this.prosthetics = prosthetics;
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

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
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
				+ ", condition=" + condition + ", report=" + report + ", prosthetics=" + prosthetics + "]";
	}

	
	
	

}
