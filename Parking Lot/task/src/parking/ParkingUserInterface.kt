package parking

import kotlin.system.exitProcess

class ParkingUserInterface {
    private var parking: Parking? = null

    init {
        start()
    }

    fun start() {
        createParking()
        while (true) {
            val userRequest = readln().split(" ")
            val command = userRequest[0]
            when (command) {
                "create" -> {
                    parking = Parking(userRequest[1].toInt())
                    println("Created a parking lot with ${userRequest[1]} spots.")
                }
                "park" -> parkCommand(userRequest[1], userRequest[2])
                "status" -> statusCommand()
                "leave" -> leaveCommand(userRequest[1].toInt())
                "reg_by_color" -> regNumberByColor(userRequest[1])
                "spot_by_color" -> spotByColor(userRequest[1])
                "spot_by_reg" -> spotByRegNumber(userRequest[1])
                "exit" -> exitProcess(0)
            }
        }
    }

    fun createParking() {
        while (true) {
            val input = readln().split(" ")
            val command = input[0]
            when (command) {
                "create" -> {
                    parking = Parking(input[1].toInt())
                    println("Created a parking lot with ${input[1]} spots.")
                    break
                }
                "exit" -> exitProcess(0)
                else -> println("Sorry, a parking lot has not been created.")
            }
        }
    }


    fun parkCommand(plateNumber: String, color: String) {
        if (parking?.isParkingFull == true) {
            println("Sorry, the parking lot is full.")
        } else {
            println("$color car parked in spot ${parking?.parkCar(plateNumber, color)}.")
        }
    }

    fun statusCommand() {
       if (parking?.isParkingEmpty == false) {
           println(parking?.getInfo()?.joinToString("\n"))
       } else {
           println("Parking lot is empty.")
       }
    }

    fun leaveCommand(spotNumber: Int) {
        if (parking?.isSpotEmpty(spotNumber) != true) {
            parking?.clearSpot(spotNumber)
            println("Spot $spotNumber is free.")
        } else {
            println("There is no car in spot $spotNumber.")
        }
    }

    fun regNumberByColor(color: String) {
        if (!parking?.getRegByColor(color).isNullOrEmpty()) {
            println(parking?.getRegByColor(color)?.joinToString())
        } else {
            println("No cars with color $color were found.")
        }
    }

    fun spotByColor(color: String) {
        if (!parking?.getSpotByColor(color).isNullOrEmpty()) {
            println(parking?.getSpotByColor(color)?.joinToString())
        } else {
            println("No cars with color $color were found.")
        }
    }

    fun spotByRegNumber(regNumber: String) {
        if (!parking?.getSpotByRegistrationNumber(regNumber).isNullOrEmpty()) {
            println(parking?.getSpotByRegistrationNumber(regNumber)?.joinToString())
        } else {
            println("No cars with registration number $regNumber were found.")
        }
    }
}