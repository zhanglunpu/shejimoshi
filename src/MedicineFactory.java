public class MedicineFactory implements Factories{
    @Override
    public Medicine createMedicine(String name, String effect) {
        return new Medicine(name, effect);
    }
} 