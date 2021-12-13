//
//  Layout.swift
//  ExpirationScanner2.0
//
//  Created by Darwin Castillo on 6/24/21.
//


import Foundation
import SwiftUI

struct Section: Hashable, Decodable {
    let id: Int
    let type: String
    let title: String
    let subtitle: String
    let items: [Item]
}

struct Item: Hashable, Decodable {
    let id: Int
    let tagline: String
    let name: String
    let subheading: String
    let image: String
}

