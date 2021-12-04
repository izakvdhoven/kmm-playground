import SwiftUI
import shared

public struct CharactersContentView: View {
    @StateObject private var viewModel = CharactersViewModel(
            fetcher: DI.Dependencies().charactersFetcher,
            provider: DI.Dependencies().charactersProvider
    )

    public var body: some View {
        NavigationView{
            List(viewModel.characters){ character in
                NavigationLink(
                        destination: DetailView(),
                        label: {
                            Text(character.name)
                        }
                )
            }.navigationTitle("Characters")
        }
        Button("Refresh") {
            fetchCharacters()
        }
                .onAppear {
                    fetchCharacters()
                }
    }

    private func fetchCharacters() {
        viewModel.fetchCharacters()
    }
}

struct DetailView: View {
    var body: some View {
        Text("Character Details")
    }
}

struct CharactersContentView_Previews: PreviewProvider {
    static var previews: some View {
        CharactersContentView()
    }
}
