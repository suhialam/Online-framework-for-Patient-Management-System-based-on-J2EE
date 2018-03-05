/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Company;

/**
 *
 * @author babu
 */
public class CompaniesService {
   
    public boolean registerCompany(Company company) {
        System.out.println(company.getCompanyName());
        System.out.println(company.getAddress());
        System.out.println(company.getPhoneNumber());
        return false;
    }
}
