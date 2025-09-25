package ua.student.taxes.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


class IncomeTest {
    
    private MainJobIncome mainJobIncome;
    private AuthorReward authorReward;
    private Gift gift;
    private PropertySale propertySale;
    
    @BeforeEach
    void setUp() {
        // Створюємо тестові об'єкти перед кожним тестом
        mainJobIncome = new MainJobIncome(50000, "Тестова компанія", "Тестер");
        authorReward = new AuthorReward(10000, "Книга", "Видавництво");
        gift = new Gift(60000, "Гроші", "Родичі");
        propertySale = new PropertySale(100000, "Квартира", 5);
    }
    
    @Test
    void testMainJobIncomeTaxCalculation() {
        // Перевіряємо розрахунок податку для основної роботи
        double expectedTax = 50000 * 19.5 / 100.0; 
        assertEquals(expectedTax, mainJobIncome.calculateTax(), 0.01);
        assertEquals(19.5, mainJobIncome.getTaxRate(), 0.01);
    }
    
    @Test
    void testAuthorRewardTaxCalculation() {
        // Перевіряємо розрахунок податку для авторських винагород (з знижкою 20%)
        double taxableAmount = 10000 * 0.8; 
        double expectedTax = taxableAmount * 19.5 / 100.0;
        assertEquals(expectedTax, authorReward.calculateTax(), 0.01);
    }
    
    @Test
    void testGiftTaxCalculation() {
        // Подарунок 60000 грн, з них 50000 - без податку
        double taxableAmount = 60000 - 50000;
        double expectedTax = taxableAmount * 19.5 / 100.0;
        assertEquals(expectedTax, gift.calculateTax(), 0.01);
    }
    
    @Test
    void testGiftBelowLimit() {
        // Подарунок менше 50000 грн не оподатковується
        Gift smallGift = new Gift(40000, "Гроші", "Друзі");
        assertEquals(0.0, smallGift.calculateTax(), 0.01);
        assertEquals(0.0, smallGift.getTaxRate(), 0.01);
    }
    
    @Test
    void testPropertySaleWithBenefit() {
        // Майно у володінні більше 3 років - без податку
        assertEquals(0.0, propertySale.calculateTax(), 0.01);
        assertEquals(0.0, propertySale.getTaxRate(), 0.01);
        assertTrue(propertySale.hasBenefit());
    }
    
    @Test
    void testPropertySaleWithoutBenefit() {
        // Майно у володінні менше 3 років - стандартний податок
        PropertySale shortTermSale = new PropertySale(100000, "Авто", 1);
        double expectedTax = 100000 * 19.5 / 100.0;
        assertEquals(expectedTax, shortTermSale.calculateTax(), 0.01);
        assertFalse(shortTermSale.hasBenefit());
    }
    
    @Test
    void testIncomeValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MainJobIncome(-1000, "Компанія", "Посада");
        });
    }
    
    @Test
    void testIncomeGettersAndSetters() {
        assertEquals("Тестова компанія", mainJobIncome.getCompanyName());
        assertEquals("Тестер", mainJobIncome.getPosition());
        assertEquals(50000, mainJobIncome.getAmount(), 0.01);
        
        mainJobIncome.setAmount(60000);
        assertEquals(60000, mainJobIncome.getAmount(), 0.01);
    }
    
    @Test
    void testToStringMethod() {
        String result = mainJobIncome.toString();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.contains("50000"));
        assertTrue(result.contains("Тестова компанія"));
    }
}