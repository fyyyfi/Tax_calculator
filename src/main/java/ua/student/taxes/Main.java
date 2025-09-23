package ua.student.taxes;

import ua.student.taxes.console.ConsoleApp;


public class Main {
    
    /**
     * Точка входу в програму
     * 
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        System.out.println("=== Система розрахунку податків фізичної особи ===");
        System.out.println();
        
        // Створюємо та запускаємо консольний додаток
        ConsoleApp app = new ConsoleApp();
        app.start();
    }
}