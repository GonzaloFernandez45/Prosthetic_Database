package Prosthetic.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Surgeon implements Serializable {

		private Integer id;
		private Integer salary;
		private String name;
		private String surname;
		private String specialization;
		private Date hiredate;
		private List<Surgery> surgery;
		
		public Surgeon () {
			super();
			this.surgery = new ArrayList<Surgery>();
			
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
			return surgery;
		}

		public void setSurgery(List<Surgery> surgery) {
			this.surgery = surgery;
		}
		
}
