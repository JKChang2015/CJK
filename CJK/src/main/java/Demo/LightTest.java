package Demo;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 *
 * @author JKChang
 * @date 22-Nov-2016
 * @Description:
 *
 */
public class LightTest {

    public enum Light {
        RED(1), GREEN(2), YELLOW(3);

        private int nCode;

        private Light(int nCode) {
            this.nCode = nCode;
        }

        @Override
        public String toString() {
            return String.valueOf(this.nCode);
        }
    }

    private static void testTraversalEnum() {
        Light[] allLight = Light.values();
        for (Light aLight : allLight) {
            System.out.println("current light name: " + aLight.name());
            System.out.println("current light ordinal: " + aLight.ordinal());
            System.out.println("current light " + aLight);
        }
    }

    private static void testEnumMap() {
        EnumMap<Light, String> currEnumMap = new EnumMap<Light, String>(Light.class);
        currEnumMap.put(Light.RED, "Hong Deng");

        currEnumMap.put(Light.GREEN, "Lv Deng");

        currEnumMap.put(Light.YELLOW, "Huang Deng");

        for (Light aLight : Light.values()) {

            System.out.println("[key=" + aLight.name() + ",value="
                    + currEnumMap.get(aLight) + "]");

        }
    }

    private static void testEnumSet() {

        EnumSet<Light> currEnumSet = EnumSet.allOf(Light.class);

        for (Light aLightSetElement : currEnumSet) {

            System.out.println("Current set is " + aLightSetElement);

        }

    }

    public static void main(String[] args) {

        System.out.println("test enum traversal");
        testTraversalEnum();

        System.out.println("test enumMap");
        testEnumMap();

        System.out.println("test enmuSet");
        testEnumSet();

    }

}
