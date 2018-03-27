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
 * @author babu
 */
public class PrescriptionDAO {

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

    public Prescription addToList(String companyId, String MedicineId, String medicineDetailId, int quantity, String dosage) {
        //System.out.println("i am in prescription DAO");
        Prescription temp = new Prescription();

        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        String query = "SELECT c.company_name, m.medicine_name, md.packing from "
                + "pms_schema.companies as c, pms_schema.medicines as m, "
                + "pms_schema.medicine_details as md where c.id=" + companyId
                + " and m.id=" + MedicineId + " and md.id=" + medicineDetailId + ";";
        try {
            ResultSet rs = sql.executeQuery(query);
            rs.next();

            temp.getCompany().setCompanyId(companyId);
            temp.getCompany().setCompanyName(rs.getString("company_name"));

            temp.getMedicine().setMedicineId(Integer.parseInt(MedicineId));
            temp.getMedicine().setMedicineName(rs.getString("medicine_name"));
            temp.getMedicine().setMedicineDetailId(Integer.parseInt(medicineDetailId));
            temp.getMedicine().setPacking(rs.getString("packing"));

            temp.setDosage(dosage);
            temp.setQuantity(quantity);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }

        return temp;
    }

    public void finishPrescription(String patientId, String formatedDate, List<Prescription> listofPrescription) {
        System.out.println("i am in dao in finish prescription");
        System.out.println("patient id = " + patientId);
        System.out.println("prescription = " + formatedDate);

        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        String query = "";
        String medicineDetailId = "";
        int quantity = 0;
        String dosage = "";

        try {
            for (int i = 0; i < listofPrescription.size(); i++) {
                medicineDetailId = listofPrescription.get(i).getMedicine().getMedicineDetailId() + "";
        quantity = listofPrescription.get(i).getQuantity();
        dosage = listofPrescription.get(i).getDosage();
                query = "INSERT INTO pms_schema.patient_history (patient_id, "
                        + "medicine_detail_id, quantity, dosage, prescription_date) "
                        + "values(" + patientId + "," + medicineDetailId + "," + quantity 
                        + ",'" + dosage + "','" + formatedDate + "');";
                sql.executeUpdate(query);
                System.out.println("medicine detail id = " + listofPrescription.get(i).getMedicine().getMedicineDetailId()
                        + "  quantity = " + listofPrescription.get(i).getQuantity() + "  dosage = " + listofPrescription.get(i).getDosage());
            }
            sql.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }

    }

}
