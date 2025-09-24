package ua.student.taxes.model;

import java.util.ArrayList;
import java.util.List;


public class Person {
    
    private String name;                    
    private String taxNumber;              
    private List<Income> incomes;          
    private List<Benefit> benefits;        
    
    
    public Person(String name, String taxNumber) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ім'я не може бути порожнім");
        }
        if (taxNumber == null || taxNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Податковий номер не може бути порожнім");
        }
        
        this.name = name;
        this.taxNumber = taxNumber;
        this.incomes = new ArrayList<>();
        this.benefits = new ArrayList<>();
    }
    
    
    public void addIncome(Income income) {
        if (income != null) {
            incomes.add(income);
        }
    }
    
 
    public void addBenefit(Benefit benefit) {
        if (benefit != null) {
            benefits.add(benefit);
        }
    }
    
    
    public double getTotalIncome() {
        double total = 0;
        for (Income income : incomes) {
            total += income.getAmount();
        }
        return total;
    }
    
    
    public double getTotalTax() {
        double total = 0;
        for (Income income : incomes) {
            total += income.calculateTax();
        }
        return total;
    }
  
    public double getTotalBenefits() {
        double total = 0;
        for (Benefit benefit : benefits) {
            total += benefit.getAmount();
        }
        return total;
    }
    
    
    public List<Tax> getAllTaxes() {
        List<Tax> taxes = new ArrayList<>();
        for (Income income : incomes) {
            taxes.add(new Tax(income));
        }
        return taxes;
    }
    
   
    public String getName() {
        return name;
    }
    
    public String getTaxNumber() {
        return taxNumber;
    }
    
    public List<Income> getIncomes() {
        return new ArrayList<>(incomes);  
    }
    
    public List<Benefit> getBenefits() {
        return new ArrayList<>(benefits);  
    }
    
    @Override
    public String toString() {
        return String.format("Платник: %s (№%s) | Доходів: %d | Загальний дохід: %.2f грн | Загальний податок: %.2f грн",
                           name, taxNumber, incomes.size(), getTotalIncome(), getTotalTax());
    }
}
