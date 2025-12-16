package ui;

import model.GenuinePhone;
import model.ImportedPhone;
import model.Phone;
import service.PhoneService;

import java.util.HashMap;

public class Submenu extends Menu implements SubmenuInterface {
    private final PhoneService phoneService = new PhoneService();

    private HashMap<Integer, Phone> handleAdd(boolean isImported) {
        HashMap<Integer, Phone> phoneList = new HashMap<>();
        String name = scanner.nextLine();
        double price = scanner.nextDouble();
        int quantity = scanner.nextInt();
        scanner.nextLine();
        String brand = scanner.nextLine();

        if (isImported) {
            String origin = scanner.nextLine();
            String status = scanner.nextLine();
            ImportedPhone importedPhone = new ImportedPhone(name, price, quantity, brand, origin, status);
            phoneList.put(importedPhone.getId(), importedPhone);
        } else {
            int warrantyPeriod = scanner.nextInt();
            scanner.nextLine();
            String warrantyScope = scanner.nextLine();
            GenuinePhone genuinePhone = new GenuinePhone(name, price, quantity, brand, warrantyScope, warrantyPeriod);
            phoneList.put(genuinePhone.getId(), genuinePhone);
        }

        return phoneList;
    }

    @Override
    public void addPhone() {
        while (true) {
            HashMap<Integer, Phone> phoneList;

            System.out.println("Thêm loại điện thoại nào:");
            System.out.println("0. Thoát");
            System.out.println("1. Chính hãng");
            System.out.println("2. Xách tay");

            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 0:
                        return;
                    case 1:
                        System.out.println("===Thêm mới chính hãng===");
                        phoneList = handleAdd(false);
                        phoneService.addPhone(phoneList, false);
                        break;
                    case 2:
                        System.out.println("===Thêm mói xách tay===");
                        phoneList = handleAdd(true);
                        phoneService.addPhone(phoneList, true);
                        break;
                    default:
                        System.out.println("Nhập sai vui lòng nhập lại");
                        scanner.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Nhập sai vui lòng nhập lại");
                scanner.nextLine();
            }
        }
    }

    @Override
    public void removePhone() {
        boolean exit = false;
        while (true) {
            System.out.println("Nhập id (0 để thoát)");
            try {
                int id = scanner.nextInt();
                if(id==0){
                    return;
                }
                Phone phone = phoneService.searchPhoneByID(id);
                if (phone!=null){
                    System.out.println("Tìm thấy");
                    while (!exit) {
                        System.out.println("Có chắn chắn xóa không?(Y/N)");
                        switch (scanner.nextLine().toLowerCase()) {
                            case "y":
                                phoneService.removePhone(id);
                                break;
                            case "n":
                                exit = true;
                                break;
                            default:
                                System.out.println("Nhập sai");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Nhập sai");
                scanner.nextLine();
            }
        }

    }

    @Override
    public void listPhone() {
        System.out.println("===Danh sách điện thoại===");
        HashMap<Integer, Phone> phoneList = phoneService.listPhone();
        for (HashMap.Entry<Integer, Phone> entry : phoneList.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    @Override
    public void searchPhone() {
        while (true) {
            System.out.println("===Tìm kiếm điện thoại===");
            System.out.println("0. Thoát");
            System.out.println("1. Theo ID");
            System.out.println("2. Theo tên");

            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 0:
                        return;
                    case 1:
                        try {
                            System.out.println("Nhập id:");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            Phone phone= phoneService.searchPhoneByID(id);
                            System.out.println(phone);
                        } catch (Exception e) {
                            System.out.println("Nhập sai");
                            scanner.nextLine();
                        }
                        break;
                    case 2:
                        System.out.println("Nhập tên:");
                        String name = scanner.nextLine();
                        HashMap<Integer, Phone> phoneList = phoneService.searchPhoneByName(name);
                        for (HashMap.Entry<Integer, Phone> entry : phoneList.entrySet()) {
                            System.out.println(entry.getValue());
                        }
                        break;
                    default:
                        System.out.println("Nhập sai");
                        scanner.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Nhập sai");
                scanner.nextLine();
            }
        }
    }
}
