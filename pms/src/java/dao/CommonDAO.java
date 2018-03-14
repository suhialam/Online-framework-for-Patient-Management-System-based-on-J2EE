/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Company;
import entity.Medicine;
import entity.Patient;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.SQLQueryUtil;

/**
 *
 * @author babu
 */
public class CommonDAO {

    public List<Company> getListCompanies() {
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        Company company;

        List<Company> listCompanies = new ArrayList<Company>();

        String query = "SELECT * FROM pms_schema.companies ORDER BY id ASC;";
        try {
            ResultSet rs = sql.executeQuery(query);
            while (rs.next()) {
                company = new Company();
                company.setCompanyId(rs.getInt("id"));
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

        String query = "SELECT * FROM pms_schema.medicines where company_id="
                + company.getCompanyId() + ";";
        try {
            ResultSet rs = sql.executeQuery(query);
            while (rs.next()) {
                medicine = new Medicine();
                medicine.setMedicineId(rs.getInt("id"));
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

    public Company findCompany(String companyId) {
        Company selectedCompany = new Company();

        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        String query = "SELECT * FROM pms_schema.companies where id=" + companyId + ";";
        System.out.println(query);
        try {
            ResultSet resultSet = sql.executeQuery(query);
            resultSet.next();

            selectedCompany.setCompanyId(resultSet.getInt("id"));
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

     public Medicine findMedicine(String companyId,String medicineId) {
        Medicine selectedMedcine = new Medicine();
        Company company = new Company();
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

       String query = "SELECT * FROM pms_schema.medicines where company_id="
                + company.getCompanyId() + ";";
         System.out.println(query);
        try {
            ResultSet resultSet = sql.executeQuery(query);
            resultSet.next();

            selectedMedcine.setMedicineDetailId(resultSet.getInt("id"));
           selectedMedcine.setMedicineId(resultSet.getInt("medicine_id"));
           
            selectedMedcine.setCompany(company);
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

        String query = "SELECT * FROM pms_schema.patients ORDER BY id ASC;";

        try {
            ResultSet rs = sql.executeQuery(query);
            while (rs.next()) {
                patient = new Patient();
                patient.setPatientId(rs.getInt("id"));
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

        String query = "SELECT * FROM pms_schema.patients where id=" + patientId + ";";

        try {
            ResultSet rs = sql.executeQuery(query);
            rs.next();
            
            selectedPatient = new Patient();
            selectedPatient.setPatientId(rs.getInt("id"));
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

}
