/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author babu
 */
@ManagedBean(name = "anyMedicine")
@ViewScoped
public class Medicine implements Serializable{

    private static final long serialVersionUID = 1L;
    
    
    private Company company;
    
    private int medicineId;
    private int medicineDetailId;
    
    private String medicineName;
    private String Packing;

    public Medicine() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getMedicineDetailId() {
        return medicineDetailId;
    }

    public void setMedicineDetailId(int medicineDetailId) {
        this.medicineDetailId = medicineDetailId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getPacking() {
        return Packing;
    }

    public void setPacking(String Packing) {
        this.Packing = Packing;
    }

    
    
}
