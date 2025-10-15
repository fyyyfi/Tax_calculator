

package ua.student.taxes.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.student.taxes.model.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StorageService {

    private static final String FILE_PATH = "tax_database.json";
    private final Gson gson;

    public StorageService() {
        RuntimeTypeAdapterFactory<Income> adapterFactory = RuntimeTypeAdapterFactory
                .of(Income.class, "type")
                .registerSubtype(MainJobIncome.class)
                .registerSubtype(AdditionalJobIncome.class)
                .registerSubtype(AuthorReward.class)
                .registerSubtype(Gift.class)
                .registerSubtype(PropertySale.class)
                .registerSubtype(ForeignTransfer.class);

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapterFactory(adapterFactory)
                .create();
    }

 
    public void saveData(TaxDatabase database) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(database, writer);
            System.out.println("Дані успішно збережено у файл: " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Помилка під час збереження даних: " + e.getMessage());
        }
    }

    public TaxDatabase loadData() {
        if (!Files.exists(Paths.get(FILE_PATH))) {
            System.out.println(" Файл бази даних не знайдено. Створено нову порожню базу.");
            return new TaxDatabase();
        }

        try (FileReader reader = new FileReader(FILE_PATH)) {
            TaxDatabase database = gson.fromJson(reader, TaxDatabase.class);
            System.out.println("Дані успішно завантажено з файлу: " + FILE_PATH);
            return database != null ? database : new TaxDatabase();
        } catch (Exception e) {
            System.err.println("Помилка під час читання файлу: " + e.getMessage());
            return new TaxDatabase();
        }
    }
}