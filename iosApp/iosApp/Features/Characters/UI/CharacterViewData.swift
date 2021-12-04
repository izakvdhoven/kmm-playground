import Foundation
import shared

struct CharacterViewData: Identifiable {
    let id: Int32
    let name: String
    let status: String
    let imageUrl: URL?
    
    init(character: Character) {
        self.id = character.id
        self.name = character.name
        self.status = character.status
        self.imageUrl = URL(string: character.imageUrl)
    }
}
