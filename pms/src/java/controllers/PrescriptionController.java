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
    
    private CommonService commonService;
    private List<Medicine> listMedicines;
    
    @ManagedProperty(value= "#{anyPrescription}")
    Prescription prescription;

    public PrescriptionController() {
       commonService = new CommonService();
    }
    
    

                        /*some extra work here*/

                        private String date;

                        public String getDate() {
                            return date;
                        }

                        public void setDate(String date) {
                            this.date = date;
                        }

                        /*end here*/

    public List<Medicine> getListMedicines() {
        return listMedicines;
    }

    public void setListMedicines(List<Medicine> listMedicines) {
        this.listMedicines = listMedicines;
    }
    
                        
                        
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

    public List<Prescription> getListofPrescription() {
        return listofPrescription;
    }

    public void setListofPrescription(List<Prescription> listofPrescription) {
        this.listofPrescription = listofPrescription;
    }
    
    public void setPrescriptionObject() {
        System.out.println(date);
        CommonService commonService = new CommonService();
        prescription = commonService.setPrescriptionObject(prescription);
        //listofPrescription.add(prescription);
    }
    
     public void onCompanyChange() {
        commonService = new CommonService();
       

        listMedicines = commonService.getListMedicines(prescription.getMedicine().getMedicineId());
    }
     
    public void onMedicineChange() {
        System.out.println(prescription.getPatient().getPatientId());
        System.out.println(prescription.getPatient().getPatientName());
        CommonService commonService = new CommonService();
        listofPacking = commonService.findPacking(prescription);
    }
    
}
