package com.rock.controller;

public class Details {
	  private String name;
	  private String passportNumber;
	  
	  public Details() {
	    super();
	  }

	  public Details(String name, String passportNumber) {
	    super();
	    this.name = name;
	    this.passportNumber = passportNumber;
	  }
	  public String getName() {
	    return name;
	  }
	  public void setName(String name) {
	    this.name = name;
	  }
	  public String getPassportNumber() {
	    return passportNumber;
	  }
	  public void setPassportNumber(String passportNumber) {
	    this.passportNumber = passportNumber;
	  }

	@Override
	public String toString() {
		return "Details [name=" + name + ", passportNumber=" + passportNumber + "]";
	}
	    
	  
	}