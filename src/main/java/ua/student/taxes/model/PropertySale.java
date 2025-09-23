package ua.student.taxes.model;


public class PropertySale extends Income {
    
    private static final double TAX_RATE = 18.0;           
    private static final double MILITARY_TAX_RATE = 1.5;   
    private static final int MIN_OWNERSHIP_YEARS = 3;      
    
    private String propertyType;        
    private int ownershipYears;        
    
  
    public PropertySale(double amount, String propertyType, int ownershipYears) {
        super(amount, "Продаж майна", "Продаж " + propertyType);
        this.propertyType = propertyType;
        this.ownershipYears = ownershipYears;
    }
    
    @Override
    public double calculateTax() {
        // Якщо майном володіли більше 3 років - пільга
        if (ownershipYears >= MIN_OWNERSHIP_YEARS) {
            return 0.0;
        }
        
        // Інакше стандартне оподаткування
        return getAmount() * (TAX_RATE + MILITARY_TAX_RATE) / 100.0;
    }
    
    @Override
    public double getTaxRate() {
        if (ownershipYears >= MIN_OWNERSHIP_YEARS) {
            return 0.0;
        }
        return TAX_RATE + MILITARY_TAX_RATE;
    }
    
    public String getPropertyType() {
        return propertyType;
    }
    
    public int getOwnershipYears() {
        return ownershipYears;
    }
    
    public boolean hasBenefit() {
        return ownershipYears >= MIN_OWNERSHIP_YEARS;
    }
    
    @Override
    public String toString() {
        String benefitInfo = hasBenefit() ? " (пільга)" : "";
        return String.format("%s | Тип: %s | Років: %d%s | Податок: %.2f грн", 
                           super.toString(), propertyType, ownershipYears, benefitInfo, calculateTax());
    }
}
