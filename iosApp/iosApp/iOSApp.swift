import SwiftUI
import shared

@main
struct iOSApp: App {
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}

	init() {
	    _ = DI(enableNetworkLogs: true) { _ in }
	}
}
