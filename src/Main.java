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
            System.out.println("6. 查看分类");
            System.out.println("0. 退出系统");
            System.out.print("请选择操作（0-6）：");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("请选择搜索方式：");
                    System.out.println("1. 按名称搜索");
                    System.out.println("2. 按功效搜索");
                    String searchMode = scanner.nextLine();
                    
                    if (searchMode.equals("1")) {
                        system.setSearchStrategy(new NameSearchStrategy());
                        System.out.print("请输入要查询的中草药名称：");
                    } else if (searchMode.equals("2")) {
                        system.setSearchStrategy(new EffectSearchStrategy());
                        System.out.print("请输入要查询的功效关键词：");
                    } else {
                        System.out.println("无效的选择！");
                        break;
                    }
                    
                    String searchKeyword = scanner.nextLine();
                    system.searchMedicine(searchKeyword);
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

                case "6":
                    System.out.println("\n=== 中草药分类 ===");
                    System.out.println("1. 补气类");
                    System.out.println("2. 补血类");
                    System.out.println("3. 清热类");
                    System.out.print("请选择要查看的分类（1-3）：");
                    String categoryChoice = scanner.nextLine();
                    String categoryName = "";
                    switch (categoryChoice) {
                        case "1":
                            categoryName = "补气类";
                            break;
                        case "2":
                            categoryName = "补血类";
                            break;
                        case "3":
                            categoryName = "清热类";
                            break;
                        default:
                            System.out.println("无效的选择！");
                            break;
                    }
                    if (!categoryName.isEmpty()) {
                        system.displayCategory(categoryName);
                    }
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