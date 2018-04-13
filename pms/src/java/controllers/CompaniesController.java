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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import services.CommonService;
import services.CompaniesService;



import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import java.io.File;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


/**
 *
 * @author babu
 */
@ManagedBean(name = "companyController")
@ViewScoped
public class CompaniesController implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{anyCompany}")
    private Company company;
    private String message;

    private String cssClass = "";

    private String companyId;

    private CommonService commonService;

    public CompaniesController() {

    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void registerCompany() {
        int rowsAffected = 0;

        if (company.getCompanyName().trim().equals("")
                || company.getAddress().trim().equals("")
                || company.getPhoneNumber().trim().equals("")) {
            System.out.println("empty data can not be stored.");
            message = "Empty data can not be stored. Please fill the form properly.";
            cssClass = "failure-class";
        } else {
            System.out.println("else condition");
            CompaniesService companiesService = new CompaniesService();
            rowsAffected = companiesService.registerCompany(company);

            if (rowsAffected > 0) {
                message = company.getCompanyName() + " registered successfully !";
                cssClass = "success-class";

                company.setCompanyName("");
                company.setAddress("");
                company.setPhoneNumber("");

            } else {
                message = company.getCompanyName() + " already exists !";
                cssClass = "failure-class";
            }

        }

    }

    public void onCompanyChange() {
        commonService = new CommonService();

        company = commonService.findCompany(companyId);


    }

    public void updateCompany() {
        int rowsAffected = 0;
                System.out.println(company.getCompanyId());
        System.out.println(company.getCompanyName());
        System.out.println(company.getAddress());
        System.out.println(company.getPhoneNumber());

        if (company.getCompanyName().trim().equals("")
                || company.getAddress().trim().equals("")
                || company.getPhoneNumber().trim().equals("")) {
            System.out.println("empty data can not be updated.");
            message = "Empty data can not be updated. Please fill the form properly.";
            cssClass = "failure-class";
        } else {
            
            CompaniesService companiesService = new CompaniesService();
            rowsAffected = companiesService.updateCompany(company);

            if (rowsAffected > 0) {
                message = company.getCompanyName() + " Updated successfully !";
                cssClass = "success-class";

                company.setCompanyName("");
                company.setAddress("");
                company.setPhoneNumber("");

            } else {
                message = company.getCompanyName() + " already exists !";
                cssClass = "failure-class";
            }

        }
        
    }
    
    
    /**
     * pdf of all companies report generation code starts here
     */
    
    public void printAllCompanies() {
        
         commonService = new CommonService();
        List<Company> listCompanies = commonService.getListCompanies();
        //System.out.println("list size = " + listCompanies.size());
        
         
        
        try {
            
            System.out.println("ok till here 1");
                        
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./abcdd.pdf"));
            //PdfDocument pdfDocument = new PdfDocument(new PdfWriter("../reports/first-invoince.pdf"));
            Document layoutDocument = new Document(pdfDocument);
System.out.println("ok till here 1....111");
        // title
        layoutDocument.add(new Paragraph("ALL COMPANIES").setBold().setUnderline().setTextAlignment(TextAlignment.CENTER));

        // customer reference information
        layoutDocument.add(new Paragraph("DR NAJM-UD-DIN").setTextAlignment(
                TextAlignment.LEFT).setMultipliedLeading(0.2f));
        
        layoutDocument.add(new Paragraph("PESHAWAR").setMultipliedLeading(.2f));
        layoutDocument.add(new Paragraph("tel: 1234567890").setMultipliedLeading(.2f));
        
        //create items to add into pdf
       
        
        //create a table to display items into tabular form
        Table table = new Table(UnitValue.createPointArray(new float[]{60f, 180f, 50f, 80f}));
        // headers
        table.addCell(new Paragraph("S.N.O").setBold());
        table.addCell(new Paragraph("COMPANY NAME").setBold());
        table.addCell(new Paragraph("ADDRESS").setBold());
        table.addCell(new Paragraph("PHONE NUMBER").setBold());
        //table.addCell(new Paragraph("AMOUNT IN RS.").setBold());

        //now add items into the table
        for (Company c : listCompanies) {
            table.addCell(new Paragraph(c.getCompanyId() + ""));
            table.addCell(new Paragraph(c.getCompanyName()));
            table.addCell(new Paragraph(c.getAddress()));
            table.addCell(new Paragraph(c.getPhoneNumber()));
            //table.addCell(new Paragraph((item.quantity * item.unitPrice) + ""));
        }
        System.out.println("ok till here 2");
        // add table to pdf
        layoutDocument.add(table);
        System.out.println("ok till here 3");
        // close the document
        layoutDocument.close();
        } catch(Throwable th) {
            th.printStackTrace();
        }
        System.out.println("ok till here 4");
    }

}
