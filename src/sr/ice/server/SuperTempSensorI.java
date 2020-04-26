package sr.ice.server;

import House.MyHouse;
import House.SuperTempSensor;
import com.zeroc.Ice.Current;

public class SuperTempSensorI implements SuperTempSensor {
    private MyHouseSingleton myHouse;

    SuperTempSensorI(MyHouseSingleton myHouse){
        this.myHouse = myHouse;
    }

    @Override
    public int measure(Current current) {
        return myHouse.temperature;
    }

    @Override
    public int heatHouse(int temp, Current current) {
        this.myHouse.temperature += temp;
        return this.myHouse.temperature;
    }

    @Override
    public int coolHouse(int temp, Current current) {
        this.myHouse.temperature -= temp;
        return this.myHouse.temperature;
    }

    @Override
    public String showUnit(Current current) {
        return myHouse.unit;
    }

    @Override
    public void changeTempUnit(Current current) {
        if(myHouse.unit.equals("C")){
            myHouse.unit = "F";
            this.myHouse.temperature = 2 * this.myHouse.temperature + 32;
            return;
        }
        if(myHouse.unit.equals("F")){
            myHouse.unit = "C";
            this.myHouse.temperature = (this.myHouse.temperature - 32) / 2;
        }
    }
}
