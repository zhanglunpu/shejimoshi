public class MedicineSystem extends MedicineFactory {
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
        // 补气类中草药
        Medicine renshen = medicineFactory.createMedicine("人参", "补气益中，大补元气");
        Medicine huangqi = medicineFactory.createMedicine("黄芪", "补气固表，利水消肿");
        Medicine dangshen = medicineFactory.createMedicine("党参", "补中益气，健脾益肺");
        
        // 补血类中草药
        Medicine danggui = medicineFactory.createMedicine("当归", "补血活血，调经止痛");
        Medicine shudihuang = medicineFactory.createMedicine("熟地黄", "补血滋阴，益精填髓");
        Medicine ejiao = medicineFactory.createMedicine("阿胶", "补血滋阴，润燥止血");
        
        // 清热类中草药
        Medicine huanglian = medicineFactory.createMedicine("黄连", "清热燥湿，泻火解毒");
        Medicine jinyinhua = medicineFactory.createMedicine("金银花", "清热解毒，疏散风热");
        Medicine banlangen = medicineFactory.createMedicine("板蓝根", "清热解毒，凉血利咽");
        
        // 添加到数据库
        database.addMedicine(renshen);
        database.addMedicine(huangqi);
        database.addMedicine(dangshen);
        database.addMedicine(danggui);
        database.addMedicine(shudihuang);
        database.addMedicine(ejiao);
        database.addMedicine(huanglian);
        database.addMedicine(jinyinhua);
        database.addMedicine(banlangen);
    }

    public void setSearchStrategy(SearchStrategy strategy) {
        this.searchStrategy = strategy;
    }

    // 新增中草药
    public void addMedicine(String name, String effect) {
        Medicine medicine = medicineFactory.createMedicine(name, effect);
        database.addMedicine(medicine);
        observer.notifyObservers("添加了新中草药：" + name);
        System.out.println("成功添加中草药：" + name);
    }

    // 删除中草药
    public void deleteMedicine(String name) {
        if (database.removeMedicine(name)) {
            observer.notifyObservers("删除了中草药：" + name);
            System.out.println("成功删除中草药：" + name);
        } else {
            System.out.println("未找到中草药：" + name);
        }
    }

    // 更新中草药信息
    public void updateMedicine(String name, String newEffect) {
        if (database.updateMedicine(name, newEffect)) {
            observer.notifyObservers("更新了中草药信息：" + name);
            System.out.println("成功更新中草药信息：" + name);
        } else {
            System.out.println("未找到中草药：" + name);
        }
    }

    // 查询中草药
    public void searchMedicine(String keyword) {
        searchStrategy.search(database, keyword);
        observer.notifyObservers("执行了搜索操作：" + keyword);
    }

    // 显示所有中草药
    public void listAllMedicines() {
        System.out.println("\n所有中草药列表：");
        database.listAllMedicines();
    }

    // 显示指定分类的中草药
    public void displayCategory(String categoryName) {
        database.displayCategory(categoryName);
    }
}


