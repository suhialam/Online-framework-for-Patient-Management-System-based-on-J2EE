/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Medicine;
import entity.Prescription;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.swing.JOptionPane;
import services.CommonService;

/**
 *
 * @author babu
 */
@ManagedBean(name = "prescriptionController")
@ViewScoped
public class PrescriptionController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private List<Medicine> listofPacking;
    private List<Prescription> listofPrescription;
    
    @ManagedProperty(value= "#{anyPrescription}")
    Prescription prescription;
     

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public List<Medicine> getListofPacking() {
        return listofPacking;
    }

    public void setListofPacking(List<Medicine> listofPacking) {
        this.listofPacking = listofPacking;
    }
    
    
    
    
    public void onMedicineChange() {
        System.out.println(prescription.getPatient().getPatientId());
        System.out.println(prescription.getPatient().getPatientName());
        CommonService commonService = new CommonService();
        listofPacking = commonService.findPacking(prescription);
    }
    
}
