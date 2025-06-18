public class Medicine implements MedicineComponent {
    private String name;
    private String effect;

    public Medicine(String name, String effect) {
        this.name = name;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return "中草药名称：" + name + "，功效：" + effect;
    }

    @Override
    public void display() {
        System.out.println("中草药名称：" + name);
        System.out.println("功效：" + effect);
        System.out.println("------------------------");
    }

    @Override
    public void add(MedicineComponent component) {
        // 叶子节点不需要实现
    }

    @Override
    public void remove(MedicineComponent component) {
        // 叶子节点不需要实现
    }
} 