var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
// Automated browser
var puppeteer = require('puppeteer');
// Get location of chrome executable 
location = require('chrome-location');
// Read external files
var fs = require('fs');
// HTTP request
var axios = require('axios');
var request = require('request');
// Pick contents of the axios/request results
var cheerio = require('cheerio');
// Some slight animations for CLI. 
var ora = require('ora');
var spinners = require('cli-spinners');
function supremeTask() {
    return __awaiter(this, void 0, void 0, function () {
        var desiredItemEndpoint, desiredItem, shopItems, itemURL, users, spinner;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    desiredItemEndpoint = [];
                    users = readUserBillingInfo();
                    spinner = ora("Retrieving Item\n").start();
                    return [4 /*yield*/, getProductURL()];
                case 1:
                    itemURL = _a.sent();
                    spinner.succeed('Item Retrieved');
                    /*
                     * await will only let the process proceed once the function returns a promise. If there is no
                     * promise the code will continue while the function is still processing.
                     * Will be used to run multiple tasks at once.
                     */
                    return [4 /*yield*/, task(itemURL)];
                case 2:
                    /*
                     * await will only let the process proceed once the function returns a promise. If there is no
                     * promise the code will continue while the function is still processing.
                     * Will be used to run multiple tasks at once.
                     */
                    _a.sent();
                    return [2 /*return*/];
            }
        });
    });
}
//============================================================================================
// Get the URL to the specified user product
function getProductURL() {
    return __awaiter(this, void 0, void 0, function () {
        var products, desiredItem;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, getUserItem()];
                case 1:
                    /*
                     * Promises / async / awaits are part of asychro coding
                     * **https://www.youtube.com/watch?v=DHvZLI7Db8E**
                     * **https://www.youtube.com/watch?v=V_Kr9OSfDeU**
                     */
                    desiredItem = _a.sent();
                    return [4 /*yield*/, loadProducts(desiredItem)];
                case 2:
                    products = _a.sent();
                    return [2 /*return*/, findItem(products[0], (desiredItem.itemName + desiredItem.itemColor))];
            }
        });
    });
}
// Find the user product from the list provided
function findItem(categoryItems, desiredItem) {
    for (var i = 0; i < categoryItems.length; i++) {
        if (categoryItems[i].itemName.toLowerCase().includes(desiredItem.toLowerCase())) {
            if (categoryItems[i].stock.toLowerCase() !== 'sold out') {
                return ('https://www.supremenewyork.com' + categoryItems[i].url);
            }
            else {
                // Keep looping until item in stock??
                console.log('Item is currently sold out!');
            }
        }
    }
    // Keep looping until task is cancelled??
    console.log('Waiting for item');
}
// Load all products from the site
function loadProducts(desiredItem) {
    var shopItems, categoryItems;
    var URL = 'https://www.supremenewyork.com/shop/all';
    shopItems = getStockInfoFromAll(URL);
    categoryItems = getStockInfoFromDesiredCategory(URL + '/' + desiredItem.itemCat);
    return Promise.all([categoryItems, shopItems]);
}
// Get user desired item
function getUserItem() {
    return new Promise(function (resolve, reject) {
        resolve({ itemCat: 'accessories', itemName: 'PS4 Multitool', itemColor: 'Red' });
    });
}
// Get map with key = item url and value = item sold out for all products
function getStockInfoFromAll(URL) {
    return new Promise(function (resolve, reject) {
        request(URL, function (error, response, html) {
            if (response.statusCode === 200) {
                var $_1 = cheerio.load(html);
                var currentStock_1 = [];
                $_1('.inner-article a').each(function (i, elem) {
                    currentStock_1.push({
                        url: $_1(elem).attr('href'),
                        stock: $_1(elem).text()
                    });
                });
                resolve(currentStock_1);
            }
        });
    });
}
// Get map with key = item url and value = item sold out for all products in specified category
function getStockInfoFromDesiredCategory(URL) {
    return new Promise(function (resolve, reject) {
        request(URL, function (error, response, html) {
            if (response.statusCode === 200) {
                var $_2 = cheerio.load(html);
                var currentStock_2 = [];
                $_2('.inner-article').each(function (i, elem) {
                    currentStock_2.push({
                        url: $_2(elem).find('a').attr('href'),
                        itemName: $_2(elem).find('.name-link').text(),
                        stock: $_2(elem).find('.sold_out_tag').text()
                    });
                });
                resolve(currentStock_2);
            }
        });
    });
}
// Open user JSON file and read/return its contents
function readUserBillingInfo() {
    /*
     * Retrieve contents of json file with fs.readFileSync() and use JSON.parse()
     * to convert the string into its correllated object
     */
    try {
        return JSON.parse(fs.readFileSync('./Profiles.json'));
    }
    catch (error) {
        console.log('Could not load user profiles:', error);
    }
}
//==============================================================================================
// Open a brower with the specified site (Puppeteer version)
function task(URL) {
    return __awaiter(this, void 0, void 0, function () {
        var spinner, page, user;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    spinner = ora('Running task').start();
                    return [4 /*yield*/, openBrowser(URL)];
                case 1:
                    page = _a.sent();
                    return [4 /*yield*/, getUser('User1')];
                case 2:
                    user = _a.sent();
                    return [4 /*yield*/, addToCart(page)];
                case 3:
                    _a.sent();
                    return [4 /*yield*/, checkout(page, user)];
                case 4:
                    _a.sent();
                    return [4 /*yield*/, page.browser().close()];
                case 5:
                    _a.sent();
                    spinner.succeed('Task Completed');
                    return [2 /*return*/, true];
            }
        });
    });
}
function checkout(page, user) {
    return __awaiter(this, void 0, void 0, function () {
        var spinner, listOfElements, userInfo, i;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    spinner = ora("Checking out\n").start();
                    listOfElements = ["order_billing_name", 'order_email', 'order_tel', 'bo',
                        'oba3', 'order_billing_zip', 'order_billing_city', 'rnsnckrn', 'orcer'];
                    userInfo = [user.firstName + ' ' + user.lastName, user.email, user.phoneNumber.number,
                        user.address.streetAddress, user.address.apt, user.address.postalCode, user.address.city,
                        user.cardInfo.number, user.cardInfo.cvv];
                    return [4 /*yield*/, page.waitForSelector('#order_billing_name')];
                case 1:
                    _a.sent();
                    for (i = 0; i < listOfElements.length; i++) {
                        try {
                            page.evaluateHandle("document.getElementById(\"" + listOfElements[i] + "\").value = \"" + userInfo[i] + "\";");
                        }
                        catch (_b) {
                            i--;
                        }
                    }
                    page.select('select#order_billing_state', user.address.state);
                    page.select('select#credit_card_month', user.cardInfo.expMonth);
                    page.select('select#credit_card_year', user.cardInfo.expYear);
                    return [4 /*yield*/, page.click('#terms-checkbox')];
                case 2:
                    _a.sent();
                    return [4 /*yield*/, page.click('input.button')];
                case 3:
                    _a.sent();
                    return [4 /*yield*/, delay(5000)];
                case 4:
                    _a.sent();
                    spinner.succeed('Copped!');
                    return [2 /*return*/];
            }
        });
    });
}
// Add item to cart
function addToCart(page) {
    return __awaiter(this, void 0, void 0, function () {
        var checkout, spinner, _a;
        return __generator(this, function (_b) {
            switch (_b.label) {
                case 0:
                    checkout = 'https://www.supremenewyork.com/checkout';
                    spinner = ora("Adding to cart\n").start();
                    return [4 /*yield*/, page.click('input.button')];
                case 1:
                    _b.sent();
                    return [4 /*yield*/, delay(50)];
                case 2:
                    _b.sent();
                    _b.label = 3;
                case 3:
                    _b.trys.push([3, 5, , 9]);
                    return [4 /*yield*/, page.click('.checkout')];
                case 4:
                    _b.sent();
                    return [3 /*break*/, 9];
                case 5:
                    _a = _b.sent();
                    _b.label = 6;
                case 6:
                    if (!(page.url() != checkout)) return [3 /*break*/, 8];
                    return [4 /*yield*/, page.goto(checkout)];
                case 7:
                    _b.sent();
                    return [3 /*break*/, 6];
                case 8: return [3 /*break*/, 9];
                case 9:
                    spinner.stop();
                    return [2 /*return*/];
            }
        });
    });
}
// New delay, page.waitFor is gonna be removed
function delay(x) {
    return new Promise(function (resolve) {
        setTimeout(function () {
            resolve(x);
        }, x);
    });
}
function getUser(userID) {
    var user = JSON.parse(fs.readFileSync('Profiles.json'));
    for (var i = 0; i < user.length; i++) {
        if (user[i].userID === userID) {
            return user[i];
        }
    }
}
// Open browser Instance
function openBrowser(URL) {
    return __awaiter(this, void 0, void 0, function () {
        var br, browser, page;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    br = ora("Opening Browser\n").start();
                    return [4 /*yield*/, puppeteer.launch({
                            headless: false,
                            executablePath: location,
                            defaultViewport: null
                        })];
                case 1:
                    browser = _a.sent();
                    return [4 /*yield*/, browser.newPage()];
                case 2:
                    page = _a.sent();
                    return [4 /*yield*/, page.goto(URL)];
                case 3:
                    _a.sent();
                    br.stop();
                    return [2 /*return*/, page];
            }
        });
    });
}
supremeTask();
//# sourceMappingURL=Supreme.js.map