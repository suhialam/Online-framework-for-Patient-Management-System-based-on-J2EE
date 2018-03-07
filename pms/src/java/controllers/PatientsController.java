/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Patient;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import services.PatientService;

/**
 *
 * @author babu
 */
@ManagedBean( name = "patientBean")
@ViewScoped
public class PatientsController implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{anyPatient}")
    private Patient patient;
    
    private String message = "";
    private String cssClass;

    

    public PatientsController() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public void registerPatient() {
        int rowsAffected = 0;
        if(patient.getPatientName().trim().equals("") ||
                patient.getAddress().trim().equals("") || 
                patient.getPhoneNumber().trim().equals("")) {
            message = "Empty data can not be stored. Please fill the form properly.";
            cssClass = "failure-class";
        } else {
            PatientService patientService = new PatientService();
            rowsAffected = patientService.registerPatient(patient);
            
            if(rowsAffected > 0) {
                message = patient.getPatientName() + " Patient Register Successfully !";
                cssClass = "success-class";
                
                patient.setPatientName("");
                patient.setAddress("");
                patient.setPhoneNumber("");
            } else {
                message = patient.getPatientName() + " Already exist !";
                cssClass = "failure-class";
                
            }
        }
    }

}
