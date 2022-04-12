/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.CompaniesDAO;
import entity.Company;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.SQLQueryUtil;

/**
 *
 * @author suhail
 */
public class CompaniesService {

    private CompaniesDAO companiesDAO;

    public CompaniesService() {
        companiesDAO = new CompaniesDAO();
    }

    public int registerCompany(Company company) {
        int rowsAffected = 0;
        int count = 0;

        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        String queryCheck = "SELECT count(*) as count from company where company_name like('" + company.getCompanyName() + "');";
        System.out.println(queryCheck);
        try {
            ResultSet resultSet = sql.executeQuery(queryCheck);
            resultSet.next();

            count = resultSet.getInt("count");

            if (count == 0) {
                rowsAffected = companiesDAO.registerCompany(company);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }

        return rowsAffected;

    }

    public int updateCompany(Company company) {
        return companiesDAO.updateCompany(company);
    }
}
