# Igloo
Code for DS labs, smart house using ZerocIce

Smart house simulation in Java & Kotlin using Zeroc Ice. Supported devices and their functionalities
- Fridge: "fridgeH" -> increasing fridge temperature by 1 C, "fridgeC" -> decreasing fridge temperature by 1 C, "fridgeT" -> displaying fridge current temperature
- temperature sensor: "houseT" -> displaying house temperature in C
- better temperature sensor: "house2T" -> displaying house temperature in C & F
- super temperature sensor: "houseH <temp>" -> increasing house temperature by temp degrees, "houseC <temp>" -> decreasing house temperature by temp degrees, "changeUnit" -> changing temperature units, displaying temperature in another unit
- washing machine: "wash <fast|medium|long>" -> washing clothes, returning list of clean clothes
