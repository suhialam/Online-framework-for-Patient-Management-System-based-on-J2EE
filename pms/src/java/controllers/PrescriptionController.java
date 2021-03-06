/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import entity.Company;
import entity.Medicine;
import entity.Patient;
import entity.Prescription;
import java.awt.Desktop;
import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import services.CommonService;
import services.PrescriptionService;

/**
 *
 * @author suhail
 */
@ManagedBean(name = "prescriptionController")
@ViewScoped
public class PrescriptionController implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Medicine> listofPacking;
    private List<Prescription> listofPrescription;

    private CommonService commonService;
    private List<Medicine> listMedicines;
    private String message;

    private Date date;
    
    @ManagedProperty(value = "#{anyPrescription}")
    Prescription prescription;
    
    private List<Prescription> listPrescription;
    private PrescriptionService prescriptionService;
    private Company company;
    private Patient patient;
    private Medicine medicine;
    
    private String companyId;
    private String MedicineId;
    private String medicineDetailId;
    private String patientId;
    
    private int quantity;
    private String dosage;

    
    public PrescriptionController() {
        commonService = new CommonService();
        date = new Date(System.currentTimeMillis());
        
        listofPrescription = new ArrayList<Prescription>();
        
        
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    
    public CommonService getCommonService() {
        return commonService;
    }

    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    public PrescriptionService getPrescriptionService() {
        return prescriptionService;
    }

    public void setPrescriptionService(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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
        return MedicineId;
    }

    public void setMedicineId(String MedicineId) {
        this.MedicineId = MedicineId;
    }

    public String getMedicineDetailId() {
        return medicineDetailId;
    }

    public void setMedicineDetailId(String medicineDetailId) {
        this.medicineDetailId = medicineDetailId;
    }
    

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /*end here*/
    public List<Medicine> getListMedicines() {
        return listMedicines;
    }

    public void setListMedicines(List<Medicine> listMedicines) {
        this.listMedicines = listMedicines;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public List<Medicine> getListofPacking() {
        return listofPacking;
    }

    public void setListofPacking(List<Medicine> listofPacking) {
        this.listofPacking = listofPacking;
    }

    public List<Prescription> getListofPrescription() {
        return listofPrescription;
    }

    public void setListofPrescription(List<Prescription> listofPrescription) {
        this.listofPrescription = listofPrescription;
    }

    public void addToList() {
       //new code
        /*System.out.println("company id " + companyId);
        System.out.println("medicine id " + MedicineId);
        System.out.println("medicine detail id " + medicineDetailId);
        
        System.out.println("quantity " + quantity);
        System.out.println("dosage " + dosage);*/
        
        prescriptionService = new PrescriptionService();
                
        Prescription newPrescription = prescriptionService.addToList(companyId, MedicineId, medicineDetailId, quantity, dosage);
        
        listofPrescription.add(newPrescription);
        
        
    }

    public void onCompanyChange() {
        commonService = new CommonService();

        listMedicines = commonService.getListMedicines(companyId);
        
    }

    public void onMedicineChange() {
        listofPacking = commonService.findPacking(MedicineId);
        
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    public static final int DEFAULT_ROWS;
    public static final int DEFAULT_LIST_SIZE;

    static {
        DEFAULT_ROWS = 8;
        DEFAULT_LIST_SIZE = 30;

    }


    public List<Prescription> getListPrescription(Patient patient) {
        return prescriptionService.getListPrescription(patient);
    }

    public List<Prescription> getListPrescription() {
        return listPrescription;
    }

    public void setListPrescription(List<Prescription> listPrescription) {
        this.listPrescription = listPrescription;
    }
    
    public void finishPrescription() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
	System.out.println(dateFormat.format(date));
        String formatedDate = dateFormat.format(date).toString();
	System.out.println(patientId); 
        
        prescriptionService = new PrescriptionService();
        prescriptionService.finishPrescription(patientId, formatedDate, listofPrescription);
        //listofPrescription = null;
        message = "Prescription Saved Successfully";
    }
    
    public void findPatientHistory() {
        System.out.println(patientId);
        listPrescription = commonService.findPatientHistory(patientId);
    }
   
    
     public void printPrescription() {
         System.out.println("Print Prescription");
      // prescriptionService = new PrescriptionService();
      
        
        try {
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter("../../reports/second-invoince.pdf"));
            Document layoutDocument = new Document(pdfDocument);
            
            // title
            layoutDocument.add(new Paragraph("Prescription").setBold().setUnderline().setTextAlignment(TextAlignment.CENTER));

            // customer reference information
            layoutDocument.add(new Paragraph("DR NAJM-UD-DIN").setTextAlignment(
                    TextAlignment.LEFT).setMultipliedLeading(0.2f));
            
             layoutDocument.add(new Paragraph("Patient Name :").setTextAlignment(
                    TextAlignment.RIGHT).setMultipliedLeading(0.2f));
             
              layoutDocument.add(new Paragraph("Ali Khan").setTextAlignment(
                    TextAlignment.RIGHT).setMultipliedLeading(0.2f));

            layoutDocument.add(new Paragraph("PESHAWAR").setMultipliedLeading(.2f));
            layoutDocument.add(new Paragraph("tel: 12345678900").setMultipliedLeading(.2f));

            

            //create items to add into pdf
            //create a table to display items into tabular form
            Table table = new Table(UnitValue.createPointArray(new float[]{60f, 180f, 180f, 80f}));
            // headers
            table.addCell(new Paragraph("S.N.O").setBold());
            table.addCell(new Paragraph("COMPANY NAME").setBold());
            table.addCell(new Paragraph("Medicine Name").setBold());
            //table.addCell(new Paragraph("Packing").setBold());
            //table.addCell(new Paragraph("Quantity").setBold());
            table.addCell(new Paragraph("Dosage").setBold());
            //table.addCell(new Paragraph("AMOUNT IN RS.").setBold());

            //now add items into the table
            for (Prescription p :listofPrescription) {
                table.addCell(new Paragraph(p.getCompany().getCompanyId()+ ""));
                table.addCell(new Paragraph(p.getCompany().getCompanyName()));
                table.addCell(new Paragraph(p.getMedicine().getMedicineName()));
                table.addCell(new Paragraph(p.getDosage()));
                //table.addCell(new Paragraph((item.quantity * item.unitPrice) + ""));
            }
            
            // add table to pdf
            layoutDocument.add(table);
            
            // close the document
            layoutDocument.close();

            /**
             * to open it in browser
             */
            //FacesContext.getCurrentInstance (). getExternalContext (). redirect("../../reports/first-invoince.pdf");
            File pdfFile = new File("../../reports/second-invoince.pdf");
		if (pdfFile.exists()) {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(pdfFile);
                                System.out.println("File has been opened successfully.");
			} else {
				System.out.println("Awt Desktop is not supported!");
			}

		} else {
			System.out.println("File is not exists!");
		}
        } catch (Throwable th) {
            th.printStackTrace();
        }
        
    }
}
