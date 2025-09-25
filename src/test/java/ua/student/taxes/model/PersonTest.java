package ua.student.taxes.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class PersonTest {
    
    private Person person;
    private MainJobIncome mainJob;
    private Gift gift;
    private Benefit childBenefit;
    
    @BeforeEach
    void setUp() {
        person = new Person("Іван Петренко", "1234567890");
        mainJob = new MainJobIncome(50000, "IT Компанія", "Розробник");
        gift = new Gift(30000, "Гроші", "Батьки");
        childBenefit = Benefit.createChildBenefit(2);
    }
    
    @Test
    void testPersonCreation() {
        assertEquals("Іван Петренко", person.getName());
        assertEquals("1234567890", person.getTaxNumber());
        assertTrue(person.getIncomes().isEmpty());
        assertTrue(person.getBenefits().isEmpty());
    }
    
    @Test
    void testPersonValidation() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Person("", "1234567890");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Person("Іван", "");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Person(null, "1234567890");
        });
    }
    
    @Test
    void testAddIncome() {
        person.addIncome(mainJob);
        
        assertEquals(1, person.getIncomes().size());
        assertTrue(person.getIncomes().contains(mainJob));
        
        person.addIncome(null);
        assertEquals(1, person.getIncomes().size()); 
    }
    
    @Test
    void testAddMultipleIncomes() {
        person.addIncome(mainJob);
        person.addIncome(gift);
        
        assertEquals(2, person.getIncomes().size());
        assertTrue(person.getIncomes().contains(mainJob));
        assertTrue(person.getIncomes().contains(gift));
    }
    
    @Test
    void testAddBenefit() {
        person.addBenefit(childBenefit);
        
        assertEquals(1, person.getBenefits().size());
        assertTrue(person.getBenefits().contains(childBenefit));

        person.addBenefit(null);
        assertEquals(1, person.getBenefits().size()); 
    }
    
    @Test
    void testGetTotalIncome() {
        assertEquals(0.0, person.getTotalIncome(), 0.01);
        
        person.addIncome(mainJob);    
        person.addIncome(gift);       
        
        assertEquals(80000.0, person.getTotalIncome(), 0.01);
    }
    
    @Test
    void testGetTotalTax() {
        assertEquals(0.0, person.getTotalTax(), 0.01);
        
        person.addIncome(mainJob);    
        person.addIncome(gift);       
        
        double expectedTax = 9750.0; 
        assertEquals(expectedTax, person.getTotalTax(), 0.01);
    }
    
    @Test
    void testGetTotalBenefits() {
        assertEquals(0.0, person.getTotalBenefits(), 0.01);
        
        person.addBenefit(childBenefit);  
        Benefit materialHelp = Benefit.createMaterialAssistance(5000, "Лікування");
        person.addBenefit(materialHelp);  
        
        assertEquals(25640.0, person.getTotalBenefits(), 0.01);
    }
    
    @Test
    void testGetAllTaxes() {
        person.addIncome(mainJob);
        person.addIncome(gift);
        
        List<Tax> taxes = person.getAllTaxes();
        
        assertEquals(2, taxes.size());
              
        for (Tax tax : taxes) {
            assertNotNull(tax);
            assertNotNull(tax.getIncome());
        }
    }
    
    @Test
    void testGetIncomesReturnsDefensiveCopy() {
        person.addIncome(mainJob);
        List<Income> incomes = person.getIncomes();
        
    
        incomes.clear();   
        
        assertEquals(1, person.getIncomes().size());
    }
    
    @Test
    void testGetBenefitsReturnsDefensiveCopy() {
        person.addBenefit(childBenefit);
        List<Benefit> benefits = person.getBenefits();
        
        
        benefits.clear();
        
        
        assertEquals(1, person.getBenefits().size());
    }
    
    @Test
    void testToString() {
        person.addIncome(mainJob);
        String result = person.toString();
        
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.contains("Іван Петренко"));
        assertTrue(result.contains("1234567890"));
        assertTrue(result.contains("50000"));
    }
    
    @Test
    void testComplexScenario() {
        
        person.addIncome(new MainJobIncome(60000, "Компанія 1", "Менеджер"));
        person.addIncome(new AuthorReward(15000, "Книга", "Видавництво"));
        person.addIncome(new PropertySale(200000, "Будинок", 2)); // Без пільги
        
        person.addBenefit(Benefit.createChildBenefit(3));
        person.addBenefit(Benefit.createMaterialAssistance(8000, "Медицина"));
        
      
        assertEquals(275000.0, person.getTotalIncome(), 0.01);
        assertTrue(person.getTotalTax() > 0);
        assertEquals(38960.0, person.getTotalBenefits(), 0.01); // 3*10320 + 8000
        assertEquals(3, person.getIncomes().size());
        assertEquals(2, person.getBenefits().size());
    }
}