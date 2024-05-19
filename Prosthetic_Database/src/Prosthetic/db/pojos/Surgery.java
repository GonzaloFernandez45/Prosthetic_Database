package Prosthetic.db.pojos;

import java.util.Objects;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class Surgery implements Serializable {
	
	private static final long serialVersionUID = 6988768427763823667L;
	private String time;
	private Date date;	
	private String result;
	private Integer room;
	private Integer id;
	private Surgeon surgeon;
	private Prosthetic prosthetic;
	
	public Surgery() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Surgery(Integer id,String time,Date date,Integer room,Surgeon surgeon,Prosthetic prosthetic,String result) {
		super();

		this.date = date;
		this.time = time;
		this.result = result;
		this.room = room;
		this.id = id;
		this.surgeon = surgeon;
		this.prosthetic = prosthetic;
	}
	


	

	public Surgery(String time, Date date, Integer room, Surgeon surgeon,Prosthetic prosthetic,String result) {
		super();
		this.time = time;
		this.date = date;
		this.result = result;
		this.room = room;
		this.surgeon = surgeon;
		this.prosthetic=prosthetic;
	}


	public Prosthetic getProsthetic() {
		return prosthetic;
	}


	public void setProsthetic(Prosthetic prosthetic) {
		this.prosthetic = prosthetic;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
		return "Surgery [id= " + id+",time=" + time + ", date=" + date + ", result=" + result + ", room=" + room
				+ ", surgeon=" + surgeon +"]";
	}

	
	
	
	
	
}
