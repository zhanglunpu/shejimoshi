public class OperationCounter implements Observer {
    private int searchCount = 0;
    private int addCount = 0;
    private int deleteCount = 0;
    private int updateCount = 0;

    @Override
    public void update(String message) {
        if (message.contains("搜索")) {
            searchCount++;
        } else if (message.contains("添加")) {
            addCount++;
        } else if (message.contains("删除")) {
            deleteCount++;
        } else if (message.contains("更新")) {
            updateCount++;
        }
        displayStats();
    }

    private void displayStats() {
        System.out.println("\n=== 操作统计 ===");
        System.out.println("搜索操作次数：" + searchCount);
        System.out.println("添加操作次数：" + addCount);
        System.out.println("删除操作次数：" + deleteCount);
        System.out.println("更新操作次数：" + updateCount);
        System.out.println("================\n");
    }
} 