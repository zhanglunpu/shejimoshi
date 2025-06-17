public class NameSearchStrategy implements SearchStrategy {
    @Override
    public void search(MedicineDatabase database, String keyword) {
        System.out.println("按名称搜索：" + keyword);
        database.searchByName(keyword);
    }
} 