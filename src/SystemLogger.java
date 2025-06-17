public class SystemLogger implements Observer {
    @Override
    public void update(String message) {
        System.out.println("[系统日志] " + message);
    }
} 