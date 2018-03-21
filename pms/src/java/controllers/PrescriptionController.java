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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import services.CommonService;
import services.PrescriptionService;
import util.SQLQueryUtil;

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

    @ManagedProperty(value = "#{anyPrescription}")
    Prescription prescription;
    
    private List<Prescription> listPrescription;
    private PrescriptionService prescriptionService;
    private Company company;
    private Patient patient;
    private Medicine medicine;
    private String patientId;
    

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
        
        System.out.println("hello world");
        //System.out.println(date);
        //CommonService commonService = new CommonService();
        //prescription = commonService.setPrescriptionObject(prescription);
        //listofPrescription.add(prescription);
    }

    public void onCompanyChange() {
        commonService = new CommonService();

        listMedicines = commonService.getListMedicines(prescription.getCompany().getCompanyId());
    }

    public void onMedicineChange() {
        System.out.println(prescription.getPatient().getPatientId());
        System.out.println(prescription.getPatient().getPatientName());
        CommonService commonService = new CommonService();
        listofPacking = commonService.findPacking(prescription);
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

    public void prescriptionTable() {
        SQLQueryUtil sql = new SQLQueryUtil();

        sql.connect(false);

        listPrescription = new ArrayList<Prescription>();

        String query = "SELECT * FROM pms_schema.companies AS c, pms_schema.medicines AS m,"
                + " pms_schema.medicine_details AS md, pms_schema.patient_history AS ph"
                + " WHERE c.id=m.company_id AND m.id=md.medicine_id AND md.id=ph.medicine_detail_id"
                + " AND ph.patient_id=" + patientId + " ORDER by patient_id ASC;";
        try {

            ResultSet rs = sql.executeQuery(query);

            prescription = new Prescription();
            company = new Company();
            medicine = new Medicine();
            patient = new Patient();

            while (rs.next()) {
                prescription = new Prescription();

                company = new Company();

                medicine = new Medicine();

                company.setCompanyId(rs.getString("id"));
                company.setCompanyName(rs.getString("company_name"));
                medicine.setCompany(company);

                medicine.setMedicineId(rs.getInt("id"));
                medicine.setMedicineDetailId(rs.getInt("id"));
                medicine.setPacking(rs.getString("packing"));
                medicine.setMedicineName(rs.getString("medicine_name"));

                prescription.setPatient(patient);
                prescription.setCompany(company);
                prescription.setMedicine(medicine);

                prescription.setQuantity(rs.getInt("quantity"));
                prescription.setDosage(rs.getString("dosage"));
                prescription.setCurrentDate(rs.getDate("prescription_date"));

                listPrescription.add(prescription);

                System.out.println(query);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }
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

}
