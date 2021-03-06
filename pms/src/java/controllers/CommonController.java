/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Company;
import entity.Medicine;
import entity.Patient;
import entity.Prescription;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import services.CommonService;

/**
 *
 * @author suhail
 */
@ManagedBean(name = "commonController")
@ViewScoped

public class CommonController implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Company> listCompanies;
    CommonService commonService;
    private Company company;

    private Medicine medicine;
    private List<Medicine> listMedicines;
    
    public CommonController() {
        commonService = new CommonService();
        listCompanies = commonService.getListCompanies();
        listMedicines = commonService.getListMedicines();
       
      
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

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public List<Medicine> getListMedicine() {
        return listMedicines;
    }

    public void setListMedicines(List<Medicine> listMedicines) {
        this.listMedicines = listMedicines;
    }
    

    public void setListCompanies(List<Company> listCompanies) {
        this.listCompanies = listCompanies;
    }

    public List<Patient> getListPatient() {
        return commonService.getListPatient();
    }

    
}
