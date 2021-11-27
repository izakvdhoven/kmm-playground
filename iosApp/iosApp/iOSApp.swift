import SwiftUI

@main
struct iOSApp: App {
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}

	init() {
	    _ = DI { _ in }

	    let helloService = HelloServiceImpl()
	    print(helloService.hello()
	}
}