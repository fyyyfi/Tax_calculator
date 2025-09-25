package ua.student.taxes.model;


public class ForeignTransfer extends Income {
    
    private static final double TAX_RATE = 18.0;           
    private static final double MILITARY_TAX_RATE = 5.0;   
    
    private String country;     
    private String purpose;     
    

    public ForeignTransfer(double amount, String country, String purpose) {
        super(amount, "Переказ з-за кордону", "Міжнародний переказ з " + country);
        this.country = country;
        this.purpose = purpose;
    }
    
    @Override
    public double calculateTax() {
        
        return getAmount() * (TAX_RATE + MILITARY_TAX_RATE) / 100.0;
    }
    
    @Override
    public double getTaxRate() {
        return TAX_RATE + MILITARY_TAX_RATE;
    }
    
    public String getCountry() {
        return country;
    }
    
    public String getPurpose() {
        return purpose;
    }
    
    @Override
    public String toString() {
        return String.format("%s | З країни: %s | Мета: %s | Податок: %.2f грн", 
                           super.toString(), country, purpose, calculateTax());
    }
}