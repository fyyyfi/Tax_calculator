package ua.student.taxes.console;


public class ConsoleApp {
    
    public void start() {
        System.out.println("Консольний додаток запущено!");
        System.out.println("Функціонал буде додано пізніше...");
        
        
        demonstrateBasicFunctionality();
    }
    
    /**
     * Демонстрація базового функціоналу
     */
    private void demonstrateBasicFunctionality() {
        System.out.println("\nДемонстрація роботи з доходами ");
        System.out.println("Система готова до роботи з різними типами доходів:");
        System.out.println("1. Основна робота");
        System.out.println("2. Додаткова робота"); 
        System.out.println("3. Авторські винагороди");
        System.out.println("4. Продаж майна");
        System.out.println("5. Подарунки");
        System.out.println("6. Перекази з-за кордону");
        System.out.println("");
    }
}
