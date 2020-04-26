package sr.ice.server;

import House.BetterTempSensor;
import House.MyHouse;
import com.zeroc.Ice.Current;

public class BetterTempSensorI implements BetterTempSensor {
    private MyHouseSingleton myHouse;
    private String unit = "C";

    BetterTempSensorI(MyHouseSingleton myHouse){
        this.myHouse = myHouse;
    }

    @Override
    public int measure(Current current) {
        return myHouse.temperature;
    }

    @Override
    public String showFormula(Current current) {
        return null;
    }
}
