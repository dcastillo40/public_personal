//
//  Product.swift
//  ExpirationScanner2.0
//
//  Created by Darwin Castillo on 6/1/21.
//

import Foundation
import SwiftUI

struct Product: Hashable, Codable, Identifiable {
    var name: String
    var id: Int
    var expFormat: String
    var sLife: sLife
    var isFavorite: Bool
    
    var category: Category
    
    enum Category: String, CaseIterable, Codable {
        case beer = "Beer"
        case seltzer = "Seltzer"
        case candy = "Candy"
        case food = "Food"
        case other = "Other"
    }
    
    var imageName: String
    var image: Image {
        Image(imageName)
    }
    
    var imageCategory: String
    var imageCat: Image {
        Image(imageCategory)
    }
    
    
}

struct sLife: Hashable, Codable {
    var length: Int
    var format: String
}
