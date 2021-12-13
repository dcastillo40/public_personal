//
//  Home.swift
//  ExpirationScanner2.0
//
//  Created by Darwin Castillo on 6/25/21.
//

import SwiftUI

struct Home: View {
    @EnvironmentObject var modelData: ModelData
    
    var body: some View {
        
        NavigationView {
            List {
                ForEach(modelData.categories.keys.sorted(), id: \.self) { key in
                    CategoryRow(categoryName: key, products: modelData.categories[key]!)
                }
                .listSeparatorNone()
            }
            .navigationTitle("Home")
        }
        
        
    }
}

struct ListSeparatorNone: ViewModifier {

    var backgroundColor: Color = Color(.systemBackground)
    
    func body(content: Content) -> some View {
        content
            .listRowInsets(EdgeInsets(top: -1, leading: 0, bottom: 0, trailing: 0))
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .leading)
            .background(backgroundColor)
    }
}

extension View {
    func listSeparatorNone(backgroundColor: Color = Color(.systemBackground)) -> some View {
        self.modifier(ListSeparatorNone(backgroundColor: backgroundColor))
    }
}

struct Home_Previews: PreviewProvider {
    static var previews: some View {
        Home().environmentObject(ModelData())
    }
}
