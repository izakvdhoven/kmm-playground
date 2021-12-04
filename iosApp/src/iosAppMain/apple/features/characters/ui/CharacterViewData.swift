import Foundation
import shared

public struct CharacterViewData: Identifiable {
    public let id: Int32
    let name: String
    let status: String
    let imageUrl: URL?
    
    public init(character: Character) {
        id = character.id
        name = character.name
        status = character.status
        imageUrl = URL(string: character.imageUrl)
    }
}
