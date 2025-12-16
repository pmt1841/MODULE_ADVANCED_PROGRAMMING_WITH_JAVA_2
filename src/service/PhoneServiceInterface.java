package service;

import model.Phone;

import java.util.HashMap;

public interface PhoneServiceInterface {
    void addPhone(HashMap<Integer,Phone> phoneList, boolean isImported);

    HashMap<Integer,Phone> listPhone();

    HashMap<Integer,Phone> searchPhoneByName(String name);

    Phone searchPhoneByID(int id);

    void removePhone(int id);
}
