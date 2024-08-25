package com.example.model;

import jakarta.persistence.Column;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empid;

	@JsonProperty("emp_name")
	@Column(name = "emp_name")
	private String empname;

	@JsonProperty("emp_salary")
	@Column(name = "emp_salary")
	private Float empsalary;

	@JsonProperty("emp_age")
	@Column(name = "emp_age")
	private int empage;

	@JsonProperty("emp_city")
	@Column(name = "emp_city")
	private String empcity;

	public Long getEmpid() {
		return empid;
	}

	public void setEmpid(Long empid) {
		this.empid = empid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public Float getEmpsalary() {
		return empsalary;
	}

	public void setEmpsalary(Float empsalary) {
		this.empsalary = empsalary;
	}

	public int getEmpage() {
		return empage;
	}

	public void setEmpage(int empage) {
		this.empage = empage;
	}

	public String getEmpcity() {
		return empcity;
	}

	public void setEmpcity(String empcity) {
		this.empcity = empcity;
	}

	public Employee(Long empid, String empname, Float empsalary, int empage, String empcity) {
		this.empid = empid;
		this.empname = empname;
		this.empsalary = empsalary;
		this.empage = empage;
		this.empcity = empcity;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", empname=" + empname + ", empsalary=" + empsalary + ", empage=" + empage
				+ ", empcity=" + empcity + "]";
	}

	public Employee() {

	}
}
