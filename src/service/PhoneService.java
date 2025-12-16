package service;

import io.FileInterface;
import io.FileService;
import model.Phone;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PhoneService implements PhoneServiceInterface {
    private final String IMPORT_FILE_PATH = "src/data/importedPhones.csv";
    private final String GENUINE_FILE_PATH = "src/data/genuinePhones.csv";

    private final FileInterface fileService = new FileService();

    @Override
    public void addPhone(HashMap<Integer, Phone> phoneList, boolean isImported) {
        if (isImported) {
            fileService.writeToFile(phoneList, IMPORT_FILE_PATH);
        } else {
            fileService.writeToFile(phoneList, GENUINE_FILE_PATH);
        }
    }

    @Override
    public HashMap<Integer, Phone> listPhone() {
        HashMap<Integer, Phone> phoneList = new HashMap<>();
        phoneList.putAll(fileService.readFromFile(GENUINE_FILE_PATH, false));
        phoneList.putAll(fileService.readFromFile(IMPORT_FILE_PATH, true));
        return phoneList;
    }

    @Override
    public HashMap<Integer, Phone> searchPhone(String name) {
        HashMap<Integer, Phone> phoneList = listPhone();
        return (HashMap<Integer, Phone>) phoneList.entrySet().stream().filter(entry -> entry.getValue().getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue
        ));
    }

    @Override
    public Phone searchPhone(int id) {
        HashMap<Integer, Phone> phoneList = listPhone();
        return phoneList.get(id);
    }

    @Override
    public void removePhone(Phone phone) {
        HashMap<Integer, Phone> genuinePhoneList = fileService.readFromFile(GENUINE_FILE_PATH, false);
        HashMap<Integer, Phone> importedPhoneList = fileService.readFromFile(IMPORT_FILE_PATH, true);
        for (HashMap.Entry<Integer, Phone> entry : genuinePhoneList.entrySet()) {
            if (entry.getValue().equals(phone)) {
                genuinePhoneList.remove(entry.getKey());
                break;
            }
            fileService.writeToFile(genuinePhoneList, GENUINE_FILE_PATH);
        }
        for (HashMap.Entry<Integer, Phone> entry : importedPhoneList.entrySet()) {
            if (entry.getValue().equals(phone)) {
                importedPhoneList.remove(entry.getKey());
                break;
            }
            fileService.writeToFile(importedPhoneList, IMPORT_FILE_PATH);
        }
    }
}
