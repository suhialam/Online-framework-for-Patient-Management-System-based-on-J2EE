/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.CommonDAO;
import entity.Company;
import entity.Medicine;
import entity.Patient;
import entity.Prescription;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author babu
 */
public class CommonService implements Serializable {
private static final long serialVersionUID = 1L;
    private CommonDAO commonDAO;

    public CommonService() {
        commonDAO = new CommonDAO();
    }

    public List<Company> getListCompanies() {
        return commonDAO.getListCompanies();
    }

    public List<Medicine> getListMedicines() {
        return commonDAO.getListMedicines();
    }
    
    public List<Medicine> getListMedicines(String companyId) {
        return commonDAO.getListMedicines(companyId);
    }

    public Company findCompany(String companyId) {
        return commonDAO.findCompany(companyId);
    }
    public Medicine findMedicine(String medicineId){
        return commonDAO.findMedicine(medicineId);
    }
    
    public List<Patient> getListPatient() {
        return commonDAO.getListPatient();

    }
     public Patient findPatient(String patientId) {
         return commonDAO.findPatient(patientId);
     }
     public List<Prescription> getListPrescription(Patient patient){
        return commonDAO.getListPrescription(patient); 
     }
        
}
