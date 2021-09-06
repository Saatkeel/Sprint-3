package ru.sber.oop

import kotlin.random.Random

interface Fightable {

    val powerType: String
    var healthPoints: Int
    val damageRoll: Int
        get() = Random.nextInt()

    fun attack(opponent: Fightable): Int

}

class Player(val name: String,
             val isBlessed: Boolean,
             override var healthPoints: Int,
             override val powerType: String): Fightable {

    override fun attack(opponent: Fightable): Int {
        var damage = damageRoll
        if(isBlessed)
            damage *= 2
        opponent.healthPoints -= damage
        return damage
    }

}

abstract class Monster(val name: String,
                       val description: String,
                       override var healthPoints: Int,
                       override val powerType: String): Fightable {

        override fun attack(opponent: Fightable): Int {
        val damage = damageRoll
        opponent.healthPoints -= damage
        return damage
    }

}

class Goblin(name: String, description: String, powerType: String, healthPoints: Int) :
        Monster(name, description,  healthPoints ,powerType){

        override val damageRoll: Int
            get() = super.damageRoll / 2

}
