package service;

import io.FileInterface;
import io.FileService;
import model.Phone;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PhoneService implements PhoneServiceInterface {
    private final FileInterface fileService = new FileService();

    @Override
    public void addPhone(HashMap<Integer, Phone> phoneList, boolean isImported) {
        if (isImported) {
            fileService.writeToFile(phoneList, "data/importedPhone.csv");
        } else {
            fileService.writeToFile(phoneList, "data/genuinePhone.csv");
        }
    }

    @Override
    public HashMap<Integer, Phone> listPhone() {
        HashMap<Integer, Phone> phoneList = new HashMap<>();
        phoneList.putAll(fileService.readFromFile("data/genuinePhone.csv", false));
        phoneList.putAll(fileService.readFromFile("data/importedPhone.csv", true));
        return phoneList;
    }

    @Override
    public HashMap<Integer, Phone> searchPhoneByName(String name) {
        HashMap<Integer, Phone> phoneList = listPhone();
        return (HashMap<Integer, Phone>) phoneList.entrySet().stream().filter(entry -> entry.getValue().getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue
        ));
    }

    @Override
    public Phone searchPhoneByID(int id) {
        HashMap<Integer, Phone> phoneList = listPhone();
        return phoneList.get(id);
    }

    @Override
    public void removePhone(int id) {
        HashMap<Integer, Phone> phoneList = listPhone();
        phoneList.remove(id);
    }

}
