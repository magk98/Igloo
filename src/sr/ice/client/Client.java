package sr.ice.client;
// **********************************************************************
//
// Copyright (c) 2003-2019 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

import Demo.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import House.FridgePrx;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Util;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.LocalException;

public class Client 
{
	public static void main(String[] args) 
	{
		int status = 0;
		Communicator communicator = null;

		try {
			// 1. Inicjalizacja ICE
			communicator = Util.initialize(args);

			// 2. Uzyskanie referencji obiektu na podstawie linii w pliku konfiguracyjnym
			//    (w�wczas aplikacj� nale�y uruchomi� z argumentem --Ice.config=config.client
			//Ice.ObjectPrx base = communicator.propertyToProxy("Calc1.Proxy");
			
			// 2. To samo co powy�ej, ale mniej �adnie
			ObjectPrx base = communicator.stringToProxy("calc/calc11:tcp -h localhost -p 10000");
			ObjectPrx baseHouse = communicator.stringToProxy("fridge/fridge1:tcp -h localhost -p 10000");

			// 3. Rzutowanie, zaw�anie
			CalcPrx obj = CalcPrx.checkedCast(base);
			CalcPrx obj1 = CalcPrx.checkedCast(base);
			FridgePrx fridgePrx = FridgePrx.checkedCast(baseHouse);
			if (obj == null) throw new Error("Invalid proxy - obj");
			if (obj1 == null) throw new Error("Invalid proxy - obj1");
			if (fridgePrx == null) throw new Error("Invalid proxy - fridge");

			// 4. Wywolanie zdalnych operacji
			String line = null;
			java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
			CompletableFuture<Long> cfl = null;
			int temp = 5;
			do
			{
				try
				{
					System.out.print("==> ");
					System.out.flush();
					line = in.readLine();
					
					if (line == null)
					{
						break;
					}
					if(line.equals("cool")){
						temp = fridgePrx.cool(temp);
					}
					if(line.equals("heat")){
						temp = fridgePrx.heat(temp);
					}
					if (line.equals("add"))
					{
						long r = obj.add(7, 8);
						long r1 = obj1.add(1, 2);
						System.out.println("RESULT = " + r);
						System.out.println("RESULT2 = " + r1);
					}
					if (line.equals("subtract"))
					{
						long r = obj.subtract(7, 8);
						System.out.println("RESULT = " + r);
					}
					if (line.equals("add2"))
					{
						long r = obj.add(7000, 8000);
						System.out.println("RESULT = " + r);
					}
					if (line.equals("add-with-ctx")) //wys�anie danych stanowi�cych kontekst wywo�ania
					{
						Map<String,String> map = new HashMap<String, String>();
						map.put("key1", "val1");
						map.put("key2", "val2");
						long r = obj.add(7, 8, map);
						System.out.println("RESULT = " + r);
					}
					if (line.equals("add-asyn1")) //co si� dzieje ze sterowaniem?
					{
						cfl = obj.addAsync(7000, 8000).whenComplete((result, ex) -> 
						{
							 System.out.println("RESULT (asyn) = " + result);
						});
					}
					if (line.equals("add-asyn2-req")) //zlecenie wywo�ania
					{
						cfl = obj.addAsync(7000, 8000);						
					}
					if (line.equals("add-asyn2-res")) //odebranie wyniku
					{
						long r = cfl.join();						
						System.out.println("RESULT = " + r);
					}
					else if (line.equals("x"))
					{
						// Nothing to do
					}
				}
				catch (java.io.IOException ex)
				{
					System.err.println(ex);
				}
			}
			while (!line.equals("x"));


		} catch (LocalException e) {
			e.printStackTrace();
			status = 1;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			status = 1;
		}
		if (communicator != null) {
			// Clean up
			//
			try {
				communicator.destroy();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				status = 1;
			}
		}
		System.exit(status);
	}

}