//
//  ProductItem.swift
//  ExpirationScanner2.0
//
//  Created by Darwin Castillo on 6/25/21.
//

import SwiftUI

struct ProductItem: View {
    var product: Product
    
    var body: some View {
        VStack() {
            if(product.imageCategory == "geneseeLogo") {
                product.imageCat
                    .renderingMode(.original)
                    .resizable()
                    .frame(width: 100, height: 60)
            } else {
                if(product.imageCategory == "labattLogo") {
                    product.imageCat
                        .renderingMode(.original)
                        .resizable()
                        .frame(width: 100, height: 60)
                        .padding(.trailing, 5)
                } else {
                    if(product.imageCategory == "whiteClawLogo") {
                        product.imageCat
                            .renderingMode(.original)
                            .resizable()
                            .frame(width: 95, height: 95)
                            .padding(.top, 10)
                            .padding(.bottom, 5)
                    } else {
                        if(product.imageCategory == "seagramsLogo") {
                            product.imageCat
                                .renderingMode(.original)
                                .resizable()
                                .frame(width: 120, height: 75)
                        } else {
                            if(product.imageCategory == "blueseltzerLogo") {
                                product.imageCat
                                    .renderingMode(.original)
                                    .resizable()
                                    .padding()
                                    .frame(width: 125, height: 125)
                            } else {
                                if(product.imageCategory == "smirnoffLogo") {
                                    product.imageCat
                                        .renderingMode(.original)
                                        .resizable()
                                        .frame(width: 100, height: 50)
                                } else {
                                    if(product.imageCategory == "mikesLogo") {
                                        product.imageCat
                                            .renderingMode(.original)
                                            .resizable()
                                            .frame(width: 110, height: 110)
                                            .padding(.top, 10)
                                    } else {
                                        if(product.imageCategory == "budlightSeltzerLogo") {
                                            product.imageCat
                                                .renderingMode(.original)
                                                .resizable()
                                                .padding()
                                                .frame(width: 125, height: 115)
                                        } else {
                                            if(product.imageCategory == "ultraLogo") {
                                                product.imageCat
                                                    .renderingMode(.original)
                                                    .resizable()
                                                    .frame(width: 110, height: 75)
                                                    .padding(.trailing, 5)
                                            } else {
                                                if(product.imageCategory == "lingLogo") {
                                                    product.imageCat
                                                        .renderingMode(.original)
                                                        .resizable()
                                                        .frame(width: 120, height: 55)
                                                } else {
                                                    product.imageCat
                                                        .renderingMode(.original)
                                                        .resizable()
                                                        .frame(width: 125, height: 70)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

struct ProductItem_Previews: PreviewProvider {
    static var previews: some View {
        ProductItem(product: ModelData().products[13])
    }
}
