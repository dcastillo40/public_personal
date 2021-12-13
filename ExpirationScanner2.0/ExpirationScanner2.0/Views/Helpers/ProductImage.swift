//
//  ProductImage.swift
//  ExpirationScanner2.0
//
//  Created by Darwin Castillo on 6/1/21.
//

import SwiftUI

struct ProductImage: View {
    var imageName: String
    @EnvironmentObject var modelData: ModelData
    var product: Product
    //@State private var moving = false
    @State private var tilting = false
    //@State private var jerking = false
    
    var foreverAnimation: Animation {
            Animation.linear(duration: 1)
                .repeatForever()
        
        }
    
    var productIndex: Int {
        modelData.products.firstIndex(where: { $0.id == product.id })!
    }
    
    var body: some View {
        if(imageName == "naturalIce" || imageName == "labattBlue" || imageName == "geneseeLight") {
            Image(imageName)
                .resizable()
                .padding(.horizontal, 25.0)
                .frame(width: 140, height: 175)
                .padding(.vertical, 40)
                .shadow(color: .white, radius: 20)
        } else {
            if(imageName == "budIce") {
                Image(imageName)
                    .resizable()
                    .padding(.horizontal, -25.0)
                    .frame(width: 250, height: 350)
                    .padding(.vertical, -70)
                    .shadow(color: .white, radius: 20)
            } else {
                if(imageName == "whiteClaw" || imageName == "blueSeltzer" || imageName == "ultra") {
                    Image(imageName)
                        .resizable()
                        .padding(.horizontal, 25.0)
                        .frame(width: 140, height: 200)
                        .padding(.vertical, 20)
                        .shadow(color: .white, radius: 20)
                } else {
                    if(imageName == "smirnoffCan"  || imageName == "mikes") {
                        Image(imageName)
                            .resizable()
                            .padding(.horizontal, -75.0)
                            .frame(width: 130, height: 190)
                            .padding(.vertical, 20)
                            .shadow(color: .white, radius: 20)
                    } else {
                        if(imageName == "seagrams") {
                            Image(imageName)
                                .resizable()
                                .padding(.horizontal, -25.0)
                                .frame(width: 300, height: 275)
                                .padding(.vertical, -25)
                                .shadow(color: .white, radius: 20)
                        } else {
                            if(imageName == "budlightSeltzer" ) {
                                Image(imageName)
                                    .resizable()
                                    .padding(.horizontal, 20.0)
                                    .padding(.vertical, 10.0)
                                    .frame(width: 170, height: 230)
                                    .shadow(color: .white, radius: 20)
                            } else {
                                if(imageName == "ling") {
                                    Image(imageName)
                                        .resizable()
                                        .padding(.horizontal, 20.0)
                                        .padding(.vertical, 10.0)
                                        .frame(width: 150, height: 200)
                                        .shadow(color: .white, radius: 20)
                                } else {
                                    Image(imageName)
                                        .resizable()
                                        .padding(.horizontal, 25.0)
                                        .frame(width: 200, height: 250)
                                        .shadow(color: .white, radius: 20)
                                        .rotationEffect((imageName == "naturalLight") ? (Angle(degrees: tilting ? 0 : 5)) : (Angle(degrees: tilting ? 0 : 0)))
                                }
                            }
                        }
                    }
                }
            }
        }
        
        // Not needed will save for future implementation
        //FavoriteButton(isSet: $modelData.products[productIndex].isFavorite)
            //.padding(.top, 2)
    }
    
}

struct ProductImage_Previews: PreviewProvider {
    static var products = ModelData().products
    
    static var previews: some View {
        Group {
            ProductImage(imageName: products[13].imageName, product: products[13]).environmentObject(ModelData())
        }
    }
}
