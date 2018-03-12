/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Company;
import entity.Medicine;
import entity.Patient;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import services.CommonService;

/**
 *
 * @author babu
 */
@ManagedBean(name = "commonController")
@ViewScoped

public class CommonController implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Company> listCompanies;
    CommonService commonService;
    private Company company;

    public CommonController() {
        commonService = new CommonService();
        listCompanies = commonService.getListCompanies();

    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Company> getListCompanies() {

        return listCompanies;
    }

    public List<Medicine> getListMedicine() {
        return commonService.getListMedicines();
    }

    public void setListCompanies(List<Company> listCompanies) {
        this.listCompanies = listCompanies;
    }

    public List<Patient> getListPatient() {
        return commonService.getListPatient();
    }

}
