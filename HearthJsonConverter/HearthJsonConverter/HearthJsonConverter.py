import json
import io
import codecs

def main():
    myFile = codecs.open(r"C:\Users\Chris\Downloads\HearthstoneCustoms\cards.json", "r", "utf-8")
    flavorString = ''
    minionString = ''
    spellString = ''
    nameString = ''
    weaponString = ''
    heroPowerString = ''
    jsonString = myFile.read()
    myFile.close()
    hearthJson = json.loads(jsonString)

    for jsonObj in hearthJson:
        try:
            jsonObj['rarity']
        except:
            jsonObj['rarity'] = 'BASIC'

        try:
            jsonObj['text'] = jsonObj['text'].rstrip()
        except:
            jsonObj['text'] = ''

        try:
            jsonObj['playerClass']
        except:
            jsonObj['playerClass'] = 'NONE'

        if jsonObj['text'] is not None and jsonObj['type'] == 'SPELL':
            spellString = spellString + ' ' + jsonObj['text'] + ' ' + 'RARITY:'+jsonObj['rarity'] + ',COST:'+str(jsonObj['cost']) + ',CLASS:' + jsonObj['playerClass']
        elif jsonObj['text'] is not None and jsonObj['type'] == 'MINION':
            minionString = minionString + ' ' + jsonObj['text'] + ' ' + 'RARITY:'+jsonObj['rarity'] + ',COST:'+str(jsonObj['cost']) + ',ATT:'+str(jsonObj['attack']) + ',DUR:'+str(jsonObj['health']) + ',CLASS:'+ str(jsonObj['playerClass'])
        elif jsonObj['text'] is not None and jsonObj['type'] == 'HERO_POWER':
            heroPowerString = heroPowerString + ' ' + jsonObj['text'] + ' ' + 'COST:'+str(jsonObj['cost'])
        elif jsonObj['text'] is not None and jsonObj['type'] == 'WEAPON':
            weaponString = weaponString + ' ' + jsonObj['text'] + ' ' + 'RARITY:'+jsonObj['rarity'] + ',COST:'+str(jsonObj['cost']) + ',ATT:'+str(jsonObj['attack']) + ',DUR:'+str(jsonObj['durability'])

        try:
            flavorString += ' ' + jsonObj['flavor']
        except:
            pass

        try:
            nameString = nameString + ' ' + jsonObj['name']
        except:
            pass

    myFile = codecs.open(r"C:\Users\Chris\Downloads\HearthstoneCustoms\spell.txt", "w", "utf-8")
    myFile.write(spellString)
    myFile.close()
    
    myFile = codecs.open(r"C:\Users\Chris\Downloads\HearthstoneCustoms\minion.txt", "w", "utf-8")
    myFile.write(minionString)
    myFile.close()

    myFile = codecs.open(r"C:\Users\Chris\Downloads\HearthstoneCustoms\names.txt", "w", "utf-8")
    myFile.write(nameString)
    myFile.close()

    myFile = codecs.open(r"C:\Users\Chris\Downloads\HearthstoneCustoms\flavor.txt", "w", "utf-8")
    myFile.write(flavorString)
    myFile.close()

    myFile = codecs.open(r"C:\Users\Chris\Downloads\HearthstoneCustoms\hero_power.txt", "w", "utf-8")
    myFile.write(heroPowerString)
    myFile.close()

    myFile = codecs.open(r"C:\Users\Chris\Downloads\HearthstoneCustoms\weapon.txt", "w", "utf-8")
    myFile.write(weaponString)
    myFile.close()

main()