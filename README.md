# Meccano

## Köra projektet

> **Stå alltid i projektroten när du kör skript.** Stäng inte terminalen medan applikationen körs.

### Unix (Linux / macOS)

```bash
chmod +x run.sh
./run.sh
```

### Windows

```bat
run.bat
```

## Ta bort den sparade fildatabasen

### Unix (Linux / macOS)

```bash
chmod +x remove_database.sh
./remove_database.sh
```

### Windows

```bat
remove_database.bat
```

## Testexempel

| Funktion | HTTP | URL |
|---|---|---|
| Skapa kund | `POST` | `http://localhost:8080/customers?name=Anna&phone=123456789` |
| Hämta kund-ID via namn | `GET` | `http://localhost:8080/customer-id
