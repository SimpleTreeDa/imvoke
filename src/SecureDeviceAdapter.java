package src;

public class SecureDeviceAdapter {
    private final Object device;
    private String operateMethod = "operate";

    public SecureDeviceAdapter(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            this.device = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate device: " + className, e);
        }
    }

    public void setOperateMethodName(String name) {
        this.operateMethod = name;
    }

    public void operate() {
        try {
            device.getClass().getMethod(operateMethod).invoke(device);
        } catch (Exception e) {
            throw new RuntimeException("Failed to call " + operateMethod, e);
        }
    }

    public Object getDeviceInstance() {
        return device;
    }
}