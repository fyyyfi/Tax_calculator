package ua.student.taxes.model;


public class Tax {
    
    private Income income;      
    private double amount;      
    private double taxRate;     
    private String taxType;     
    
 
    public Tax(Income income) {
        if (income == null) {
            throw new IllegalArgumentException("Дохід не може бути null");
        }
        
        this.income = income;
        this.amount = income.calculateTax();
        this.taxRate = income.getTaxRate();
        this.taxType = "ПДФО + Військовий збір";
    }
    

    public Tax(Income income, String taxType) {
        this(income);
        if (taxType != null && !taxType.trim().isEmpty()) {
            this.taxType = taxType;
        }
    }
    
    public Income getIncome() {
        return income;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public double getTaxRate() {
        return taxRate;
    }
    
    public String getTaxType() {
        return taxType;
    }
    
 
    public double getIncomeAmount() {
        return income.getAmount();
    }
    
  
    public String getIncomeDescription() {
        return income.getDescription();
    }
    
    @Override
    public String toString() {
        return String.format("Податок: %s | Дохід: %.2f грн | Ставка: %.2f%% | Сума: %.2f грн | Джерело: %s",
                           taxType, getIncomeAmount(), taxRate, amount, income.getSource());
    }
}