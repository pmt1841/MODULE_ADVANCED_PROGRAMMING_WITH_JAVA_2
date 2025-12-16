package ui;

import model.GenuinePhone;
import model.ImportedPhone;
import model.Phone;
import service.PhoneService;

import java.util.HashMap;
import java.util.InputMismatchException;

public class Submenu extends Menu implements SubmenuInterface {
    private final PhoneService phoneService = new PhoneService();
    private final String WORD_REGEX = "[a-zA-Z]+";
    private final String STRING_REGEX = "[A-Za-z]+[\\w ]*";

    private String getInputName() {
        while (true) {
            System.out.println("Nhập tên:");
            String name = scanner.nextLine().trim();
            boolean isValid = name.matches(STRING_REGEX);
            if (isValid)
                return name;
            else
                System.out.println("Nhập sai");
        }
    }

    private double getInputPrice() {
        while (true) {
            try {
                System.out.println("Nhập giá:");
                double price = scanner.nextDouble();
                scanner.nextLine();
                return price;
            } catch (InputMismatchException e) {
                System.out.println("Nhập sai");
                scanner.nextLine();
            }
        }
    }

    private int getInputQuantity() {
        while (true) {
            try {
                System.out.println("Nhập số lượng:");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                return quantity;
            } catch (InputMismatchException e) {
                System.out.println("Nhập sai");
                scanner.nextLine();
            }
        }
    }

    private String getInputBrand() {
        while (true) {
            System.out.println("Nhập hãng:");
            String brand = scanner.nextLine().trim();
            boolean isValid = brand.matches(WORD_REGEX);
            if (isValid) {
                return brand;
            } else {
                System.out.println("Nhập sai");
            }
        }
    }

    private int getInputId() {
        while (true) {
            try {
                System.out.println("Nhập id (0 để thoát)");
                int id = scanner.nextInt();
                scanner.nextLine();
                return id;
            } catch (InputMismatchException e) {
                System.out.println("Nhập sai");
                scanner.nextLine();
            }
        }
    }

    private int getInputWarrantyPeriod() {
        while (true) {
            try {
                System.out.println("Nhập thời gian bảo hành (ngày):");
                int warrantyPeriod = scanner.nextInt();
                scanner.nextLine();
                return warrantyPeriod;
            } catch (InputMismatchException e) {
                System.out.println("Nhập sai");
                scanner.nextLine();
            }

        }
    }

    private String getInputWarrantyScope() {
        while (true) {
            System.out.println("Nhập phạm vi bảo hành (Toàn quốc/Quốc tế):");
            String warrantyScope = scanner.nextLine().trim();
            boolean isValid = warrantyScope.equalsIgnoreCase("toan quoc") || warrantyScope.equalsIgnoreCase("quoc te");
            if (isValid) {
                return warrantyScope;
            } else {
                System.out.println("Nhập sai");
            }
        }
    }

    private String getInputOrigin() {
        while (true) {
            System.out.println("Nhập quốc gia xách tay:");
            String origin = scanner.nextLine().trim();
            boolean isValid = origin.matches(STRING_REGEX) && origin.matches("^(?!.*viet\\s*nam).*$");
            if (isValid) {
                return origin;
            } else {
                System.out.println("Nhập sai");
            }
        }
    }

    private String getInputStatus() {
        while (true) {
            System.out.println("Nhập trạng thái (Đã sửa chữa/Chưa sửa chữa)");
            String status = scanner.nextLine();
            boolean isValid = status.equalsIgnoreCase("Da sua chua") || status.equalsIgnoreCase("Chua sua chua");
            if (isValid) {
                return status;
            } else {
                System.out.println("Nhập sai");
            }
        }
    }

    private HashMap<Integer, Phone> handleAdd(boolean isImported) {
        HashMap<Integer, Phone> phoneList = new HashMap<>();

        String name = getInputName();
        double price = getInputPrice();
        int quantity = getInputQuantity();
        String brand = getInputBrand();

        if (isImported) {
            String origin = getInputOrigin();
            String status = getInputStatus();
            Phone importedPhone = new ImportedPhone(name, price, quantity, brand, origin, status);
            phoneList.put(importedPhone.getId(), importedPhone);
        } else {
            int warrantyPeriod = getInputWarrantyPeriod();
            String warrantyScope = getInputWarrantyScope();
            Phone genuinePhone = new GenuinePhone(name, price, quantity, brand, warrantyScope, warrantyPeriod);
            phoneList.put(genuinePhone.getId(), genuinePhone);
        }

        return phoneList;
    }

    @Override
    public void addPhone() {
        while (true) {
            HashMap<Integer, Phone> phoneList;

            System.out.println("===Thêm điện thoại===");
            System.out.println("Thêm loại điện thoại nào:");
            System.out.println("0. Thoát");
            System.out.println("1. Chính hãng");
            System.out.println("2. Xách tay");
            System.out.println("Nhập lựa chọn");

            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 0:
                        return;
                    case 1:
                        System.out.println("===Thêm mới chính hãng===");
                        phoneList = handleAdd(false);
                        phoneService.addPhone(phoneList, false);
                        break;
                    case 2:
                        System.out.println("===Thêm mới xách tay===");
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
        while (true) {
            System.out.println("===Xóa điện thoại===");
            int id = getInputId();
            if (id == 0) {
                return;
            }

            boolean exit = false;
            Phone phone = phoneService.searchPhone(id);
            boolean notFound = phone != null;
            if (notFound) {
                System.out.println("Tìm thấy");
                System.out.println(phone);
                while (!exit) {
                    System.out.println("Có chắn chắn xóa không?(Y/N)");
                    String answer = scanner.nextLine().trim().toLowerCase();
                    switch (answer) {
                        case "y":
                            phoneService.removePhone(phone);
                            break;
                        case "n":
                            exit = true;
                            break;
                        default:
                            System.out.println("Nhập sai");
                    }
                }
            }
            System.out.println("Không tìm thấy " + id);
        }
    }

    @Override
    public void listPhone() {
        System.out.println("===Danh sách điện thoại===");
        HashMap<Integer, Phone> phoneList = phoneService.listPhone();
        boolean isEmpty = phoneList.isEmpty();
        if (isEmpty) {
            System.out.println("Trống");
        } else {
            for (HashMap.Entry<Integer, Phone> entry : phoneList.entrySet()) {
                System.out.println(entry.getValue());
            }
        }
    }

    @Override
    public void searchPhone() {
        while (true) {
            System.out.println("===Tìm kiếm điện thoại===");
            System.out.println("0. Thoát");
            System.out.println("1. Theo ID");
            System.out.println("2. Theo tên");
            System.out.println("Nhập lựa chọn:");

            try {
                int option = scanner.nextInt();
                boolean notFound;
                switch (option) {
                    case 0:
                        return;
                    case 1:
                        int id = getInputId();
                        Phone phone = phoneService.searchPhone(id);
                        notFound = phone != null;
                        if (notFound) {
                            System.out.println("Tìm thấy");
                            System.out.println(phone);
                        } else {
                            System.out.println("Không tìm thấy");
                        }

                        break;
                    case 2:
                        String name = getInputName();
                        HashMap<Integer, Phone> phoneList = phoneService.searchPhone(name);
                        notFound = phoneList.isEmpty();
                        if (notFound) {
                            System.out.println("Không tìm thấy");
                        } else {
                            System.out.println("Tìm thấy");
                            for (HashMap.Entry<Integer, Phone> entry : phoneList.entrySet()) {
                                System.out.println(entry.getValue());
                            }
                        }
                        break;
                    default:
                        System.out.println("Nhập sai");
                        scanner.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("Nhập sai");
                scanner.nextLine();
            }
        }
    }
}
