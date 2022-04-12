/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Company;
import entity.Medicine;
import entity.Patient;
import entity.Prescription;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.SQLQueryUtil;

/**
 *
 * @author suhail
 */
public class CommonDAO {
    
public static void main(String[] args) {
    
       CommonDAO com = new CommonDAO();
        //String ab=(com.findPacking(medicineId));
        System.out.println();
    }
    public List<Company> getListCompanies() {
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        Company company;

        List<Company> listCompanies = new ArrayList<Company>();

        String query = "select * from company ORDER BY company_id ASC;";
        try {
            ResultSet rs = sql.executeQuery(query);
            while (rs.next()) {
                company = new Company();
                company.setCompanyId(rs.getInt("company_id"));
                company.setCompanyName(rs.getString("company_name"));
                company.setAddress(rs.getString("address"));
                company.setPhoneNumber(rs.getString("phone_number"));

                listCompanies.add(company);
                
   
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
           
            sql.disconnect();
        }

        return listCompanies;
    }

    
    public List<Medicine> getListMedicines() {
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        Medicine medicine;
        Company company = new Company();
        List<Medicine> listMedicines = new ArrayList<Medicine>();

        String query = "SELECT * FROM medicines where company_id="
                + company.getCompanyId() + ";";
        try {
            ResultSet rs = sql.executeQuery(query);
            while (rs.next()) {
                medicine = new Medicine();
                medicine.setMedicineId(rs.getInt("medicine_id"));
                medicine.setMedicineName(rs.getString("medicine_name"));

                medicine.setCompany(company);
                listMedicines.add(medicine);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }

        return listMedicines;
    }

    public List<Medicine> getListMedicines(String companyId) {
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        Medicine medicine;

        List<Medicine> listMedicines = new ArrayList<Medicine>();

        String query = "SELECT * FROM medicines where company_id="
                + companyId + ";";
        try {
            ResultSet rs = sql.executeQuery(query);
            while (rs.next()) {
                medicine = new Medicine();
                medicine.setMedicineId(rs.getInt("medicine_id"));
                medicine.setMedicineName(rs.getString("medicine_name"));

                //medicine.setCompany(company);
                listMedicines.add(medicine);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }

        return listMedicines;
    }

    public Company findCompany(String companyId) {
        Company selectedCompany = new Company();

        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        String query = "SELECT * FROM company where company_id=" + companyId + ";";
        System.out.println(query);
        try {
            ResultSet resultSet = sql.executeQuery(query);
            resultSet.next();

            selectedCompany.setCompanyId(resultSet.getInt("company_id"));
            selectedCompany.setCompanyName(resultSet.getString("company_name"));
            selectedCompany.setAddress(resultSet.getString("address"));
            selectedCompany.setPhoneNumber(resultSet.getString("phone_number"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }
        return selectedCompany;
    }

    public Medicine findMedicine(String medicineId) {
        Medicine selectedMedcine = new Medicine();
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        String query = "SELECT * FROM medicines where medicine_id="
                + medicineId + ";";
        System.out.println(query);
        try {
            ResultSet resultSet = sql.executeQuery(query);
            resultSet.next();

            selectedMedcine.setMedicineId(resultSet.getInt("medicine_id"));
            selectedMedcine.setMedicineName(resultSet.getString("medicine_name"));

            System.out.println(selectedMedcine);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }
        return selectedMedcine;
    }
    
    public List<Patient> getListPatient() {
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        List<Patient> listPatient = new ArrayList<Patient>();
        Patient patient;

        String query = "SELECT * FROM patients ORDER BY patient_id ASC;";

        try {
            ResultSet rs = sql.executeQuery(query);
            while (rs.next()) {
                patient = new Patient();
                patient.setPatientId(rs.getString("patient_id"));
                patient.setPatientName(rs.getString("patient_name"));
                patient.setAddress(rs.getString("address"));
                patient.setPhoneNumber(rs.getString("phone_number"));

                listPatient.add(patient);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }
        return listPatient;

    }

    
    public Patient findPatient(String patientId) {
        Patient selectedPatient = new Patient();

        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        String query = "SELECT * FROM patients where patient_id=" + patientId + ";";

        try {
            ResultSet rs = sql.executeQuery(query);
            rs.next();

            selectedPatient = new Patient();
            selectedPatient.setPatientId(rs.getString("patient_id"));
            selectedPatient.setPatientName(rs.getString("patient_name"));
            selectedPatient.setAddress(rs.getString("address"));
            selectedPatient.setPhoneNumber(rs.getString("phone_number"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }
        return selectedPatient;

    }

    public List<Prescription> getListPrescription(Patient patient) {

        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        List<Prescription> listpPrescription = new ArrayList<Prescription>();

        Prescription prescription = null;
        Company company = null;
        Medicine medicine = null;

        String query = "SELECT * FROM pms_schema.companies AS c, pms_schema.medicines AS m,"
                + " pms_schema.medicine_details AS md, pms_schema.patient_history AS ph"
                + " WHERE c.id=m.company_id AND m.id=md.medicine_id AND md.id=ph.medicine_detail_id"
                + " AND ph.patient_id=" + patient.getPatientId() + " ORDER by patient_id ASC;";

        try {
            ResultSet rs = sql.executeQuery(query);
            System.out.println(rs);
            while (rs.next()) {
                prescription = new Prescription();

                company = new Company();

                medicine = new Medicine();

                company.setCompanyId(rs.getInt("company_id"));
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
                prescription.setCurrentDate(rs.getString("prescription_date"));

                listpPrescription.add(prescription);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }

        return listpPrescription;
    }

    public List<Medicine> findPacking(String medicineId) {
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        List<Medicine> listofPakcing = new ArrayList<Medicine>();
        Medicine medicine;

        String query = "select * from medicine_detail where "
                + "medicine_id=" + medicineId + ";";

        try {
            ResultSet rs = sql.executeQuery(query);
            while (rs.next()) {
                medicine = new Medicine();
                medicine.setMedicineDetailId(rs.getInt("medicine_detail_id"));
                medicine.setPacking(rs.getString("packing"));

                listofPakcing.add(medicine);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }
        return listofPakcing;
    }

    public Prescription setPrescriptionObject(Prescription prescription) {
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        return null;
    }

    public List<Prescription> findPatientHistory(String patientId) {
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        List<Prescription> listPrescription = new ArrayList<Prescription>();

        Prescription prescription = null;
        Company company = null;

        Medicine medicine = null;

        String query = "select * from company as c,medicines as m, "
                + " medicine_detail as md, " 
                +  "patient_history as ph " 
                + "where c.company_id=m.company_id and m.medicine_id=md.medicine_id " 
                + "and md.medicine_detail_id=ph.medicine_detail_id and ph.patient_id='" + patientId
                + "' order by prescription_date desc;";

        try {
            ResultSet resultSet = sql.executeQuery(query);

            while (resultSet.next()) {
                prescription = new Prescription();
                company = new Company();
                medicine = new Medicine();

                
                company.setCompanyName(resultSet.getString("company_name"));

                medicine.setCompany(company);
                
                
                medicine.setPacking(resultSet.getString("packing"));
                medicine.setMedicineName(resultSet.getString("medicine_name"));

                
                prescription.setCompany(company);
                prescription.setMedicine(medicine);

                prescription.setQuantity(resultSet.getInt("quantity"));
                prescription.setDosage(resultSet.getString("dosage"));
                prescription.setCurrentDate(resultSet.getString("prescription_date"));

                listPrescription.add(prescription);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }

        return listPrescription;
    }

}
