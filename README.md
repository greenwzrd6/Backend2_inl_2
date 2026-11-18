Stå alltid i projektroten när du kör skript

För att köra projektet på unix system kör `chmod +x run.sh` och sedan `run.sh` och stäng ej terminalen

För att köra projektet på windows system kör `run.bat` och stäng ej terminalen

För att ta bort den sparade fildatabasen kör `chmod +x remove_database.sh` och `remove_database.sh` eller `remove_database.bat`

Testexempel:
Function ------------------------ HTTP ------------------------ URL

Skapa kund.-----------------------POST------------/customers?name=Anna&phone=123456789

Hämta kund-ID via namn------------GET-------------/customer-id?name=Anna

Lista alla kunder + fordon--------GET-------------/customers

Skapa fordon----------------------POST------------/vehicles?registrationNumber=ABC123&brand=Volvo&model=V60&productionYear=2022

Lista alla fordon-----------------GET-------------/vehicles

Lista fordon med visst bilmärke---GET-------------/vehicles-by-brand?brand=Volvo

Koppla fordon till customer-------POST------------/addvehicletocustomer?customerId=1&vehicleId=1

Uppdatera bilmärke----------------PUT-------------/vehicles/1/brand?brand=Saab

Lista registreringsnummer---------GET-------------/vehicle-brand-info
och bilmärke
