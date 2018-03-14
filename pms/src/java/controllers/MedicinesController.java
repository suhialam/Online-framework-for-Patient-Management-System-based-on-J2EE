/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Medicine;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import services.CommonService;
import services.MedicineService;

/**
 *
 * @author babu
 */
@ManagedBean(name = "medicineController")
@ViewScoped
public class MedicinesController implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{anyMedicine}")
    private Medicine medicine;
    private String companyId;
    private String medicineId;
    private String packing;
    CommonService commonService;
    MedicineService medicineService;

    private String message;
    private String cssClass = "";

    private List<Medicine> listMedicines;

    public MedicinesController() {
    }

    public List<Medicine> getListMedicines() {
        return listMedicines;
    }

    public void setListMedicines(List<Medicine> listMedicines) {
        this.listMedicines = listMedicines;
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
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public void onCompanyChange() {
        commonService = new CommonService();
        System.out.println(companyId);

        listMedicines = commonService.getListMedicines(companyId);
    }

    public void onMedicineChange() {
        commonService = new CommonService();
        System.out.println(medicineId);
        medicine = commonService.findMedicine(medicineId);
    }

    public void registerMedicine() {
        int rowAffected = 0;

        if (medicine.getMedicineName().trim().equals("")) {
            System.out.println("Empty Data Cannot Be Stored");
            message = "Empty data can not be stored. Please fill the form properly.";
            cssClass = "failure-class";
        } else {
            MedicineService medicineService = new MedicineService();
            rowAffected = medicineService.registerMedicine(medicine, companyId);

            if (rowAffected > 0) {
                message = medicine.getMedicineName() + " registered successfully";
                cssClass = "success-class";

                medicine.setMedicineName("");
            } else {
                message = medicine.getMedicineName() + " already exists";
                cssClass = "failure-class";
            }
        }

    }

    public void updateMedicine() {
        int rowsAffected = 0;
        System.out.println(medicine.getMedicineId());

        if (medicine.getMedicineName().trim().equals("")) {
            System.out.println("Empty data can not be updated");
            message = "Empty data can not be updated. Please fill the form properly.";
            cssClass = "failure-class";
        } else {
            MedicineService medicineService = new MedicineService();
            rowsAffected = medicineService.updateMedicine(medicine);

            if (rowsAffected > 0) {
                message = medicine.getMedicineName() + " Updated Successfully ! ";
                cssClass = "success-class";

                medicine.setMedicineName("");
            } else {
                message = medicine.getMedicineName() + " already exists !";
                cssClass = "failure-class";

            }
        }
    }

    public void addMedicineDetail() {
        int rowAffected = 0;

        if (medicine.getPacking().trim().equals("")) {
            System.out.println("Empty Data Cannot Be Stored");
            message = "Empty data can not be stored. Please fill the form properly.";
            cssClass = "failure-class";
        } else {
            MedicineService medicineService = new MedicineService();
            rowAffected = medicineService.addMedicineDetail(medicine);

            if (rowAffected > 0) {
                message = medicine.getMedicineName() + " registered successfully";
                cssClass = "success-class";

                medicine.setMedicineName("");
            } else {
                message = medicine.getMedicineName() + " already exists";
                cssClass = "failure-class";
            }
        }
    }

}
