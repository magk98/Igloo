package sr.ice.server;

import House.MyHouse;
import House.TempSensor;
import com.zeroc.Ice.Current;

public class TempSensorI implements TempSensor {
    public MyHouseSingleton myHouse;

    public TempSensorI(MyHouseSingleton myHouse){
        this.myHouse = myHouse;
    }

    @Override
    public int measure(Current current) {
        return myHouse.temperature;
    }
}
