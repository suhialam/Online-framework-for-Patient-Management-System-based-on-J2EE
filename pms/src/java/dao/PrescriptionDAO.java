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
                prescription.setCurrentDate(rs.getDate("prescription_date"));

                listpPrescription.add(prescription);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }

        return listpPrescription;
    }

}
