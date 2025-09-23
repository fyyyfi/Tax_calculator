package ua.student.taxes.model;



public class AuthorReward extends Income {
    
    private static final double TAX_RATE = 18.0;          
    private static final double MILITARY_TAX_RATE = 1.5;   
    private static final double CREATIVE_DEDUCTION = 20.0; 
    
    private String workType;       
    private String publisher;      
    
  
    public AuthorReward(double amount, String workType, String publisher) {
        super(amount, "Авторська винагорода", publisher);
        this.workType = workType;
        this.publisher = publisher;
    }
    
    @Override
    public double calculateTax() {
       
        double taxableAmount = getAmount() * (100.0 - CREATIVE_DEDUCTION) / 100.0;
        return taxableAmount * (TAX_RATE + MILITARY_TAX_RATE) / 100.0;
    }
    
    @Override
    public double getTaxRate() {
        return (TAX_RATE + MILITARY_TAX_RATE) * (100.0 - CREATIVE_DEDUCTION) / 100.0;
    }
    
    public String getWorkType() {
        return workType;
    }
    
    public String getPublisher() {
        return publisher;
    }
    
    @Override
    public String toString() {
        return String.format("%s | Тип: %s | Видавець: %s | Податок: %.2f грн", 
                           super.toString(), workType, publisher, calculateTax());
    }
}
