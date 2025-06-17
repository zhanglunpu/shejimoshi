public class MedicineFactory {
    public Medicine createMedicine(String name, String effect) {
        return new Medicine(name, effect);
    }
} 