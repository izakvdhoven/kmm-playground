package za.co.izakvdhoven.kmmplayground.core.networking

interface ConnectivityHelper {
    val isConnected: Boolean
}

//TODO - Implement platform-specific reachability logic
class MockConnectivityHelper: ConnectivityHelper {
    override val isConnected: Boolean = true
}