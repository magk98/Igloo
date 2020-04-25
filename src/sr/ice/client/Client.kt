package sr.ice.client

import House.*
import com.zeroc.Ice.Communicator
import com.zeroc.Ice.Util
import kotlin.system.exitProcess

fun main(args: Array<String>){
    var status = 0
    val communicator: Communicator = Util.initialize(args)
    val baseFridge = communicator.stringToProxy("fridge/fridge1:tcp -h localhost -p 10000")
    val baseTemp = communicator.stringToProxy("temp/tempBasic:tcp -h localhost -p 10000")
    val superTemp = communicator.stringToProxy("temp/tempSuper:tcp -h localhost -p 10000")
    val wm = communicator.stringToProxy("wm/wm1:tcp -h localhost -p 10000")
    val fridgePrx: FridgePrx = FridgePrx.checkedCast(baseFridge)?: throw Error("Invalid proxy - fridge")
    val tempPrx = TempSensorPrx.checkedCast(baseTemp)?: throw Error("Invalid proxy - temp sensor")
    val superTempPrx = SuperTempSensorPrx.checkedCast(superTemp)?: throw Error("Invalid proxy - super temp sensor")
    val washingMachinePrx = WashingMachinePrx.checkedCast(wm)?: throw Error("Invalid proxy - washing machine")
    print("==> ")
    var line = readLine()
    while(line != "x"){
        if(line == null)
            break
        val lines = line.split(" ")
        when(lines[0].trim()){
            "cool" -> println("temperatura w lodówce zmniejszona do: ${fridgePrx.cool()} stopni")
            "heat" -> println("temperatura w lodówce zwiekszona do: ${fridgePrx.heat()} stopni")
            "fridgeT" -> println("temperatura lodowki wynosi: ${fridgePrx.showTemperature()}")
            "houseT" -> println("temperatura w domu wynosi: ${tempPrx.measure()} ${superTempPrx.showUnit()}")
            "houseH" -> println("temperatura w domu podniesiona o ${lines[1]} ${superTempPrx.showUnit()}, wynosi: ${superTempPrx.heatHouse(lines[1].toInt())} ${superTempPrx.showUnit()}")
            "houseC" -> println("temperatura w domu obnizona o ${lines[1]} ${superTempPrx.showUnit()}, wynosi: ${superTempPrx.coolHouse(lines[1].toInt())} ${superTempPrx.showUnit()}")
            "changeU" -> {
                superTempPrx.changeTempUnit()
                println("Jednostka temperatury zmieniona, wynosi teraz: ${superTempPrx.measure()} ${superTempPrx.showUnit()}")
            }
            "wash" -> {
                val washedClothes = washingMachinePrx.wash(lines[1].trim(), arrayOf(Cloth("bluzka", 20), Cloth("kurtka", 40), Cloth("spodenki", 10)))
                if(washedClothes.isNotEmpty())
                    washedClothes.forEach { println(it.name) }
                else
                    println("Nic się nie wyprało :(")
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