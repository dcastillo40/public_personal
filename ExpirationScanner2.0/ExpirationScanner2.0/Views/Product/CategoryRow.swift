//
//  CategoryRow.swift
//  ExpirationScanner2.0
//
//  Created by Darwin Castillo on 6/25/21.
//

import SwiftUI

struct CategoryRow: View {
    var categoryName: String
    var products: [Product]
    
    let rows = [
            GridItem(.flexible()),
            GridItem(.flexible())
        ]
    
    
    
    var body: some View {
        VStack() {
            Text(categoryName)
                .font(.title2)
                .bold()
                .frame(minWidth: 0, idealWidth: .infinity, maxWidth: .infinity, minHeight: 0, idealHeight: 30, maxHeight: 30, alignment: .center)
                .background(Color(.systemBlue))
                .cornerRadius(5)
                .padding(.leading, 30)
                .padding(.trailing, 30)
                
            
            ScrollView(.horizontal, showsIndicators: false) {
                LazyHGrid(rows: rows, alignment: .center) {
                    ForEach(products) { product in
                        NavigationLink(
                            destination: ProductDetail(product: product)) {
                            ProductItem(product: product)
                        }
                    }
                }
            }
            .frame(height: 190)
            .padding(.leading, 15)
        }
        .padding(.top, 15)
        .padding(.bottom, 15)
    }
}

struct CategoryRow_Previews: PreviewProvider {
    static var products = ModelData().products
    
    static var previews: some View {
        CategoryRow(
            categoryName: products[0].category.rawValue,
            products: Array(products)
            
        )
    }
}
