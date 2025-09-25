package ua.student.taxes.model;


public class Gift extends Income {
    
    private static final double TAX_RATE = 18.0;           
    private static final double MILITARY_TAX_RATE = 5.0;   
    private static final double TAX_FREE_LIMIT = 50000.0;  // Неоподатковуваний ліміт 50 тис грн
    
    private String giftType;    
    private String fromWhom;    
    

    public Gift(double amount, String giftType, String fromWhom) {
        super(amount, "Подарунок", fromWhom);
        this.giftType = giftType;
        this.fromWhom = fromWhom;
    }
    
    @Override
    public double calculateTax() {
        // Подарунки до 50 тис грн не оподатковуються
        if (getAmount() <= TAX_FREE_LIMIT) {
            return 0.0;
        }
        
        double taxableAmount = getAmount() - TAX_FREE_LIMIT;
        return taxableAmount * (TAX_RATE + MILITARY_TAX_RATE) / 100.0;
    }
    
    @Override
    public double getTaxRate() {
        if (getAmount() <= TAX_FREE_LIMIT) {
            return 0.0;
        }
        return (calculateTax() / getAmount()) * 100.0;
    }
    
    public String getGiftType() {
        return giftType;
    }
    
    public String getFromWhom() {
        return fromWhom;
    }
    
    @Override
    public String toString() {
        return String.format("%s | Тип: %s | Від: %s | Податок: %.2f грн", 
                           super.toString(), giftType, fromWhom, calculateTax());
    }
}