// Automated browser
const puppeteer = require('puppeteer');

 // Get location of chrome executable 
location = require('chrome-location'); 

// Read external files
const fs = require('fs');

// HTTP request
const axios = require('axios');
const request = require('request');

// Pick contents of the axios/request results
const cheerio = require('cheerio');

// Some slight animations for CLI. 
const ora = require('ora');
const spinners = require('cli-spinners');

async function supremeTask() { 
    var desiredItemEndpoint = [];
    let desiredItem;
    let shopItems;
    let itemURL;
    // Set the user billing info
    let users = readUserBillingInfo();
    

// Browser Mode
//==========================================================================================//
    const spinner = ora("Retrieving Item\n").start();

    itemURL = await getProductURL();

    spinner.succeed('Item Retrieved');

    /*
     * await will only let the process proceed once the function returns a promise. If there is no
     * promise the code will continue while the function is still processing.
     * Will be used to run multiple tasks at once.
     */

    await task(itemURL);

    

//==========================================================================================//



}


//============================================================================================
// Get the URL to the specified user product
async function getProductURL() {
    let products, desiredItem;
    /*
     * Promises / async / awaits are part of asychro coding
     * **https://www.youtube.com/watch?v=DHvZLI7Db8E**
     * **https://www.youtube.com/watch?v=V_Kr9OSfDeU**
     */
    desiredItem = await getUserItem();

    products = await loadProducts(desiredItem);

    return findItem(products[0], (desiredItem.itemName + desiredItem.itemColor))
}

// Find the user product from the list provided
function findItem(categoryItems, desiredItem) {
    for (let i = 0; i < categoryItems.length; i++) {
        if (categoryItems[i].itemName.toLowerCase().includes(desiredItem.toLowerCase())) {
            if (categoryItems[i].stock.toLowerCase() !== 'sold out') {
                return ('https://www.supremenewyork.com' + categoryItems[i].url);
            } else {
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
    let shopItems, categoryItems;
    var URL = 'https://www.supremenewyork.com/shop/all';

    shopItems = getStockInfoFromAll(URL);
    categoryItems = getStockInfoFromDesiredCategory(URL + '/' + desiredItem.itemCat);

    return Promise.all([categoryItems, shopItems]);
}

// Get user desired item
function getUserItem() {
    return new Promise((resolve, reject) => {
        resolve({ itemCat: 'accessories', itemName: 'PS4 Multitool', itemColor: 'Red' });
    });
}

// Get map with key = item url and value = item sold out for all products
function getStockInfoFromAll(URL) {
    return new Promise((resolve, reject) => {
            request(URL, (error, response, html) => {
                if (response.statusCode === 200) {
                    const $ = cheerio.load(html);

                    const currentStock = [];
                    
                    $('.inner-article a').each((i, elem) => {
                        currentStock.push({
                            url: $(elem).attr('href'),
                            stock: $(elem).text()
                        })
                    });

                    resolve(currentStock);
                }
            });
    });
}

// Get map with key = item url and value = item sold out for all products in specified category
function getStockInfoFromDesiredCategory(URL) {
    return new Promise((resolve, reject) => {
            request(URL, (error, response, html) => {
                if (response.statusCode === 200) {
                    const $ = cheerio.load(html);

                    const currentStock = [];

                    $('.inner-article').each((i, elem) => {
                        currentStock.push({
                            url: $(elem).find('a').attr('href'),
                            itemName: $(elem).find('.name-link').text(),
                            stock: $(elem).find('.sold_out_tag').text()
                        })
                    });

                    resolve(currentStock);
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
    } catch (error) {
        console.log('Could not load user profiles:', error);
    }
}

//==============================================================================================
// Open a brower with the specified site (Puppeteer version)
async function task(URL) {
    const spinner = ora('Running task').start();

    const page = await openBrowser(URL);
    var user = await getUser('User1');
    await addToCart(page);
    await checkout(page, user);

    await page.browser().close();

    spinner.succeed('Task Completed');

    return true;
} 

async function checkout(page, user) {
    const spinner = ora("Checking out\n").start();

    var listOfElements = ["order_billing_name", 'order_email', 'order_tel', 'bo',
        'oba3', 'order_billing_zip', 'order_billing_city', 'rnsnckrn', 'orcer'];

    var userInfo = [user.firstName + ' ' + user.lastName, user.email, user.phoneNumber.number,
        user.address.streetAddress, user.address.apt, user.address.postalCode, user.address.city,
        user.cardInfo.number, user.cardInfo.cvv];

    await page.waitForSelector('#order_billing_name');

    for (let i = 0; i < listOfElements.length; i++) {
        try {
            page.evaluateHandle(`document.getElementById("${listOfElements[i]}").value = "${userInfo[i]}";`);
        } catch {
            i--;
        }
    }

    page.select('select#order_billing_state', user.address.state);
    page.select('select#credit_card_month', user.cardInfo.expMonth);
    page.select('select#credit_card_year', user.cardInfo.expYear);

    await page.click('#terms-checkbox');

    await page.click('input.button');
    
    await delay(5000);

    spinner.succeed('Copped!');
}

// Add item to cart
async function addToCart(page) {
    var checkout = 'https://www.supremenewyork.com/checkout';

    const spinner = ora("Adding to cart\n").start();

    await page.click('input.button');

    await delay(50);

    try {
        await page.click('.checkout');
    } catch {
        while (page.url() != checkout) {
            await page.goto(checkout);
        }
    }

    spinner.stop();
}

// New delay, page.waitFor is gonna be removed
function delay(x) {
    return new Promise(resolve => {
        setTimeout(() => {
            resolve(x);
        }, x);
    });
}

function getUser(userID) {
    var user = JSON.parse(fs.readFileSync('Profiles.json'));

    for (let i = 0; i < user.length; i++) {
        if (user[i].userID === userID) {
            return user[i];
        }
    }
}

// Open browser Instance
async function openBrowser(URL) {
    const br = ora("Opening Browser\n").start();

    const browser = await puppeteer.launch({
        headless: false,
        executablePath: location,
        defaultViewport: null
    });

    const page = await browser.newPage();

    await page.goto(URL);

    br.stop();

    return page;
}

supremeTask();