package ru.sber.oop

data class User(val name: String, val age: Long) {
    lateinit var city: String
    override fun equals(other: Any?): Boolean {
        if(other == null) return false
        if(javaClass != other.javaClass) return false
        other as User
        return (name == other.name && age == other.age && city == other.city)
    }
}

fun main() {
    val user1 = User("Alex", 13)
    val user2 = user1.copy(name="Andrew")
    user1.city = "Omsk"
    val user3 = user1.copy()
    user3.city = "Tomsk"
    println( user1.equals(user3) )
}