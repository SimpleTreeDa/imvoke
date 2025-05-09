package edu.ccu.students.logan.smarthome.adapters;

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
            throw new RuntimeException(e);
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
        try {
            device.getClass().getMethod(turnOnMethod).invoke(device);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void turnOff() {
        try {
            device.getClass().getMethod(turnOffMethod).invoke(device);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isOn() {
        try {
            return (boolean) device.getClass().getMethod(isOnMethod).invoke(device);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object getDeviceInstance() {
        return device;
    }
}
