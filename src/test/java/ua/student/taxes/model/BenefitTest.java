package ua.student.taxes.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BenefitTest {
    
    @Test
    void testBenefitCreation() {
        Benefit benefit = new Benefit("Тестова пільга", 5000.0, "Опис тестової пільги");
        
        assertEquals("Тестова пільга", benefit.getBenefitType());
        assertEquals(5000.0, benefit.getAmount(), 0.01);
        assertEquals("Опис тестової пільги", benefit.getDescription());
    }
    
    @Test
    void testBenefitValidation() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Benefit("", 5000.0, "Опис");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Benefit(null, 5000.0, "Опис");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Benefit("Пільга", -1000.0, "Опис");
        });
  
        assertThrows(IllegalArgumentException.class, () -> {
            new Benefit("Пільга", 5000.0, "");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Benefit("Пільга", 5000.0, null);
        });
    }
    
    @Test
    void testCreateChildBenefit() {
        Benefit oneChild = Benefit.createChildBenefit(1);
        
        assertEquals("Пільга на дітей", oneChild.getBenefitType());
        assertEquals(10320.0, oneChild.getAmount(), 0.01);
        assertTrue(oneChild.getDescription().contains("1"));
        
        Benefit threeChildren = Benefit.createChildBenefit(3);
        
        assertEquals("Пільга на дітей", threeChildren.getBenefitType());
        assertEquals(30960.0, threeChildren.getAmount(), 0.01); // 3 * 10320
        assertTrue(threeChildren.getDescription().contains("3"));
    }
    
    @Test
    void testCreateChildBenefitValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            Benefit.createChildBenefit(0);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            Benefit.createChildBenefit(-1);
        });
    }
    
    @Test
    void testCreateMaterialAssistance() {
        Benefit assistance = Benefit.createMaterialAssistance(7500.0, "Операція");
        
        assertEquals("Матеріальна допомога", assistance.getBenefitType());
        assertEquals(7500.0, assistance.getAmount(), 0.01);
        assertEquals("Матеріальна допомога: Операція", assistance.getDescription());
    }
    
    @Test
    void testCreateMaterialAssistanceValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            Benefit.createMaterialAssistance(0, "Причина");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            Benefit.createMaterialAssistance(-1000, "Причина");
        });
    }
    
    @Test
    void testSetters() {
        Benefit benefit = new Benefit("Початкова пільга", 1000.0, "Початковий опис");
      
        benefit.setBenefitType("Новий тип");
        assertEquals("Новий тип", benefit.getBenefitType());

        benefit.setAmount(2500.0);
        assertEquals(2500.0, benefit.getAmount(), 0.01);
        
        benefit.setDescription("Новий опис");
        assertEquals("Новий опис", benefit.getDescription());
    }
    
    @Test
    void testSettersValidation() {
        Benefit benefit = new Benefit("Пільга", 1000.0, "Опис");
        
        assertThrows(IllegalArgumentException.class, () -> {
            benefit.setBenefitType("");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            benefit.setAmount(-500);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            benefit.setDescription(null);
        });
    }
    
    @Test
    void testToString() {
        Benefit benefit = new Benefit("Пільга на дітей", 15000.0, "2 дитини");
        String result = benefit.toString();
        
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.contains("Пільга на дітей"));
        assertTrue(result.contains("15000"));
        assertTrue(result.contains("2 дитини"));
    }
    
    @Test
void testFactoryMethodsProduceValidObjects() {
        Benefit childBenefit = Benefit.createChildBenefit(2);
        assertNotNull(childBenefit.getBenefitType());
        assertTrue(childBenefit.getAmount() > 0);
        assertNotNull(childBenefit.getDescription());
        
        Benefit materialHelp = Benefit.createMaterialAssistance(3000, "Лікування");
        assertNotNull(materialHelp.getBenefitType());
        assertTrue(materialHelp.getAmount() > 0);
        assertNotNull(materialHelp.getDescription());
    }
    
    @Test
    void testZeroAmountBenefit() {
        Benefit zeroBenefit = new Benefit("Символічна пільга", 0.0, "Без фінансової вигоди");
        assertEquals(0.0, zeroBenefit.getAmount(), 0.01);
    }
}