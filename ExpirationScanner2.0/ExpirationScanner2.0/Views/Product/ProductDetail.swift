//
//  ProductDetail.swift
//  ExpirationScanner2.0
//
//  Created by Darwin Castillo on 6/1/21.
//

import SwiftUI
import UIKit
import Foundation


struct ProductDetail: View {
    @EnvironmentObject var modelData: ModelData
    var product: Product
    let julianConv = JulianDate()
    
    // Calculate the index of the input
    var productIndex: Int {
        modelData.products.firstIndex(where: { $0.id == product.id })!
    }
    
    
    var body: some View {
        let currDate = Date()
        
        let components = Calendar.current.dateComponents(
                            [
                                Calendar.Component.year, Calendar.Component.month, Calendar.Component.day
                            ]
                            , from: currDate)
        
        let currMonth = components.month
        let currYear = components.year
        let currDay = components.day
        
        let nDay = julianConv.convertToJulian(month: currMonth!, day: currDay!)
        
        // Format for the current date based on the product format
        let dFormat = getFormat(month: currMonth!, day: currDay!, year: currYear!, nDay: nDay)
        
        ZStack {
            VStack {
                // Image
                VStack {
                    // Display image of the product
                    ProductImage(imageName: product.imageName, product: product).environmentObject(modelData)
                      
                    // Display the shelf life length and format of the product
                    Text("Shelf Life: \(product.sLife.length)" + " \(product.sLife.format)")
                        .font(.subheadline)
                        .bold()
                        .padding(.top, 5)
                        
                    // Seperator
                    Rectangle()
                        .fill(Color(UIColor.white))
                        .padding(.horizontal, 10)
                        .frame(height: 1)
                        .padding(.vertical, 15)
                        .opacity(0.5)
                
                }
                
// Example window using current date information =========================================================================================================
                
                VStack {
                    ZStack {
                        
                        // Does nothing, used for the gray box behind the main box.
                        HStack {
                            VStack {
                                if(product.expFormat == "Day") {
                                    Text("Example Code: \(dFormat)")
                                        .bold()

                                    Text("Month: \(julianConv.getLMonth(month: currMonth!))")
                                        .bold()
                                    
                                    Text("\(product.expFormat): \(getDay(day: currDay!, nDay: nDay))")
                                        .bold()
                                    
                                    Text("Year: \(currYear! - 2020)")
                                        .bold()
                                } else {
                                    if(product.expFormat == "Ignored") {
                                        let igFormat = String(currYear!).substring(fromIndex: 3)
                                        let igDateFormat = String(dFormat).substring(fromIndex: 1)
                                        
                                        Text("Example Code: L\(igDateFormat)")
                                            .bold()
                                        
                                        Text("Ignore: L")
                                            .bold()
                                        
                                        Text("Year: \(igFormat)")
                                            .bold()

                                        Text("Julian Day: \(getDay(day: currDay!, nDay: nDay))")
                                            .bold()
                                    } else {
                                        Text("Example Code: \(dFormat)")
                                            .bold()
                                        
                                        Text("Year: \(currYear! - 2000)")
                                            .bold()

                                        Text("\(product.expFormat): \(getDay(day: currDay!, nDay: nDay))")
                                            .bold()
                                    }
                                }
                            }
                            .padding(.vertical, 10)
                                
                            Rectangle().fill(Color(UIColor.white))
                                .frame(width: 1, height: 65)
                                .padding(.horizontal, 5)
                                
                            Text(currDate, style: .date)
                                .bold()
                        }
                        .padding(.horizontal, 16)
                        .background(Color(UIColor.darkGray))
                        .cornerRadius(5)
                        .offset(x: 1.5, y: 2)
                        
                        // Box in front for example code
                        HStack {
                            VStack {
                                // Different formats for the different code layout
                                // Format is "Day"
                                if(product.expFormat == "Day") {
                                    Text("Example Code: \(dFormat)")
                                        .bold()

                                    Text("Month: \(julianConv.getLMonth(month: currMonth!))")
                                        .bold()
                                    
                                    Text("\(product.expFormat): \(getDay(day: currDay!, nDay: nDay))")
                                        .bold()
                                    
                                    Text("Year: \(currYear! - 2020)")
                                        .bold()
                                } else {
                                    // Format is "Ignored" otherwise is "Julian Day"
                                    if(product.expFormat == "Ignored") {
                                        let igFormat = String(currYear!).substring(fromIndex: 3)
                                        let igDateFormat = String(dFormat).substring(fromIndex: 1)
                                        
                                        Text("Example Code: L\(igDateFormat)")
                                            .bold()
                                        
                                        Text("Ignore: L")
                                            .bold()
                                        
                                        Text("Year: \(igFormat)")
                                            .bold()

                                        Text("Julian Day: \(getDay(day: currDay!, nDay: nDay))")
                                            .bold()
                                    } else {
                                        Text("Example Code: \(dFormat)")
                                            .bold()
                                        
                                        Text("Year: \(currYear! - 2000)")
                                            .bold()

                                        Text("\(product.expFormat): \(getDay(day: currDay!, nDay: nDay))")
                                            .bold()
                                    }
                                }
                            }
                            .padding(.vertical, 10)
                                
                            // Separator
                            Rectangle().fill(Color(UIColor.white))
                                .frame(width: 1, height: 65)
                                .padding(.horizontal, 5)
                            
                            // Display current day
                            Text(currDate, style: .date)
                                .bold()
                        }
                        .padding(.horizontal, 15)
                        .background(Color(.systemBlue))
                        .cornerRadius(5)
                    }
                    
                    // Separator
                    Rectangle()
                        .fill(Color(UIColor.white))
                        .padding(.horizontal, 10)
                        .frame(height: 1)
                        .padding(.vertical, 15)
                        .opacity(0.5)
                    
// Autocalculate window =====================================================================================================================
                    
                    var exp = expDate()
                    
                    let nJDate = exp.determineExpDate(length: product.sLife.length, format: product.sLife.format, currJDate: nDay, currYear: currYear!, expFormat: product.expFormat)

                    ZStack {
                        Text("Expired if manufactured before")
                            .font(.title2)
                            .bold()
                            .position(x: 170, y: 10.0)
                        
                        // Expiration date, different formats based on the product format
                        ZStack {
//                          "Day" format
                            if(product.expFormat == "Day") {
                                // Does nothing, used for the gray box behind the main box.
                                VStack(alignment: .center) {
                                    Text(nJDate)
                                        .font(.largeTitle)
                                        .bold()
                                        .kerning(10)
                                        .padding(.leading, 10)
                                    
                                    Rectangle()
                                        .fill(Color(UIColor.white))
                                        .frame(width: 100, height: 1)
                                        .padding(.vertical, 5)
                                        .opacity(0.5)
                                    
                                    Text(exp.revert())
                                        .font(.footnote)
                                        .bold()
                                }
                                .padding()
                                .padding(.horizontal, 1.25)
                                .background(Color(UIColor.darkGray))
                                .cornerRadius(5)
                                .offset(x: 1.5, y: 52)
                                
                                
                                VStack {
                                    Text(nJDate)
                                        .font(.largeTitle)
                                        .bold()
                                        .kerning(10)
                                        .padding(.leading, 10)
                                    
                                    Rectangle()
                                        .fill(Color(UIColor.white))
                                        .frame(width: 100, height: 1)
                                        .padding(.vertical, 5)
                                        .opacity(0.5)
                                        
                                    Text(exp.revert())
                                        .font(.footnote)
                                        .bold()
                                }
                                .padding()
                                .background(Color(.systemBlue))
                                .cornerRadius(5)
                                .offset(y: 50)
                            } else {
//                              "Ignored" Format otherwise is "Julian Day"
                                
                                    // Does nothing, used for the gray box behind the main box.
                                    VStack(alignment: .center) {
                                        Text(nJDate)
                                            .font(.largeTitle)
                                            .bold()
                                            .kerning(10)
                                            .padding(.leading, 10)
                                        
                                        Rectangle()
                                            .fill(Color(UIColor.white))
                                            .frame(width: 100, height: 1)
                                            .padding(.vertical, 5)
                                            .opacity(0.5)
                                        
                                        Text(exp.revert())
                                            .font(.footnote)
                                            .bold()
                                    }
                                    .padding()
                                    .padding(.horizontal, 1.25)
                                    .background(Color(UIColor.darkGray))
                                    .cornerRadius(5)
                                    .offset(x: 1.5, y: 52)
                                    
                                    VStack {
                                        Text(nJDate)
                                            .font(.largeTitle)
                                            .bold()
                                            .kerning(10)
                                            .padding(.leading, 10)
                                        
                                        Rectangle()
                                            .fill(Color(UIColor.white))
                                            .frame(width: 100, height: 1)
                                            .padding(.vertical, 5)
                                            .opacity(0.5)
                                            
                                        Text(exp.revert())
                                            .font(.footnote)
                                            .bold()
                                    }
                                    .padding()
                                    .background(Color(.systemBlue))
                                    .cornerRadius(5)
                                    .offset(y: 50)
                                
                               
                            }
                        }
                    }
                }
                
                
            }
            .frame(minWidth: 0, idealWidth: 100, maxWidth: .infinity, minHeight: 0, idealHeight: 100, maxHeight: .infinity, alignment: .center)
            .offset(y: -75)
            
        }
        .preferredColorScheme(.dark)
    }
    
    func getFormat(month: Int, day: Int, year: Int, nDay: Int) -> String {
        var format = "\((year - 2000).description)\(getDay(day: day, nDay: nDay))"
        
        if(product.expFormat == "Day") {
            format = julianConv.convertToReg(month: month, day: day, year: year)
        }
        
        return format
    }
    
    // Get the format of the day specified
    func getDay(day: Int, nDay: Int) -> String {
        var rDay = "\(nDay)"
        
        // Differernt day format based on expiration format
        if(product.expFormat == "Day") {
            if(day < 10) {
                rDay = "0\(day)"
            } else {
                rDay = "\(day)"
            }
        } else {
            if(nDay < 10) {
                rDay = "00\(nDay)"
            } else {
                if(nDay < 100) {
                    rDay = "0\(nDay)"
                }
            }
        }
        
        return rDay
    }
}

// Expiration date for the product
struct expDate {
    var daysInMonths = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    var daysInMonthsL = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    let months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]
    let lMonths = ["A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M"]
    var nJulian = 0
    var nYear = 0
    var jDate = JulianDate()
    
    // Determine the expiration date to be displayed
    mutating func determineExpDate(length: Int, format: String, currJDate: Int, currYear: Int, expFormat: String) -> String {
        nYear = currYear
        
        // If the product shelf life is in years, determine if the year is a leap year. If it is, subtract
        // 366, otherwise sutract 365. If the product shelf life is not in years, calculate the new
        // Julian date normally.
        if(format == "year") {
            if(jDate.isLeap(year: nYear)) {
                nJulian = currJDate - 366
            } else {
                nJulian = currJDate - 365
            }
        } else {
            if(format == "months") {
                var nLength = length
                
                nJulian = currJDate
                
                if(jDate.isLeap(year: nYear)) {
                    while(nLength > 0) {
                        nJulian = nJulian - daysInMonthsL[nLength - 1];
                        
                        nLength = nLength - 1
                    }
                } else {
                    while(nLength > 0) {
                        nJulian = nJulian - daysInMonths[nLength - 1];
                        
                        nLength = nLength - 1
                    }
                }
            } else {
                nJulian = currJDate - length
            }
        }
        
        // If the new julian date is less than 0, that means the expiration date is in the past year.
        if(nJulian <= 0) {
            // reset the year to the previous one
            nYear = currYear - 1
            
            // If prevous year was a leap year then add that years day total to the new julian expiration value.
            if(jDate.isLeap(year: nYear)) {
                nJulian += 366
            } else {
                nJulian += 365
            }
        }
        
        var ret = "\(nYear - 2000)\(nJulian)"
        
        // If the product expiration format if Day change the return format
        if(expFormat == "Day") {
            let normal = jDate.convertToNormal(jDay: nJulian)
            var nDate = "\(normal[1])"
            
            if(normal[1] < 10) {
                nDate = "0\(normal[1])"
            }
            ret = "\(lMonths[normal[0] - 1])" + "\(nDate)" + "\(nYear - 2020)"
        } else {
            if(expFormat == "Ignored") {
                // If the new julian number is less than 10 or 100 fix the format.
                if(nJulian < 10) {
                    ret = "L\(nYear - 2000)00\(nJulian)"
                } else {
                    if(nJulian < 100) {
                        ret = "L\(nYear - 2000)0\(nJulian)"
                    } else {
                        ret = "L\(String(nYear).substring(fromIndex: 3))\(nJulian)"
                    }
                }
            } else {
                // If the new julian number is less than 10 or 100 fix the format.
                if(nJulian < 10) {
                    ret = "\(nYear - 2000)00\(nJulian)"
                } else {
                    if(nJulian < 100) {
                        ret = "\(nYear - 2000)0\(nJulian)"
                    }
                }
            }
        }
        
        return ret
    }
    
    // Revert the expiration code to the actual date it represents
    func revert() -> String {
        let convert = jDate.convertToNormal(jDay: nJulian)
        
        return ("\(months[convert[0] - 1])" + " \(convert[1]), \(nYear)")
    }
}


// Allow customization of individual corners
extension View {
    func cornerRadius(_ radius: CGFloat, corners: UIRectCorner) -> some View {
        clipShape(RoundedCorner(radius: radius, corners: corners))
    }
    
    func julian(year: Int, month: Int, day: Int) -> JulianDate {
        return JulianDate()
    }
}

struct RoundedCorner: Shape {
    var radius: CGFloat = .infinity
    var corners: UIRectCorner = .allCorners
    
    func path(in rect: CGRect) -> Path {
        let path = UIBezierPath(roundedRect: rect, byRoundingCorners: corners, cornerRadii: CGSize(width: radius, height: radius))
        
        
        return Path(path.cgPath)
    }
}

struct JulianDate {
    var daysInMonths = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    let months = ["A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M"]
    
    mutating func isLeap(year: Int) -> Bool {
        if(year == 2020) {
            daysInMonths[1] = 29
            
            return true
        }
    
        return false
    }
    
    func convertToJulian(month: Int, day: Int) -> Int {
        var julian = 0;
        
        for cnt in 0...(month - 2) {
            julian += daysInMonths[cnt]
        }
        
        julian += day
        
        return julian
    }
    
    func convertToReg(month: Int, day: Int, year: Int) -> String {
        var nDay = "\(day)"
        
        if(day < 10) {
            nDay = "0\(nDay)"
        }
        
        return ("\(months[month - 1])" + nDay + "\(year - 2020)")
    }
    
    func getLMonth(month: Int) -> String {
        return "\(months[month - 1])"
    }
    func convertToNormal(jDay: Int) -> [Int] {
        var currMonth = 0
        var cnt = 0
        var julian = jDay
        
        while(julian > 0) {
            julian -= daysInMonths[currMonth]
            
            cnt += 1
            currMonth += 1
        }
        
        julian += daysInMonths[currMonth - 1]
        
        return [currMonth, julian]
    }
}

extension String {

    var length: Int {
        return count
    }

    subscript (i: Int) -> String {
        return self[i ..< i + 1]
    }

    func substring(fromIndex: Int) -> String {
        return self[min(fromIndex, length) ..< length]
    }

    func substring(toIndex: Int) -> String {
        return self[0 ..< max(0, toIndex)]
    }

    subscript (r: Range<Int>) -> String {
        let range = Range(uncheckedBounds: (lower: max(0, min(length, r.lowerBound)),
                                            upper: min(length, max(0, r.upperBound))))
        let start = index(startIndex, offsetBy: range.lowerBound)
        let end = index(start, offsetBy: range.upperBound - range.lowerBound)
        return String(self[start ..< end])
    }
}

#if DEBUG
struct ProductDetail_Previews: PreviewProvider {
    static var products = ModelData().products
    static let modelData = ModelData()
    
    static var previews: some View {
        ProductDetail(product: products[7])
            .environmentObject(modelData)
    }
}
#endif
