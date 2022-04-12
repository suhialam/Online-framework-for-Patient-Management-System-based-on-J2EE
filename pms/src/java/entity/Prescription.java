/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author suhail
 */
@ManagedBean(name = "anyPrescription")
@ViewScoped
public class Prescription implements Serializable {

    private static final long serialVersionUID = 1L;

    private int prescriptionId;
    
    @ManagedProperty(value = "#{anyCompany}")
    private Company company;
    
    @ManagedProperty(value = "#{anyMedicine}")
    private Medicine medicine;
    
    @ManagedProperty(value= "#{anyPatient}")
    private Patient patient;

    private int quantity;
    private String date;
    private String dosage;

    public Prescription() {
        company = new Company();
        medicine = new Medicine();
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCurrentDate() {
        return date;
    }

    public void setCurrentDate(String currentDate) {
        
        this.date = currentDate;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

}
