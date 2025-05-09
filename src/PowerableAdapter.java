package src;

public class PowerableAdapter {
    private final Object device;
    private String turnOnMethod = "turnOn";
    private String turnOffMethod = "turnOff";
    private String isOnMethod = "isOn";

    public PowerableAdapter(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            this.device = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate device: " + className, e);
        }
    }

    public void setTurnOnMethodName(String name) {
        this.turnOnMethod = name;
    }
    public void setTurnOffMethodName(String name) {
        this.turnOffMethod = name;
    }
    public void setIsOnMethodName(String name) {
        this.isOnMethod = name;
    }

    public void turnOn() {
        invokeMethod(turnOnMethod);
    }

    public void turnOff() {
        invokeMethod(turnOffMethod);
    }

    public boolean isOn() {
        return (Boolean) invokeMethod(isOnMethod);
    }

    private Object invokeMethod(String methodName) {
        try {
            return device.getClass().getMethod(methodName).invoke(device);
        } catch (Exception e) {
            throw new RuntimeException("Failed to invoke " + methodName, e);
        }
    }

    public Object getDeviceInstance() {
        return device;
    }
}