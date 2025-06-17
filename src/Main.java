import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 获取单例实例
        MedicineSystem system = MedicineSystem.getInstance();
        
        // 启动系统
        system.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== 中草药功效信息查询系统 ===");
            System.out.println("1. 查询中草药");
            System.out.println("2. 添加中草药");
            System.out.println("3. 删除中草药");
            System.out.println("4. 修改中草药信息");
            System.out.println("5. 显示所有中草药");
            System.out.println("0. 退出系统");
            System.out.print("请选择操作（0-5）：");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("请输入要查询的中草药名称：");
                    String searchName = scanner.nextLine();
                    system.searchMedicine(searchName);
                    break;

                case "2":
                    System.out.print("请输入中草药名称：");
                    String addName = scanner.nextLine();
                    System.out.print("请输入中草药功效：");
                    String addEffect = scanner.nextLine();
                    system.addMedicine(addName, addEffect);
                    break;

                case "3":
                    System.out.print("请输入要删除的中草药名称：");
                    String deleteName = scanner.nextLine();
                    system.deleteMedicine(deleteName);
                    break;

                case "4":
                    System.out.print("请输入要修改的中草药名称：");
                    String updateName = scanner.nextLine();
                    System.out.print("请输入新的功效说明：");
                    String newEffect = scanner.nextLine();
                    system.updateMedicine(updateName, newEffect);
                    break;

                case "5":
                    system.listAllMedicines();
                    break;

                case "0":
                    System.out.println("感谢使用，系统已退出。");
                    scanner.close();
                    return;

                default:
                    System.out.println("无效的选择，请重新输入！");
            }
        }
    }
} 