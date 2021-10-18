package za.co.izakvdhoven.kmmplayground

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}