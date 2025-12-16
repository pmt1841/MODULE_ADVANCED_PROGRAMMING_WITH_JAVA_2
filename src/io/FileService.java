package io;

import model.GenuinePhone;
import model.ImportedPhone;
import model.Phone;

import java.io.*;
import java.util.HashMap;

public class FileService implements FileInterface {
    @Override
    public void writeToFile(HashMap<Integer, Phone> phoneList, String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for(HashMap.Entry<Integer, Phone> entry : phoneList.entrySet()) {
                bw.write(entry.getValue().toCSV());
            }

        } catch (IOException e) {
            System.out.println("Lỗi ghi file");
        }
    }

    @Override
    public HashMap<Integer, Phone> readFromFile(String path, boolean isImported) {
        HashMap<Integer, Phone> phoneList = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double price = Double.parseDouble(data[2]);
                int quantity = Integer.parseInt(data[3]);
                String brand = data[4];

                if (isImported) {
                    String origin = data[5];
                    String status = data[6];
                    Phone importedPhone = new ImportedPhone(id, name, price, quantity, brand, origin, status);
                    phoneList.put(importedPhone.getId(), importedPhone);
                } else {
                    int warrantyPeriod = Integer.parseInt(data[5]);
                    String warrantyScope = data[6];
                    Phone genuinePhone = new GenuinePhone(id, name, price, quantity, brand, warrantyScope, warrantyPeriod);
                    phoneList.put(genuinePhone.getId(), genuinePhone);
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi đọc file");
        }
        return phoneList;
    }
}
