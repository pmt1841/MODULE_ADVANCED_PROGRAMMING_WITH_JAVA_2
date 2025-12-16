package ui;

public class MainMenu extends Menu {
    public void showMenu(Submenu submenu) {

        while (true) {
            System.out.println("===Chương trình quản lí điện thoại===");
            System.out.println("Chọn chức năng theo số (để tiếp tục):");
            System.out.println("1. Thêm mới");
            System.out.println("2. Xóa");
            System.out.println("3. Xem danh sách điện thoại");
            System.out.println("4. Tìm kiếm");
            System.out.println("5. Thoát");
            System.out.println("Chọn chức năng");
            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        submenu.addPhone();
                        break;
                    case 2:
                        submenu.removePhone();
                        break;
                    case 3:
                        submenu.listPhone();
                        break;
                    case 4:
                        submenu.searchPhone();
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println("Invalid input");
                        scanner.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        Submenu submenu = new Submenu();
        MainMenu mainMenu = new MainMenu();
        mainMenu.showMenu(submenu);
    }
}
