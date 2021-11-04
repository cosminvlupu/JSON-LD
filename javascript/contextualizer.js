var fs = require('fs');
const { type } = require('os');
    
function readJSON(filePath) {
    filePath = './jsons/' + filePath
    const data = require(filePath);
    return data
}

function writeJSON(filePath, data) {
    console.log('data ===', data)
    filePath = './jsons-ld/' + filePath
    fs.writeFile(filePath, data, function (err) {
        if (err) throw err;
        console.log('JSON-LD created successfully.');
      });
}


function person(filePath) {
    data = readJSON(filePath)
    newData = new Object()
    newData["@context"] = "http://schema.org/"
    newData["@type"] = "Person"
    // data = JSON.parse(data)
    console.log(newData)
    // console.log(data)
    for (var i in data)
        newData[i] = data[i]
    newData = JSON.stringify(newData, null, "\t")
    writeJSON('person-ld.json',newData)
}

function event(filePath) {
    data = readJSON(filePath)
    newData = new Object()
    context = new Object()
    context["ical"] = "http://www.w3.org/2002/12/cal/ical#"
    context["xsd"] = "http://www.w3.org/2001/XMLSchema#"
    ical = new Object
    ical["@type"] = "xsd:dateTime"
    context["ical:dtstart"] = ical
    // console.log(JSON.stringify(context))
    newData["@context"] = context
    for (var i in data)
        newData["ical:"+i] = data[i]
    newData = JSON.stringify(newData, null, "\t")
    writeJSON('event-ld.json', newData)
}

function place(filePath) {
    data = readJSON(filePath)
    newData = new Object()
    context = new Object()
    context["name"] = "http://schema.org/name"
    context["description"] = "http://schema.org/description"
    image = new Object()
    image["@id"] = "http://schema.org/image"
    image["@type"] = "@id"
    context["image"] = image
    // console.log(context)
    context["geo"] = "http://schema.org/geo"
    context["latitude"] = {
        "@id": "http://schema.org/latitude",
        "@type": "xsd:float"
      }
    context["longitude"] = {
      "@id": "http://schema.org/longitude",
      "@type": "xsd:float"
    }
    context["xsd"] = "http://www.w3.org/2001/XMLSchema#"
    newData["@context"] = context
    for (var i in data)
        newData[i] = data[i]

    // console.log(newData)
    newData = JSON.stringify(newData, null, "\t")
    console.log('new',newData)
    writeJSON('place-ld.json',newData)

}

function recipe(filePath) {
    data = readJSON(filePath)
    newData = new Object()
    context = new Object()
    context["name"] = "http://rdf.data-vocabulary.org/#name"
    context["ingredient"] = "http://rdf.data-vocabulary.org/#ingredients"
    context["yield"] = "http://rdf.data-vocabulary.org/#yield"
    context["instructions"] = "http://rdf.data-vocabulary.org/#instructions"
    context["step"] = {
          "@id": "http://rdf.data-vocabulary.org/#step",
          "@type": "xsd:integer"
        }
    context["description"] = "http://rdf.data-vocabulary.org/#description"
    context["xsd"] = "http://www.w3.org/2001/XMLSchema#"
    newData["@context"] = context
    // console.log(newData)
    for (var i in data)
        newData[i] = data[i]

    // console.log(newData)
    newData = JSON.stringify(newData, null, "\t")
    console.log('new',newData)
    writeJSON('recipe-ld.json',newData)
   
}

function product(filePath) {
    data = readJSON(filePath)
    newData = new Object()
    context = new Object()
    context["gr"] = "http://purl.org/goodrelations/v1#"
    context["pto"] = "http://www.productontology.org/id/"
    context["foaf"] = "http://xmlns.com/foaf/0.1/"
    context["xsd"] = "http://www.w3.org/2001/XMLSchema#"
    context["foaf:page"] = {"@type": "@id"}
    context["gr:acceptedPaymentMethods"]= {"@type": "@id"}
    context["gr:hasBusinessFunction"]= {"@type": "@id"}
    context["gr:hasCurrencyValue"] = {"@type": "xsd:float"}
    newData["@context"] = context
    newData["@id"] = data["id"]
    newData["@type"] ="gr:"+ data["type"]
    newData["gr:name"] = data["name"]
    newData["gr:description"] = data["description"]
    newData["gr:hasBusinessFunction"] = "gr:"+ data["hasBusinessFunction"]
    newData["gr:acceptedPaymentMethods"] = "gr:"+ data["acceptedPaymentMethods"]
    newData["gr:hasPriceSpecification"] = {
        "gr:hasCurrencyValue": data["hasPriceSpecification"]["hasCurrencyValue"],
        "gr:hasCurrency": data["hasPriceSpecification"]["hasCurrency"]
    }
    newData["gr:includes"] = {
    "@type": [
      "gr:" + data["includes"]["type"][0],
      "pto:"+ data["includes"]["type"][1]
        ],
    "gr:name": data["includes"]["name"],  
    "foaf:page" : data["includes"]["page"]
    },
    newData = JSON.stringify(newData, null, "\t")
    console.log('new',newData)
    writeJSON('product-ld.json',newData)
    
}

person("person.json")
event("event.json")
place("place.json")
recipe("recipe.json")
product("product.json")



