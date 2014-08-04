package tum.sathiti.model;

/**
 *
 */
public enum DeviceType {

    DESKTOP("desktop"),
    MOBILE("mobile");

    private String textValue;

    private DeviceType(String textValue) {
        this.textValue = textValue;
    }

    public String toTextValue() {
        return this.textValue;
    }

    public static DeviceType fromText(String text) {
        for (DeviceType eachSite : DeviceType.values()) {
            if (eachSite.toTextValue().equals(text)) {
                return eachSite;
            }
        }

        return null;
    }
}