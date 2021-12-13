//
//  NinoApp.swift
//  ExpirationScanner2.0
//
//  Created by Darwin Castillo on 7/8/21.
//

import SwiftUI

@main
struct NinoApp: App {
    @StateObject private var modelData = ModelData()
    
    var body: some Scene {
        WindowGroup {
            ContentView()
                .environmentObject(modelData)
        }
    }
}

