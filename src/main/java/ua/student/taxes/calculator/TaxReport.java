package ua.student.taxes.calculator;

import ua.student.taxes.model.Person;
import ua.student.taxes.model.Tax;
import java.util.List;


public class TaxReport {
    
    private Person person;                  
    private List<Tax> sortedTaxes;         
    private double totalTaxWithBenefits;   
    
 
    public TaxReport(Person person, List<Tax> sortedTaxes, double totalTaxWithBenefits) {
        this.person = person;
        this.sortedTaxes = sortedTaxes;
        this.totalTaxWithBenefits = totalTaxWithBenefits;
    }
    
  
    public void printDetailedReport() {
        System.out.println("ЗВІТ");
        System.out.println("Платник: " + person.getName());
        System.out.println("Податковий номер: " + person.getTaxNumber());
        System.out.println();
        
        System.out.println("ДОХОДИ ТА ПОДАТКИ (за сумою податку)");
        for (int i = 0; i < sortedTaxes.size(); i++) {
            Tax tax = sortedTaxes.get(i);
            System.out.printf("%d. %s%n", (i + 1), tax.toString());
        }
        System.out.println();
        
        System.out.println("ПІЛЬГИ");
        if (person.getBenefits().isEmpty()) {
            System.out.println("Пільги відсутні");
        } else {
            for (int i = 0; i < person.getBenefits().size(); i++) {
                System.out.printf("%d. %s%n", (i + 1), person.getBenefits().get(i).toString());
            }
        }
        System.out.println();
        
        System.out.println("ПІДСУМКИ");
        System.out.printf("Загальний дохід: %.2f грн%n", person.getTotalIncome());
        System.out.printf("Податки до сплати (без пільг): %.2f грн%n", person.getTotalTax());
        System.out.printf("Сума пільг: %.2f грн%n", person.getTotalBenefits());
        System.out.printf("КІНЦЕВА СУМА ДО СПЛАТИ: %.2f грн%n", totalTaxWithBenefits);
        
        double effectiveRate = (person.getTotalIncome() > 0) ? 
            (totalTaxWithBenefits / person.getTotalIncome()) * 100.0 : 0.0;
        System.out.printf("Ефективна податкова ставка: %.2f%%%n", effectiveRate);
        System.out.println("===========================");
    }
    
  
    public void printSummary() {
        System.out.println("КОРОТКИЙ ЗВІТ");
        System.out.printf("Платник: %s | Доходи: %.2f грн | До сплати: %.2f грн%n",
                         person.getName(), person.getTotalIncome(), totalTaxWithBenefits);
    }
    
 
    public Person getPerson() {
        return person;
    }
    
    public List<Tax> getSortedTaxes() {
        return sortedTaxes;
    }
    
    public double getTotalTaxWithBenefits() {
        return totalTaxWithBenefits;
    }
    
    public int getTaxCount() {
        return sortedTaxes.size();
    }
    
    public boolean hasBenefits() {
        return !person.getBenefits().isEmpty();
    }
}
