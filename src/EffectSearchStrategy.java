public class EffectSearchStrategy implements SearchStrategy {
    @Override
    public void search(MedicineDatabase database, String keyword) {
        System.out.println("按功效搜索：" + keyword);
        database.searchByEffect(keyword);
    }
} 