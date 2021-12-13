//
//  ProductRow.swift
//  ExpirationScanner2.0
//
//  Created by Darwin Castillo on 6/1/21.
//

import SwiftUI

struct ProductRow: View {
    var product: Product

    var body: some View {
        HStack {
            product.imageCat
                .resizable()
                .frame(width: 50, height: 30)
            Text(product.name)

            Spacer()

            // Not needed will save for future implementation
            //if product.isFavorite {
               // Image(systemName: "star.fill")
                 //   .foregroundColor(.yellow)
            //}
        }
    }
}

struct ProductRow_Previews: PreviewProvider {
    static var products = ModelData().products

    static var previews: some View {
        Group {
            ProductRow(product: products[0])
        }
        .previewLayout(.fixed(width: 300, height: 70))
    }
}
