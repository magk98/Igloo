package sr.ice.server;
// **********************************************************************
//
// Copyright (c) 2003-2019 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

import House.Fridge;
import House.MyHouse;
import House.SuperTempSensor;
import House.TempSensor;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Util;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Identity;

public class Server
{
	public void t1(String[] args)
	{
		int status = 0;
		Communicator communicator = null;

		try
		{
			// 1. Inicjalizacja ICE - utworzenie communicatora
			communicator = Util.initialize(args);

			// METODA 2 (niepolecana, dopuszczalna testowo): Konfiguracja adaptera Adapter1 jest w kodzie �r�d�owym
			ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("Adapter1", "tcp -h localhost -p 10000:udp -h localhost -p 10000");

			// 3. Stworzenie serwanta/serwant�w
			String devices[] = {"fridge", "basic temperature sensor", "super temperature sensor"};
			MyHouse house = new MyHouse(devices, 22);
			TempSensor tempServant = new TempSensorI(house);
			SuperTempSensor superTempServant = new SuperTempSensorI(house);
			Fridge fridgeServant1 = new FridgeI(10);

			//próba implementacji servant locatora -> not working yet
			//MyServantLocator sl = new MyServantLocator();
			//adapter.addServantLocator(sl, "Fridge");
			//System.out.println(adapter.findServantLocator("Fridge"));

						    
			// 4. Dodanie wpis�w do tablicy ASM
			adapter.add(tempServant, new Identity("tempBasic", "temp"));
			adapter.add(superTempServant, new Identity("tempSuper", "temp"));
	        adapter.add(fridgeServant1, new Identity("fridge1", "fridge"));

	        
			// 5. Aktywacja adaptera i przej�cie w p�tl� przetwarzania ��da�
			adapter.activate();
			
			System.out.println("Entering event processing loop...");
			
			communicator.waitForShutdown(); 		
			
		}
		catch (Exception e)
		{
			System.err.println(e);
			status = 1;
		}
		if (communicator != null)
		{
			// Clean up
			try
			{
				communicator.destroy();
			}
			catch (Exception e)
			{
				System.err.println(e);
				status = 1;
			}
		}
		System.exit(status);
	}


	public static void main(String[] args)
	{
		Server app = new Server();
		app.t1(args);
	}
}
