package ru.sber.oop

open class Room(val name: String, val size: Int) {

    constructor(name: String): this(name,100)

    protected open val dangerLevel = 5

    private val monsterGoblin1: Monster = Goblin(name ="Goblin-warrior",
            description = "This one is small and grotesque, mischievous and greedy, especially for gold and jewelry.",
            healthPoints = 200,
            powerType = "Physical")

    fun description() = "Room: $name"

    open fun load() = monsterGoblin1.getSalutation() + "Nothing much to see here..."

    fun Monster.getSalutation():String{
        return "$name is screams that you must give him your gold and shinies"
    }
}

class TownSquare: Room("Town Square",1000){

    final override fun load() = "A huge and majestic marketplace with merchants selling a variety of goods"

    override val dangerLevel = super.dangerLevel-3

}

