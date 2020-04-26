package sr.ice.server;

import com.zeroc.Ice.Current;
import com.zeroc.Ice.Object;
import com.zeroc.Ice.ServantLocator;
import com.zeroc.Ice.UserException;

public class MyServantLocator implements ServantLocator {
    @Override
    public LocateResult locate(Current current) throws UserException {
        ServantLocator.LocateResult r = new ServantLocator.LocateResult();
        com.zeroc.Ice.Object servant = current.adapter.find(current.id);

        if(servant == null) {
            switch (current.id.category) {
                case "fridge":
                    servant = new FridgeI(5);
                    break;
                case "wm":
                    servant = new WashingMachineI();
                    break;
                case "superTemp":
                    servant = new SuperTempSensorI(MyHouseSingleton.getInstance());
                    break;
                case "Temp":
                    servant = new TempSensorI(MyHouseSingleton.getInstance());
                    break;
                case "betterTemp":
                    servant = new BetterTempSensorI(MyHouseSingleton.getInstance());
            }
            current.adapter.add(servant, current.id);
        }
        r.returnValue = servant;
        return r;
    }

    @Override
    public void deactivate(String s) {

    }

    @Override
    public void finished(com.zeroc.Ice.Current current, com.zeroc.Ice.Object servant, java.lang.Object cookie) throws UserException {
    }
}
