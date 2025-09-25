package ua.student.taxes.model;


public class Benefit {
    
    private String benefitType;     
    private double amount;          
    private String description;     
    
  
    public Benefit(String benefitType, double amount, String description) {
        if (benefitType == null || benefitType.trim().isEmpty()) {
            throw new IllegalArgumentException("Тип пільги не може бути порожнім");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Сума пільги не може бути від'ємною");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Опис пільги не може бути порожнім");
        }
        
        this.benefitType = benefitType;
        this.amount = amount;
        this.description = description;
    }
    

    public static Benefit createChildBenefit(int numberOfChildren) {
        if (numberOfChildren <= 0) {
            throw new IllegalArgumentException("Кількість дітей має бути більше 0");
        }
        
        
        double benefitAmount = numberOfChildren * 4200.0; 
        String description = String.format("Податкова знижка на %d дит(ину/ей)", numberOfChildren);
        
        return new Benefit("Пільга на дітей", benefitAmount, description);
    }
    

    public static Benefit createMaterialAssistance(double amount, String reason) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сума допомоги має бути більше 0");
        }
        
        String description = "Матеріальна допомога: " + reason;
        return new Benefit("Матеріальна допомога", amount, description);
    }
    
    public String getBenefitType() {
        return benefitType;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setBenefitType(String benefitType) {
        if (benefitType == null || benefitType.trim().isEmpty()) {
            throw new IllegalArgumentException("Тип пільги не може бути порожнім");
        }
        this.benefitType = benefitType;
    }
    
    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сума пільги не може бути від'ємною");
        }
        this.amount = amount;
    }
    
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Опис пільги не може бути порожнім");
        }
        this.description = description;
    }
    
    @Override
    public String toString() {
        return String.format("Пільга: %s | Сума: %.2f грн | %s", 
                           benefitType, amount, description);
    }
}