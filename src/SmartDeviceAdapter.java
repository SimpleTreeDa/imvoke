// SmartDeviceAdapter.java
package src;

public class SmartDeviceAdapter {
    private final Object device;

    public SmartDeviceAdapter(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            this.device = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate device: " + className, e);
        }
    }

    /** Sets the name on the underlying device by calling its setName(String) method */
    public void setName(String name) {
        try {
            device.getClass()
                  .getMethod("setName", String.class)
                  .invoke(device, name);
        } catch (Exception e) {
            throw new RuntimeException("Failed to call setName", e);
        }
    }

    /** Retrieves the name from the underlying device by calling its getName() */
    public String getName() {
        try {
            return (String) device.getClass()
                                  .getMethod("getName")
                                  .invoke(device);
        } catch (Exception e) {
            throw new RuntimeException("Failed to call getName", e);
        }
    }

    /** Exposes the raw device instance for inspection in tests */
    public Object getDeviceInstance() {
        return device;
    }
}
