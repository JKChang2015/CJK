package Demo;

import Demo.Color;

/**
 *
 * @author JKChang
 * @date 22-Nov-2016
 * @Description:
 *
 */
public interface Food {

    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, LATTE, CAPPUCCINO
    }

    enum Dessert implements Food {
        FRUIT, CAKE, GELATO
    }
}
