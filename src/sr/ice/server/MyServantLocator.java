package sr.ice.server;

import com.zeroc.Ice.Current;
import com.zeroc.Ice.Object;
import com.zeroc.Ice.ServantLocator;
import com.zeroc.Ice.UserException;

public class MyServantLocator implements ServantLocator {
    @Override
    public LocateResult locate(Current current) throws UserException {
        return null;
    }

    @Override
    public void deactivate(String s) {

    }

    @Override
    public void finished(com.zeroc.Ice.Current current, com.zeroc.Ice.Object servant, java.lang.Object cookie) throws UserException {
        System.out.println(1);
    }
}
