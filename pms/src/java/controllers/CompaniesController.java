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

    private String cssClass = "";

    public CompaniesController() {

    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
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

        if (company.getCompanyName().trim().equals("")
                || company.getAddress().trim().equals("")
                || company.getPhoneNumber().trim().equals("")) {
            System.out.println("if condition");
            message = "Empty data can not be stored. Please fill the form properly.";
            cssClass = "failure-class";
        } else {
            System.out.println("else condition");
            CompaniesService companiesService = new CompaniesService();
            rowsAffected = companiesService.registerCompany(company);

            if (rowsAffected > 0) {
                message = company.getCompanyName() + " registered successfully !";
                cssClass = "success-class";
                 
    //return "/page.xhtml?faces-redirect=true";
            } else {
                message = company.getCompanyName() + " already exists !";
                cssClass = "failure-class";
            }
        }

        return message;

    }

}
