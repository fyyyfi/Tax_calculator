package ua.student.taxes.console;

import ua.student.taxes.model.*;
import ua.student.taxes.calculator.TaxCalculator;
import ua.student.taxes.calculator.TaxReport;
import java.util.Scanner;

public class ConsoleApp {
    
    private Scanner scanner;
    private TaxCalculator calculator;
    private Person currentPerson;

    public ConsoleApp() {
        scanner = new Scanner(System.in);
        calculator = new TaxCalculator();
    }

    public void start() {
        System.out.println("Система розрахунку податків");
        System.out.println();
        
 
        createPerson();
        

        showMainMenu();
    }
    

    private void createPerson() {
        System.out.println("Введіть дані платника податків:");
        System.out.print("Ім'я: ");
        String name = scanner.nextLine();
        
        System.out.print("Податковий номер: ");
        String taxNumber = scanner.nextLine();
        
        currentPerson = new Person(name, taxNumber);
        System.out.println("Платника " + name + " створено успішно!");
        System.out.println();
    }
    

    private void showMainMenu() {
        boolean running = true;
        
        while (running) {
            System.out.println("ГОЛОВНЕ МЕНЮ");
            System.out.println("1. Додати дохід");
            System.out.println("2. Додати пільгу");
            System.out.println("3. Розрахувати податки");
            System.out.println("4. Показати звіт");
            System.out.println("5. Вийти");
            System.out.print("Оберіть опцію (1-5): ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    addIncomeMenu();
                    break;
                case "2":
                    addBenefitMenu();
                    break;
                case "3":
                    calculateTaxes();
                    break;
                case "4":
                    showReport();
                    break;
                case "5":
                    running = false;
                    System.out.println("До побачення!");
                    break;
                default:
                    System.out.println("Оберіть 1-5. Спробуйте знову.");
            }
            System.out.println();
        }
    }
    
 
    private void addIncomeMenu() {
        System.out.println("ДОДАВАННЯ ДОХОДУ");
        System.out.println("1. Основна робота");
        System.out.println("2. Додаткова робота");
        System.out.println("3. Авторська винагорода");
        System.out.println("4. Продаж майна");
        System.out.println("5. Подарунок");
        System.out.println("6. Переказ з-за кордону");
        System.out.print("Оберіть тип доходу: ");
        
        String choice = scanner.nextLine();
        
        switch (choice) {
            case "1":
                addMainJobIncome();
                break;
            case "2":
                addAdditionalJobIncome();
                break;
            case "3":
                addAuthorReward();
                break;
            case "4":
                addPropertySale();
                break;
            case "5":
                addGift();
                break;
            case "6":
                addForeignTransfer();
                break;
            default:
                System.out.println("Оберіть 1-6.");
        }
    }
    
    private void addMainJobIncome() {
        System.out.print("Сума заробітної плати: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Назва компанії: ");
        String company = scanner.nextLine();
        System.out.print("Посада: ");
        String position = scanner.nextLine();
        
        MainJobIncome income = new MainJobIncome(amount, company, position);
        currentPerson.addIncome(income);
        System.out.println("Дохід додано!");
    }
    
    private void addAdditionalJobIncome() {
        System.out.print("Сума доходу: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Назва компанії: ");
        String company = scanner.nextLine();
        System.out.print("Тип роботи: ");
        String workType = scanner.nextLine();
        System.out.print("Кількість годин: ");
        int hours = Integer.parseInt(scanner.nextLine());
        
        AdditionalJobIncome income = new AdditionalJobIncome(amount, company, workType, hours);
        currentPerson.addIncome(income);
        System.out.println("Дохід додано!");
    }
    
    private void addAuthorReward() {
        System.out.print("Сума винагороди: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Тип твору: ");
        String workType = scanner.nextLine();
        System.out.print("Видавець: ");
        String publisher = scanner.nextLine();
        
        AuthorReward income = new AuthorReward(amount, workType, publisher);
        currentPerson.addIncome(income);
        System.out.println("Дохід додано!");
    }
    
    private void addPropertySale() {
        System.out.print("Сума від продажу: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Тип майна: ");
        String propertyType = scanner.nextLine();
        System.out.print("Кількість років володіння: ");
        int years = Integer.parseInt(scanner.nextLine());
        
        PropertySale income = new PropertySale(amount, propertyType, years);
        currentPerson.addIncome(income);
        System.out.println("Дохід додано!");
    }
    
    private void addGift() {
        System.out.print("Сума подарунка: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Тип подарунка: ");
        String giftType = scanner.nextLine();
        System.out.print("Від кого: ");
        String fromWhom = scanner.nextLine();
        
        Gift income = new Gift(amount, giftType, fromWhom);
        currentPerson.addIncome(income);
        System.out.println("Дохід додано!");
    }
    
    private void addForeignTransfer() {
        System.out.print("Сума переказу: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Країна: ");
        String country = scanner.nextLine();
        System.out.print("Призначення: ");
        String purpose = scanner.nextLine();
        
        ForeignTransfer income = new ForeignTransfer(amount, country, purpose);
        currentPerson.addIncome(income);
        System.out.println("Дохід додано!");
    }
    

    private void addBenefitMenu() {
        System.out.println("ДОДАВАННЯ ПІЛЬГИ");
        System.out.println("1. Пільга на дітей");
        System.out.println("2. Матеріальна допомога");
        System.out.print("Оберіть тип пільги: ");
        
        String choice = scanner.nextLine();
        
        switch (choice) {
            case "1":
                System.out.print("Кількість дітей: ");
                int children = Integer.parseInt(scanner.nextLine());
                Benefit childBenefit = Benefit.createChildBenefit(children);
                currentPerson.addBenefit(childBenefit);
                System.out.println("Пільгу додано!");
                break;
            case "2":
                System.out.print("Сума допомоги: ");
                double amount = Double.parseDouble(scanner.nextLine());
                System.out.print("Причина: ");
                String reason = scanner.nextLine();
                Benefit assistance = Benefit.createMaterialAssistance(amount, reason);
                currentPerson.addBenefit(assistance);
                System.out.println("Пільгу додано!");
                break;
            default:
                System.out.println("Невірний вибір.");
        }
    }
    

    private void calculateTaxes() {
        if (currentPerson.getIncomes().isEmpty()) {
            System.out.println("Спочатку додайте доходи!");
            return;
        }
        
        System.out.println("РОЗРАХУНОК ПОДАТКІВ");
        System.out.printf("Загальний дохід: %.2f грн%n", currentPerson.getTotalIncome());
        System.out.printf("Податки до сплати: %.2f грн%n", currentPerson.getTotalTax());
        System.out.printf("Пільги: %.2f грн%n", currentPerson.getTotalBenefits());
        
        double finalTax = calculator.calculateTotalTaxWithBenefits(currentPerson);
        System.out.printf("КІНЦЕВА СУМА: %.2f грн%n", finalTax);
    }
    
    private void showReport() {
        if (currentPerson.getIncomes().isEmpty()) {
            System.out.println("Спочатку додайте доходи!");
            return;
        }
        
        TaxReport report = calculator.generateReport(currentPerson);
        report.printDetailedReport();
    }
}