package ua.student.taxes.model;

public abstract class Income {
    
   
    private double amount;          
    private String description;    
    private String source;         
    
  
    public Income(double amount, String description, String source) {
        // Перевіряємо правильність введених даних
        if (amount < 0) {
            throw new IllegalArgumentException("Сума доходу не може бути від'ємною");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Опис доходу не може бути порожнім");
        }
        if (source == null || source.trim().isEmpty()) {
            throw new IllegalArgumentException("Джерело доходу не може бути порожнім");
        }
        
        this.amount = amount;
        this.description = description;
        this.source = source;
    }
    
  
    public abstract double calculateTax();
    

    public abstract double getTaxRate();
    
 
    public double getAmount() {
        return amount;
    }
    

    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сума доходу не може бути від'ємною");
        }
        this.amount = amount;
    }
    

    public String getDescription() {
        return description;
    }
    
 
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Опис доходу не може бути порожнім");
        }
        this.description = description;
    }
    

    public String getSource() {
        return source;
    }
    

    public void setSource(String source) {
        if (source == null || source.trim().isEmpty()) {
            throw new IllegalArgumentException("Джерело доходу не може бути порожнім");
        }
        this.source = source;
    }
    
 
    @Override
    public String toString() {
        return String.format("Дохід: %s | Джерело: %s | Сума: %.2f грн | Ставка податку: %.1f%%", 
                           description, source, amount, getTaxRate());
    }
}
