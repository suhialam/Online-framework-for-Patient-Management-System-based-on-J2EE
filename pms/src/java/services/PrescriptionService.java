/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.CommonDAO;
import dao.PrescriptionDAO;
import entity.Patient;
import entity.Prescription;
import java.util.List;

/**
 *
 * @author babu
 */
public class PrescriptionService {

    
    private PrescriptionDAO prescriptionDAO;
    
    public PrescriptionService() {
        prescriptionDAO = new PrescriptionDAO();
    }
    

    public List<Prescription> getListPrescription(Patient patient) {
        return prescriptionDAO.getListPrescription(patient);
    }
    
    public Prescription addToList(String companyId, String MedicineId, String medicineDetailId, int quantity, String dosage) {
        System.out.println("i am in prescription service");
        return prescriptionDAO.addToList(companyId, MedicineId, medicineDetailId, quantity, dosage);
    }

}
