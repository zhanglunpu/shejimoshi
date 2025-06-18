import java.util.ArrayList;
import java.util.List;

public class MedicineCategory implements MedicineComponent {
    private String categoryName;
    private String description;
    private List<MedicineComponent> medicines;

    public MedicineCategory(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
        this.medicines = new ArrayList<>();
    }

    @Override
    public void display() {
        System.out.println("\n=== " + categoryName + " ===");
        System.out.println("分类说明：" + description);
        System.out.println("包含中草药数量：" + medicines.size());
        System.out.println("------------------------");
        for (MedicineComponent medicine : medicines) {
            medicine.display();
        }
    }

    @Override
    public void add(MedicineComponent component) {
        medicines.add(component);
    }

    @Override
    public void remove(MedicineComponent component) {
        medicines.remove(component);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<MedicineComponent> getMedicines() {
        return medicines;
    }
} 