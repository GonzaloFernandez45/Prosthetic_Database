	package Prosthetic.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Surgeon implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = -3852504384306294006L;
		private Integer id;
		private Integer salary;
		private String name;
		private String surname;
		private String specialization;
		private Date hiredate;
		private List<Surgery> surgeries;
		
		public Surgeon () {
			super();
			this.surgeries = new ArrayList<Surgery>();
			
		}

		
		public Surgeon(Integer id, Integer salary, String name, String surname, String specialization, Date hiredate,
				List<Surgery> surgeries) {
			super();
			this.id = id;
			this.salary = salary;
			this.name = name;
			this.surname = surname;
			this.specialization = specialization;
			this.hiredate = hiredate;
			this.surgeries = new ArrayList<Surgery>();;
		}
		
		public Surgeon(Integer id, Integer salary, String name, String surname, String specialization, Date hiredate) {
			super();
			this.id = id;
			this.salary = salary;
			this.name = name;
			this.surname = surname;
			this.specialization = specialization;
			this.hiredate = hiredate;
			this.surgeries = new ArrayList<Surgery>();
		}

		public Surgeon(String name, String surname, Integer salary, Date hiredate, String specialization) {
			super();
			this.salary = salary;
			this.name = name;
			this.surname = surname;
			this.specialization = specialization;
			this.hiredate = hiredate;
			this.surgeries = new ArrayList<Surgery>();
		}
		
		

		public Surgeon(Integer id, String name, String surname) {
			super();
			this.id = id;
			this.name = name;
			this.surname = surname;
			this.surgeries = new ArrayList<Surgery>();
		}


		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getSalary() {
			return salary;
		}

		public void setSalary(Integer salary) {
			this.salary = salary;
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

		public String getSpecialization() {
			return specialization;
		}

		public void setSpecialization(String specialization) {
			this.specialization = specialization;
		}

		public Date getHiredate() {
			return hiredate;
		}

		public void setHiredate(Date hiredate) {
			this.hiredate = hiredate;
		}

		public List<Surgery> getSurgery() {
			return surgeries;
		}

		public void setSurgery(List<Surgery> surgery) {
			this.surgeries = surgery;
		}
		public void addSurgery (Surgery surgery) {
			if(!surgeries.contains(surgery)) {
				this.surgeries.add(surgery);
			}
		}
			
		public void removeSurgery (Surgery surgery) {
			if(surgeries.contains(surgery)) {
				this.surgeries.remove(surgery);
				
				}
			}


		@Override
		public String toString() {
			return "Surgeon [id=" + id + ", name=" + name + ", surname=" + surname
					+ ", salary= " + salary+ ", specialization= " + specialization + ", hiredate=" + hiredate + ", surgeries=" + surgeries
					+ "]";
		}
		
		
		
		
}
