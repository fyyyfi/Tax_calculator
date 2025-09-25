package ua.student.taxes.model;


public class AdditionalJobIncome extends Income {
    
    
    private static final double TAX_RATE = 18.0;           
    private static final double MILITARY_TAX_RATE = 5.0;   
    private static final double TOTAL_TAX_RATE = TAX_RATE + MILITARY_TAX_RATE;
    
    private String companyName;   
    private String workType;        
    private int hoursWorked;       
    
 
    public AdditionalJobIncome(double amount, String companyName, String workType, int hoursWorked) {
        
        super(amount, "Дохід з додаткового місця роботи", companyName);
        
        if (companyName == null || companyName.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва компанії не може бути порожньою");
        }
        if (workType == null || workType.trim().isEmpty()) {
            throw new IllegalArgumentException("Тип роботи не може бути порожнім");
        }
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Кількість годин не може бути від'ємною");
        }
        
        this.companyName = companyName;
        this.workType = workType;
        this.hoursWorked = hoursWorked;
    }
    

    @Override
    public double calculateTax() {
        return getAmount() * TOTAL_TAX_RATE / 100.0;
    }
    
 
    @Override
    public double getTaxRate() {
        return TOTAL_TAX_RATE;
    }
    

    public String getCompanyName() {
        return companyName;
    }
    
 
    public void setCompanyName(String companyName) {
        if (companyName == null || companyName.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва компанії не може бути порожньою");
        }
        this.companyName = companyName;
        setSource(companyName);
    }
    
 
    public String getWorkType() {
        return workType;
    }
    
 
    public void setWorkType(String workType) {
        if (workType == null || workType.trim().isEmpty()) {
            throw new IllegalArgumentException("Тип роботи не може бути порожнім");
        }
        this.workType = workType;
    }
    

    public int getHoursWorked() {
        return hoursWorked;
    }
    
 
    public void setHoursWorked(int hoursWorked) {
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Кількість годин не може бути від'ємною");
        }
        this.hoursWorked = hoursWorked;
    }
    
 
    public double getHourlyRate() {
        if (hoursWorked > 0) {
            return getAmount() / hoursWorked;
        }
        return 0.0;
    }
    
  
    @Override
    public String toString() {
        return String.format("%s | Тип: %s | Годин: %d | Податок: %.2f грн", 
                           super.toString(), workType, hoursWorked, calculateTax());
    }
}