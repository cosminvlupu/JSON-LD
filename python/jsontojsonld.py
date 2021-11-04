from pyld import jsonld
import json


def jsonld_easyexample(option):
    f = open('easyexample.json', )
    doc = json.load(f)

    context = {
        "@context": {
            "name": "http://schema.org/name",
            "born": "http://schema.org/birthDate",
            "spouse": {
                "@id": "http://schema.org/spouse",
                "@type": "@id"
            }
        },
        "@id": "http://dbpedia.org/resource/#"
    }


    compacted = jsonld.compact(doc, context)

    if option == 1:
        print(json.dumps(compacted, indent=2))
    elif option == 2:
        # compact using URLs
        print(jsonld.compact(doc, 'http://json-ld.org/contexts/person.jsonld'))


    expanded = jsonld.expand(compacted)

    print(json.dumps(expanded, indent=2))


    flattened = jsonld.flatten(doc)
    print(flattened)


def jsonld_mediumexample(option):
    f = open('mediumexample.json', )
    doc = json.load(f)
    context = {
        "@context":{},
        "@id": "http://franz.com/#",
        "@graph": {
        "name": "http://schema.org/name",
        "description": "http://schema.org/description",
        "image": {
            "@id": "http://schema.org/image",
            "@type": "@id"
        },
        "geo": "http://schema.org/geo",
        "latitude": {
            "@id": "http://schema.org/latitude",
            "@type": "xsd:float"
        },
        "longitude": {
            "@id": "http://schema.org/longitude",
            "@type": "xsd:float"
        },
        "xsd": "http://www.w3.org/2001/XMLSchema#"
        }
    }
    compacted = jsonld.compact(doc, context)
    if option == 1:
        print("OK")
        print(json.dumps(compacted, indent=2))
    elif option == 2:
        # compact using URLs
        print(jsonld.compact(doc, 'http://json-ld.org/contexts/place.jsonld'))


def main():
    option = int(input("Optiune "))
    jsonld_easyexample(option)
    jsonld_mediumexample(option)


if __name__ == "__main__":
    main()
