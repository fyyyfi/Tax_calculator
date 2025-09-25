package ua.student.taxes.calculator;

import ua.student.taxes.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


class TaxCalculatorTest {
    
    private TaxCalculator calculator;
    private Person person;
    
    @BeforeEach
    void setUp() {
        calculator = new TaxCalculator();
        person = new Person("Тест Тестович", "1234567890");
        
        // Додаємо тестові доходи з різними сумами податків
        person.addIncome(new MainJobIncome(50000, "Компанія А", "Програміст")); // 9750 грн податку
        person.addIncome(new AuthorReward(10000, "Стаття", "Видавництво"));     // 1560 грн податку (з знижкою)
        person.addIncome(new Gift(60000, "Гроші", "Родичі"));                   // 1950 грн податку (50к неоподатковується)
        person.addIncome(new PropertySale(100000, "Квартира", 5));              // 0 грн податку (пільга)
    }
    
    @Test
    void testCalculateTaxes() {
        List<Tax> taxes = calculator.calculateTaxes(person);
        
        assertEquals(4, taxes.size());
        
        // Перевіряємо, що всі податки створені
        for (Tax tax : taxes) {
            assertNotNull(tax);
            assertNotNull(tax.getIncome());
            assertTrue(tax.getAmount() >= 0);
        }
    }
    
    @Test
    void testCalculateTotalTaxWithBenefits() {
        // Без пільг
        double totalTaxWithoutBenefits = calculator.calculateTotalTaxWithBenefits(person);
        double expectedTax = person.getTotalTax(); // 13260 грн
        assertEquals(expectedTax, totalTaxWithoutBenefits, 0.01);
        
        //з пільгами
        person.addBenefit(Benefit.createChildBenefit(2));           // 20640 грн
        person.addBenefit(Benefit.createMaterialAssistance(5000, "Лікування")); // 5000 грн
        
        double totalTaxWithBenefits = calculator.calculateTotalTaxWithBenefits(person);
  
        assertEquals(0.0, totalTaxWithBenefits, 0.01);
    }
    
    @Test
    void testSortTaxesByAmount() {
        List<Tax> taxes = calculator.calculateTaxes(person);
        List<Tax> sortedTaxes = calculator.sortTaxesByAmount(taxes);
        
        assertEquals(taxes.size(), sortedTaxes.size());
        
        // Перевіряємо сортування (від більшого до меншого)
        for (int i = 0; i < sortedTaxes.size() - 1; i++) {
            assertTrue(sortedTaxes.get(i).getAmount() >= sortedTaxes.get(i + 1).getAmount());
        }
        
        // Перевіряємо порядок: основна робота (9750), подарунок (1950), авторська (1560), продаж (0)
        assertEquals("Заробітна плата з основного місця роботи", 
                    sortedTaxes.get(0).getIncomeDescription());
        assertEquals("Продаж майна", 
                    sortedTaxes.get(3).getIncomeDescription());
    }
    
    @Test
    void testSortTaxesWithNullList() {
        List<Tax> result = calculator.sortTaxesByAmount(null);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    
    @Test
    void testFindLargestTax() {
        List<Tax> taxes = calculator.calculateTaxes(person);
        Tax largest = calculator.findLargestTax(taxes);
        
        assertNotNull(largest);
        assertEquals("Заробітна плата з основного місця роботи", 
                    largest.getIncomeDescription());
        assertEquals(9750.0, largest.getAmount(), 0.01);
    }
    
    @Test
    void testFindSmallestTax() {
        List<Tax> taxes = calculator.calculateTaxes(person);
        Tax smallest = calculator.findSmallestTax(taxes);
        
        // Найменший податок > 0 - це авторська винагорода (1560 грн)
        // Продаж майна має 0 грн, тому не рахується
        assertNotNull(smallest);
        assertEquals("Авторська винагорода", smallest.getIncomeDescription());
        assertEquals(1560.0, smallest.getAmount(), 0.01);
    }
    
    @Test
    void testFindTaxesWithEmptyList() {
        assertNull(calculator.findLargestTax(null));
        assertNull(calculator.findSmallestTax(null));
        
        List<Tax> emptyList = List.of();
        assertNull(calculator.findLargestTax(emptyList));
        assertNull(calculator.findSmallestTax(emptyList));
    }
    
    @Test
    void testCalculateAverageTaxRate() {
        double averageRate = calculator.calculateAverageTaxRate(person);
        
        // Очікуваний розрахунок: (податки / доходи) * 100
        double expectedRate = (person.getTotalTax() / person.getTotalIncome()) * 100.0;
        assertEquals(expectedRate, averageRate, 0.01);
    }
    
    @Test
    void testCalculateAverageTaxRateWithNullPerson() {
        assertEquals(0.0, calculator.calculateAverageTaxRate(null));
    }
    
    @Test
    void testGenerateReport() {
        TaxReport report = calculator.generateReport(person);
        
        assertNotNull(report);
        assertEquals(person, report.getPerson());
        assertEquals(4, report.getTaxCount());
        assertFalse(report.hasBenefits()); // Поки що пільг немає
        
        // Додаємо пільги та перевіряємо знову
        person.addBenefit(Benefit.createChildBenefit(1));
        TaxReport reportWithBenefits = calculator.generateReport(person);
        assertTrue(reportWithBenefits.hasBenefits());
    }
    
    @Test
    void testGenerateReportWithNullPerson() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.generateReport(null);
        });
    }
}
