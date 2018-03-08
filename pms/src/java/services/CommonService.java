/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.CommonDAO;
import entity.Company;
import entity.Medicine;
import java.util.List;

/**
 *
 * @author babu
 */
public class CommonService {

    private CommonDAO commonDAO;

    public CommonService() {
        commonDAO = new CommonDAO();

    }

    public List<Company> getListCompanies() {
        return commonDAO.getListCompanies();
    }
    
    
  
       public List<Medicine> getListMedicines(){
        return commonDAO.getListMedicines();
    }
    
}
