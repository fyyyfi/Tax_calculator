package ua.student.taxes;

import ua.student.taxes.model.*;
import ua.student.taxes.calculator.TaxCalculator;
import ua.student.taxes.calculator.TaxReport;


public class TaxSystemDemo {
    
    public static void main(String[] args) {
        System.out.println(" СИСТЕМА РОЗРАХУНКУ ПОДАТКІВ ");
        System.out.println();
        
   
        demonstrateSystem();
    }
    
 static void demonstrateSystem() {
        
        Person person = new Person("Іванов Іван Іванович", "1234567890");
        System.out.println("Створено платника: " + person.toString());
        System.out.println();
        
        addSampleIncomes(person);
        
        addSampleBenefits(person);
        
        TaxCalculator calculator = new TaxCalculator();
        
        showBasicInfo(person, calculator);
        
        TaxReport report = calculator.generateReport(person);
        report.printDetailedReport();
 
        demonstrateSorting(calculator, person);
    }
    
 
    private static void addSampleIncomes(Person person) {
        System.out.println("ДОДАВАННЯ ДОХОДІВ");
        
  
        MainJobIncome mainJob = new MainJobIncome(50000, "ТОВ \"Софт\"", "Програміст");
        person.addIncome(mainJob);
        System.out.println("Додано: " + mainJob.toString());
 
        AdditionalJobIncome additionalJob = new AdditionalJobIncome(15000, "ФОП Петренко", "Консультації", 40);
        person.addIncome(additionalJob);
        System.out.println("Додано: " + additionalJob.toString());
        
 
        AuthorReward authorReward = new AuthorReward(8000, "Стаття", "Видавництво \"Техніка\"");
        person.addIncome(authorReward);
        System.out.println("Додано: " + authorReward.toString());
 
        PropertySale propertySale = new PropertySale(100000, "Квартира", 5);
        person.addIncome(propertySale);
        System.out.println("Додано: " + propertySale.toString());
 
        Gift gift = new Gift(75000, "Гроші", "Родичі");
        person.addIncome(gift);
        System.out.println("Додано: " + gift.toString());
        
        ForeignTransfer transfer = new ForeignTransfer(20000, "Польща", "Фріланс");
        person.addIncome(transfer);
        System.out.println("Додано: " + transfer.toString());
        
        System.out.println();
    }
    

    private static void addSampleBenefits(Person person) {
        System.out.println("ДОДАВАННЯ ПІЛЬГ");
        
        Benefit childBenefit = Benefit.createChildBenefit(2);
        person.addBenefit(childBenefit);
        System.out.println("Додано: " + childBenefit.toString());
        
  
        Benefit materialAssistance = Benefit.createMaterialAssistance(5000, "Лікування");
        person.addBenefit(materialAssistance);
        System.out.println("Додано: " + materialAssistance.toString());
        
        System.out.println();
    }
 
    private static void showBasicInfo(Person person, TaxCalculator calculator) {
        System.out.println("БАЗОВА ІНФОРМАЦІЯ");
        System.out.printf("Кількість доходів: %d%n", person.getIncomes().size());
        System.out.printf("Загальна сума доходів: %.2f грн%n", person.getTotalIncome());
        System.out.printf("Загальна сума податків: %.2f грн%n", person.getTotalTax());
        System.out.printf("Загальна сума пільг: %.2f грн%n", person.getTotalBenefits());
        System.out.printf("До сплати (з урахуванням пільг): %.2f грн%n", 
                         calculator.calculateTotalTaxWithBenefits(person));
        System.out.printf("Середня ставка: %.2f%%%n", calculator.calculateAverageTaxRate(person));
        System.out.println();
    }

    private static void demonstrateSorting(TaxCalculator calculator, Person person) {
        System.out.println("СОРТУВАННЯ");
        
        var taxes = calculator.calculateTaxes(person);
        var sortedTaxes = calculator.sortTaxesByAmount(taxes);
        
        System.out.println("Податки відсортовані за сумою (від більшого до меншого):");
        for (int i = 0; i < sortedTaxes.size(); i++) {
            Tax tax = sortedTaxes.get(i);
            System.out.printf("%d. %.2f грн - %s%n", 
                             (i + 1), tax.getAmount(), tax.getIncomeDescription());
        }
        
        Tax largest = calculator.findLargestTax(taxes);
        Tax smallest = calculator.findSmallestTax(taxes);
        
        if (largest != null) {
            System.out.printf("%nНайбільший податок: %.2f грн (%s)%n", 
                             largest.getAmount(), largest.getIncomeDescription());
        }
        
        if (smallest != null) {
            System.out.printf("Найменший податок: %.2f грн (%s)%n", 
                             smallest.getAmount(), smallest.getIncomeDescription());
        }
        
        System.out.println();
        System.out.println(" ДЕМОНСТРАЦІЯ ЗАВЕРШЕНА");
    }
}
