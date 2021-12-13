//
//  productList.swift
//  ExpirationScanner2.0
//
//  Created by Darwin Castillo on 6/1/21.
//

import SwiftUI

struct ProductList: View {
    @EnvironmentObject var modelData: ModelData
    
    // Not need rn will save for future implementation
    @State private var showFavoritesOnly = false

    var filteredProducts: [Product] {
        modelData.products.filter { product in
            (!showFavoritesOnly || !product.isFavorite)
        }
    }

    var body: some View {
        NavigationView {
            List {
                // Not need rn will save for future implementation
                Toggle(isOn: $showFavoritesOnly) {
                    Text("Favorites only")
                }

                ForEach(filteredProducts) { product in
                    NavigationLink(destination: ProductDetail(product: product)) {
                        ProductRow(product: product)
                    }
                }
            }
            .navigationTitle("List of Products")
        }
        
    }
}

struct ProductList_Previews: PreviewProvider {
    static var previews: some View {
        ProductList()
            .environmentObject(ModelData())
    }
}
