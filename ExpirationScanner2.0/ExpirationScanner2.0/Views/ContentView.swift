//
//  ContentView.swift
//  ExpirationScanner2.0
//
//  Created by Darwin Castillo on 6/25/21.
//

import SwiftUI

struct ContentView: View {
    @State private var selection: Tab = .home
    
    enum Tab {
        case home
        case list
    }
    
    init() {
        UITabBar.appearance().barTintColor = .black
    }

    var body: some View {
            TabView() {
                Home()
                    .tabItem {
                        Image(systemName: "house.fill")
                            .renderingMode(.original)
                        Text("Home")
                    }
                    .tag(Tab.home)
                
                ProductList()
                    .tabItem {
                        Image(systemName: "list.bullet")
                            .renderingMode(.original)
                        Text("List")
                    }
                    .tag(Tab.list)

            }
            .accentColor(Color(.systemBlue))
            .preferredColorScheme(.dark)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            .environmentObject(ModelData())
    }
}
