package ua.student.taxes.model;


public class MainJobIncome extends Income {
    
   
    private static final double TAX_RATE = 18.0;           
    private static final double MILITARY_TAX_RATE = 5.0;   
    private static final double TOTAL_TAX_RATE = TAX_RATE + MILITARY_TAX_RATE;
    
    private String companyName;    
    private String position;       
    

    public MainJobIncome(double amount, String companyName, String position) {
        
        super(amount, "Заробітна плата з основного місця роботи", companyName);
        
        
        if (companyName == null || companyName.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва компанії не може бути порожньою");
        }
        if (position == null || position.trim().isEmpty()) {
            throw new IllegalArgumentException("Посада не може бути порожньою");
        }
        
        this.companyName = companyName;
        this.position = position;
    }
    
    // Розрахунок податку з основної заробітної плаВключає ПДФО 18% + військовий збір 1.5%
 
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
    
  
    public String getPosition() {
        return position;
    }
    
  
    public void setPosition(String position) {
        if (position == null || position.trim().isEmpty()) {
            throw new IllegalArgumentException("Посада не може бути порожньою");
        }
        this.position = position;
    }
    
   
    @Override
    public String toString() {
        return String.format("%s | Посада: %s | Податок: %.2f грн", 
                           super.toString(), position, calculateTax());
    }
}
