/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Company;
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

}
