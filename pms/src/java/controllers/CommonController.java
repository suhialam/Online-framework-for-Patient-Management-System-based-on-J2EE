/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Company;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import services.CommonService;

/**
 *
 * @author babu
 */
@ManagedBean(name = "commonController")
@ViewScoped

public class CommonController implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Company> listCompanies;
    
    CommonService commonService;

    public CommonController() {
        commonService = new CommonService();
        
    }

    public List<Company> getListCompanies() {
        listCompanies = commonService.getListCompanies();
        
        
        return listCompanies;
    }

    public void setListCompanies(List<Company> listCompanies) {
        this.listCompanies = listCompanies;
    }


}
