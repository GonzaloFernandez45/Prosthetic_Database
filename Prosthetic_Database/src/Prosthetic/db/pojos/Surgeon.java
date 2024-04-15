package Prosthetic.db.pojos;

import java.io.Serializable;
import java.sql.Date;

public class Surgeon implements Serializable {

		private Integer id;
		private Integer salary;
		private String name;
		private String surname;
		private String specialization;
		private Date hiredate;
		
		public Surgeon () {
			super();
			this.surgery = new List<surgery>();
		}
		
		
		
}
