/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Company;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import services.CompaniesService;

/**
 *
 * @author babu
 */

@ManagedBean(name = "companyController")
@ViewScoped
public class CompaniesController implements Serializable {
private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{anyCompany}")
	private Company company;
	private String message;
        
        private String myClass = "";
        
        public CompaniesController() {
		
	}
        
        
	public String getMyClass() {
		return myClass;
	}

	public void setMyClass(String myClass) {
		this.myClass = myClass;
	}

	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String registerCompany() {
		int rowsAffected = 0;

		          CompaniesService companiesService = new CompaniesService();
		rowsAffected = companiesService.registerCompany(company);

		if (rowsAffected > 0) {
			message = "\"" + company.getCompanyName() + "\"" + " registered successfully !";
			myClass = "success-message";
			return message;
		} else {
			message = "\"" + company.getCompanyName() + "\"" + " already exists !";
			myClass = "failure-message";
			
			return message;
		}
	}
}
