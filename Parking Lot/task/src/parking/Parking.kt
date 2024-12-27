package parking

import java.util.ArrayList

class Parking(amountOfSpots: Int) {
    private val spots = ArrayList<Car?>()

    init {
        spots.clear()
        repeat(amountOfSpots) {
            spots.add(null)
        }
    }

    val isParkingEmpty: Boolean
        get() = spots.all { it == null }

    val isParkingFull: Boolean
        get() = spots.all { it != null }

    fun isSpotEmpty(numberOfSpot: Int): Boolean {
        return spots[numberOfSpot] == null
    }



    fun parkCar(plateNumber: String, color: String): Int {
        val car = Car(plateNumber, color)
        val index = spots.indexOfFirst { it == null } //Find first empty place on parking
        spots[index] = car
        return index + 1
    }

    fun clearSpot(numberOfSpot: Int) {
        spots[numberOfSpot - 1] = null
    }


    fun getInfo(): MutableList<String> {
        val resultList = mutableListOf<String>()
        for (car in spots) {
            if (car != null) {
                val number = spots.indexOfFirst{it === car} + 1
                val plateNumber = car.plateNumber
                val color = car.color
                resultList.add("$number $plateNumber $color")
            }
        }
        return resultList
    }


    fun getRegByColor(color: String): List<String> {
        return spots.filterNotNull()
        .filter{ color.uppercase() == it.color.uppercase() }
        .map{ it.plateNumber }
    }

    fun getSpotByColor(color: String): List<Int> {
         return spots.zip(spots.indices)
         .filter {it.first != null}
         .filter { color.uppercase() == it.first?.color?.uppercase() }
         .map { it.second + 1 }
    }

    fun getSpotByRegistrationNumber(regNumber: String): List<Int> {
        return spots.zip(spots.indices)
        .filter { it.first != null }
        .filter { it.first?.plateNumber?.uppercase() == regNumber.uppercase() }
        .map { it.second + 1 }
    }
}