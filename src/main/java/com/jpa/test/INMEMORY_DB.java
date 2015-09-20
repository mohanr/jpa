package com.jpa.test;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INMEMORY_DB",schema="PUBLIC")
public class INMEMORY_DB implements Serializable{

	    private static final long serialVersionUID = 1L;
	 
	    @Id //@GeneratedValue
		@Column(name = "ID")
	    private String id;
	 
		@Column(name = "STREET")
	    private String street;

		@Column(name = "AREA")
		private String area;

		@Column(name = "STATE")
		private String state;

		@Column(name = "COUNTRY")
		private String country;

		@Column(name = "PIN")
		private int pin;
	 
	    public INMEMORY_DB() {
	    }
	 
	 
	    public String getId() {
	        return id;
	    }
	 

	    public String getStreet() {
			return street;
		}


		public void setStreet(String street) {
			this.street = street;
		}



		public String getArea() {
			return area;
		}


		public void setArea(String area) {
			this.area = area;
		}


		public String getState() {
			return state;
		}


		public void setState(String state) {
			this.state = state;
		}


		public String getCountry() {
			return country;
		}


		public void setCountry(String country) {
			this.country = country;
		}


		public int getPin() {
			return pin;
		}


		public void setPin(int pin) {
			this.pin = pin;
		}


		public void setId(String id) {
			this.id = id;
		}


		@Override
		public String toString() {
			return "INMEMORY_DB [id=" + id  + ", street="
					+ street + ",  area="
					+ area + ", state=" + state + ", country=" + country
					+ ", pin=" + pin + "]";
		}
	
}
