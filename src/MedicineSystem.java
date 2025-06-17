public class MedicineSystem {
    private static MedicineSystem instance;
    private MedicineFactory medicineFactory;
    private MedicineDatabase database;
    private SearchStrategy searchStrategy;
    private MedicineObserver observer;

    private MedicineSystem() {
        medicineFactory = new MedicineFactory();
        database = new MedicineDatabase();
        searchStrategy = new NameSearchStrategy(); // 默认使用名称搜索策略
        observer = new MedicineObserver();
        
        // 添加系统日志观察者
        SystemLogger logger = new SystemLogger();
        observer.addObserver(logger);
        
        // 添加操作统计观察者
        OperationCounter counter = new OperationCounter();
        observer.addObserver(counter);
    }

    public static MedicineSystem getInstance() {
        if (instance == null) {
            instance = new MedicineSystem();
        }
        return instance;
    }

    public void start() {
        System.out.println("中草药功效信息查询系统已启动");
        // 添加一些示例数据
        addSampleData();
    }

    private void addSampleData() {
        Medicine renshen = medicineFactory.createMedicine("人参", "补气养血，大补元气");
        Medicine danggui = medicineFactory.createMedicine("当归", "补血活血，调经止痛");
        database.addMedicine(renshen);
        database.addMedicine(danggui);
    }

    public void setSearchStrategy(SearchStrategy strategy) {
        this.searchStrategy = strategy;
    }

    // 新增药品
    public void addMedicine(String name, String effect) {
        Medicine medicine = medicineFactory.createMedicine(name, effect);
        database.addMedicine(medicine);
        observer.notifyObservers("添加了新药品：" + name);
        System.out.println("成功添加药品：" + name);
    }

    // 删除药品
    public void deleteMedicine(String name) {
        if (database.removeMedicine(name)) {
            observer.notifyObservers("删除了药品：" + name);
            System.out.println("成功删除药品：" + name);
        } else {
            System.out.println("未找到药品：" + name);
        }
    }

    // 更新药品信息
    public void updateMedicine(String name, String newEffect) {
        if (database.updateMedicine(name, newEffect)) {
            observer.notifyObservers("更新了药品信息：" + name);
            System.out.println("成功更新药品信息：" + name);
        } else {
            System.out.println("未找到药品：" + name);
        }
    }

    // 查询药品
    public void searchMedicine(String keyword) {
        searchStrategy.search(database, keyword);
        observer.notifyObservers("执行了搜索操作：" + keyword);
    }

    // 显示所有药品
    public void listAllMedicines() {
        System.out.println("\n所有药品列表：");
        database.listAllMedicines();
    }
} 