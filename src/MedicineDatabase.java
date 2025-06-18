import java.util.ArrayList;
import java.util.List;

public class MedicineDatabase {
    private List<Medicine> medicines;
    private List<MedicineCategory> categories;

    public MedicineDatabase() {
        medicines = new ArrayList<>();
        categories = new ArrayList<>();
        initializeCategories();
    }

    private void initializeCategories() {
        // 初始化一些基本分类
        MedicineCategory buqi = new MedicineCategory("补气类", "具有补气功效的中草药");
        MedicineCategory buxue = new MedicineCategory("补血类", "具有补血功效的中草药");
        MedicineCategory qingre = new MedicineCategory("清热类", "具有清热功效的中草药");
        
        categories.add(buqi);
        categories.add(buxue);
        categories.add(qingre);
    }

    public void addMedicine(Medicine medicine) {
        medicines.add(medicine);
        // 根据功效自动分类
        categorizeMedicine(medicine);
    }

    private void categorizeMedicine(Medicine medicine) {
        String effect = medicine.getEffect().toLowerCase();
        for (MedicineCategory category : categories) {
            if (isMedicineBelongsToCategory(medicine, category)) {
                category.add(medicine);
            }
        }
    }

    private boolean isMedicineBelongsToCategory(Medicine medicine, MedicineCategory category) {
        String effect = medicine.getEffect().toLowerCase();
        String categoryName = category.getCategoryName().toLowerCase();
        
        switch (categoryName) {
            case "补气类":
                return effect.contains("补气") || effect.contains("益气");
            case "补血类":
                return effect.contains("补血") || effect.contains("养血");
            case "清热类":
                return effect.contains("清热") || effect.contains("泻火");
            default:
                return false;
        }
    }

    public boolean removeMedicine(String name) {
        Medicine medicineToRemove = null;
        for (Medicine medicine : medicines) {
            if (medicine.getName().equals(name)) {
                medicineToRemove = medicine;
                break;
            }
        }
        
        if (medicineToRemove != null) {
            medicines.remove(medicineToRemove);
            // 从所有分类中移除
            for (MedicineCategory category : categories) {
                category.remove(medicineToRemove);
            }
            return true;
        }
        return false;
    }

    public boolean updateMedicine(String name, String newEffect) {
        for (Medicine medicine : medicines) {
            if (medicine.getName().equals(name)) {
                medicine.setEffect(newEffect);
                // 重新分类
                for (MedicineCategory category : categories) {
                    category.remove(medicine);
                }
                categorizeMedicine(medicine);
                return true;
            }
        }
        return false;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void listAllMedicines() {
        if (medicines.isEmpty()) {
            System.out.println("当前没有药品信息");
            return;
        }
        System.out.println("\n=== 所有中草药列表（按分类显示）===");
        for (MedicineCategory category : categories) {
            category.display();
        }
    }

    public void searchByName(String name) {
        boolean found = false;
        for (Medicine medicine : medicines) {
            if (medicine.getName().contains(name)) {
                medicine.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("未找到相关中草药");
        }
    }

    public void searchByEffect(String effect) {
        boolean found = false;
        for (Medicine medicine : medicines) {
            if (medicine.getEffect().contains(effect)) {
                medicine.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("未找到具有该功效的中草药");
        }
    }

    public void displayCategory(String categoryName) {
        for (MedicineCategory category : categories) {
            if (category.getCategoryName().equals(categoryName)) {
                category.display();
                return;
            }
        }
        System.out.println("未找到该分类");
    }
} 