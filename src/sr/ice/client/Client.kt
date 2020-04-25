package sr.ice.client

import House.FridgePrx
import com.zeroc.Ice.Communicator
import com.zeroc.Ice.Util
import kotlin.system.exitProcess

fun main(args: Array<String>){
    var status = 0
    var communicator: Communicator = Util.initialize(args)
    val baseHouse = communicator.stringToProxy("fridge/fridge1:tcp -h localhost -p 10000")

    var temp = 5
    val fridgePrx: FridgePrx = FridgePrx.checkedCast(baseHouse)?: throw Error("Invalid proxy - fridge")
    print("==> ")
    var line = readLine()
    while(line != "x"){
        if(line == null)
            break
        when(line){
            "cool" -> {
                temp = fridgePrx.cool(temp)
                println("temperatura w lodówce zmniejszona do: $temp stopni")
            }
            "heat" -> {
                temp = fridgePrx.heat(temp)
                println("temperatura w lodówce zwiekszona do: $temp stopni")
            }
            else -> print(line)
        }
        print("==> ")
        line = readLine()
    }
    try {
        communicator.destroy()
    } catch (e: Exception) {
        System.err.println(e.message)
        status = 1
    }
    exitProcess(status)
}