package io;

import model.Phone;

import java.util.HashMap;

public interface FileInterface {
    void writeToFile(HashMap<Integer, Phone> phoneList, String path);

    HashMap<Integer, Phone> readFromFile(String path, boolean isImported);
}
