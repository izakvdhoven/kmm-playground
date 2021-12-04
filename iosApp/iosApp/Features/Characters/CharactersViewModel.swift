import Foundation
import shared
import Combine

class CharactersViewModel: ObservableObject {
    private let fetcher: CharactersFetcher
    private let provider: CharactersProvider

    @Published private var isLoading: Bool = false
    @Published private var fetcherResult: FetcherResult? = nil
    @Published var characters: [CharacterViewData] = []

    private var charactersCancellable: AnyCancellable?

    init(fetcher: CharactersFetcher, provider: CharactersProvider) {
        self.fetcher = fetcher
        self.provider = provider
        
        provider.characters.collect(collector: Collector<[Character]?> { characters in
            guard let characters = characters else { return }
            self.characters = characters.map { character in
                CharacterViewData(character: character)
            }
        } as Kotlinx_coroutines_coreFlowCollector) { (unit, error) in
            // code which is executed if the Flow object completed
        }
    }

    func fetchCharacters() {
        isLoading = true
        fetcher.fetchCharacters(forcedRefresh: true) { fetcherResult, error in
            self.fetcherResult = fetcherResult
            self.isLoading = false
        }
    }
}

class Collector<T>: Kotlinx_coroutines_coreFlowCollector {

    let callback:(T) -> Void

    init(callback: @escaping (T) -> Void) {
        self.callback = callback
    }


    func emit(value: Any?, completionHandler: @escaping (KotlinUnit?, Error?) -> Void) {
        // do whatever you what with the emitted value
        callback(value as! T)

        // after you finished your work you need to call completionHandler to
        // tell that you consumed the value and the next value can be consumed,
        // otherwise you will not receive the next value
        //
        // i think first parameter can be always nil or KotlinUnit()
        // second parameter is for an error which occurred while consuming the value
        // passing an error object will throw a NSGenericException in kotlin code, which can be handled or your app will crash
        completionHandler(KotlinUnit(), nil)
    }
}

