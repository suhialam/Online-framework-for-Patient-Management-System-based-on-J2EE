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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import services.CommonService;
import services.PrescriptionService;

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
    private String message;

    private Date date;
    
    @ManagedProperty(value = "#{anyPrescription}")
    Prescription prescription;
    
    private List<Prescription> listPrescription;
    private PrescriptionService prescriptionService;
    private Company company;
    private Patient patient;
    private Medicine medicine;
    
    private String companyId;
    private String MedicineId;
    private String medicineDetailId;
    private String patientId;
    
    private int quantity;
    private String dosage;

    
    public PrescriptionController() {
        commonService = new CommonService();
        date = new Date(System.currentTimeMillis());
        
        listofPrescription = new ArrayList<Prescription>();
        
        
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    
    public CommonService getCommonService() {
        return commonService;
    }

    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    public PrescriptionService getPrescriptionService() {
        return prescriptionService;
    }

    public void setPrescriptionService(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getMedicineId() {
        return MedicineId;
    }

    public void setMedicineId(String MedicineId) {
        this.MedicineId = MedicineId;
    }

    public String getMedicineDetailId() {
        return medicineDetailId;
    }

    public void setMedicineDetailId(String medicineDetailId) {
        this.medicineDetailId = medicineDetailId;
    }
    

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public void addToList() {
       //new code
        /*System.out.println("company id " + companyId);
        System.out.println("medicine id " + MedicineId);
        System.out.println("medicine detail id " + medicineDetailId);
        
        System.out.println("quantity " + quantity);
        System.out.println("dosage " + dosage);*/
        
        prescriptionService = new PrescriptionService();
                
        Prescription newPrescription = prescriptionService.addToList(companyId, MedicineId, medicineDetailId, quantity, dosage);
        
        listofPrescription.add(newPrescription);
    }

    public void onCompanyChange() {
        commonService = new CommonService();

        listMedicines = commonService.getListMedicines(companyId);
        
    }

    public void onMedicineChange() {
        listofPacking = commonService.findPacking(MedicineId);
        
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    public static final int DEFAULT_ROWS;
    public static final int DEFAULT_LIST_SIZE;

    static {
        DEFAULT_ROWS = 8;
        DEFAULT_LIST_SIZE = 30;

    }


    public List<Prescription> getListPrescription(Patient patient) {
        return prescriptionService.getListPrescription(patient);
    }

    public List<Prescription> getListPrescription() {
        return listPrescription;
    }

    public void setListPrescription(List<Prescription> listPrescription) {
        this.listPrescription = listPrescription;
    }
    
    public void finishPrescription() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
	System.out.println(dateFormat.format(date));
        String formatedDate = dateFormat.format(date).toString();
	System.out.println(patientId); 
        
        prescriptionService = new PrescriptionService();
        prescriptionService.finishPrescription(patientId, formatedDate, listofPrescription);
        listofPrescription = null;
        message = "Prescription Saved Successfully";
    }
    
    public void findPatientHistory() {
        System.out.println(patientId);
        listPrescription = commonService.findPatientHistory(patientId);
    }
}
