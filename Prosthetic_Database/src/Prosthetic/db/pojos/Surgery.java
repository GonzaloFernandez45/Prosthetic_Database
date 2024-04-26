package Prosthetic.db.pojos;

import java.util.Objects;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class Surgery implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6988768427763823667L;
	private String type;
	private String date;	
	private String time;
	private String result;
	private Integer room;
	private Integer id;
	private Surgeon surgeon;
	private Need need;
	private Patient patient;
	
	public Surgery() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Surgery(String type, String date, String time, String result, Integer room, Integer id, Surgeon surgeon,
			Need need, Patient patient) {
		super();
		this.type = type;
		this.date = date;
		this.time = time;
		this.result = result;
		this.room = room;
		this.id = id;
		this.surgeon = surgeon;
		this.need = need;
		this.patient = patient;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Surgeon getSurgeon() {
		return surgeon;
	}

	public void setSurgeon(Surgeon surgeon) {
		this.surgeon = surgeon;
	}

	public Need getNeed() {
		return need;
	}

	public void setNeed(Need need) {
		this.need = need;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
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
		Surgery other = (Surgery) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Surgery [type=" + type + ", date=" + date + ", time=" + time + ", result=" + result + ", room=" + room
				+ ", id=" + id + ", surgeon=" + surgeon + ", need=" + need + ", patient=" + patient + "]";
	}
	
	
	
	
	
}
