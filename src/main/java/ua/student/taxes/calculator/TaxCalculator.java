package ua.student.taxes.calculator;

import ua.student.taxes.model.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class TaxCalculator {
    
  
    public List<Tax> calculateTaxes(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Особа не може бути пуста");
        }
        
        List<Tax> taxes = new ArrayList<>();
        
   
        for (Income income : person.getIncomes()) {
            Tax tax = new Tax(income);
            taxes.add(tax);
        }
        
        return taxes;
    }
    
    public double calculateTotalTaxWithBenefits(Person person) {
        if (person == null) {
            return 0.0;
        }
        
        double totalTax = person.getTotalTax();
        double totalBenefits = person.getTotalBenefits();
        
      
        double finalTax = Math.max(0, totalTax - totalBenefits);
        
        return finalTax;
    }
    
  
    public List<Tax> sortTaxesByAmount(List<Tax> taxes) {
        if (taxes == null) {
            return new ArrayList<>();
        }
        
        List<Tax> sortedTaxes = new ArrayList<>(taxes);
        
      
        Collections.sort(sortedTaxes, new Comparator<Tax>() {
            @Override
            public int compare(Tax tax1, Tax tax2) {
                if (tax1.getAmount() > tax2.getAmount()) {
                    return -1; 
                } else if (tax1.getAmount() < tax2.getAmount()) {
                    return 1;   
                } else {
                    return 0;   
                }
            }
        });
        
        return sortedTaxes;
    }
    
   
    public TaxReport generateReport(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Особа не може бути пуста");
        }
        
        List<Tax> taxes = calculateTaxes(person);
        List<Tax> sortedTaxes = sortTaxesByAmount(taxes);
        double totalTaxWithBenefits = calculateTotalTaxWithBenefits(person);
        
        return new TaxReport(person, sortedTaxes, totalTaxWithBenefits);
    }
    
 
    public Tax findLargestTax(List<Tax> taxes) {
        if (taxes == null || taxes.isEmpty()) {
            return null;
        }
        
        Tax largest = taxes.get(0);
        for (Tax tax : taxes) {
            if (tax.getAmount() > largest.getAmount()) {
                largest = tax;
            }
        }
        
        return largest;
    }
    
 
    public Tax findSmallestTax(List<Tax> taxes) {
        if (taxes == null || taxes.isEmpty()) {
            return null;
        }
        
        Tax smallest = null;
        for (Tax tax : taxes) {
            if (tax.getAmount() > 0) {
                if (smallest == null || tax.getAmount() < smallest.getAmount()) {
                    smallest = tax;
                }
            }
        }
        
        return smallest;
    }
    

    public double calculateAverageTaxRate(Person person) {
        if (person == null || person.getTotalIncome() == 0) {
            return 0.0;
        }
        
        return (person.getTotalTax() / person.getTotalIncome()) * 100.0;
    }
}

