import java.util.ArrayList;
import java.util.List;

public class MedicineDatabase {
    private List<Medicine> medicines;

    public MedicineDatabase() {
        medicines = new ArrayList<>();
    }

    public void addMedicine(Medicine medicine) {
        medicines.add(medicine);
    }

    public boolean removeMedicine(String name) {
        return medicines.removeIf(medicine -> medicine.getName().equals(name));
    }

    public boolean updateMedicine(String name, String newEffect) {
        for (Medicine medicine : medicines) {
            if (medicine.getName().equals(name)) {
                medicine.setEffect(newEffect);
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
        for (Medicine medicine : medicines) {
            System.out.println("药品名称：" + medicine.getName() + "，功效：" + medicine.getEffect());
        }
    }

    public void searchByName(String name) {
        for (Medicine medicine : medicines) {
            if (medicine.getName().contains(name)) {
                medicine.display();
            }
        }
    }
} 