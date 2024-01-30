# Client node per la nostra DB

Recorda que el codi node-js es pot llegir quan s'hi té accés. Si el fitxer `app.js` cau en mans equivocades es podrà llegir la contrasenya i accedir a la base de dades.

## Instal·la
```bash
sudo apt update
sudo apt install node
sudo apt install npm
npm i express
npm i mysql2@3.0.0
```

## Configura
Edita el fitxer app.js per posar les credencials correctes

## Executa
```bash
node app.js
```

## Prova
El client node obre una api a `localhost:3000/results` on es proveix un fitxer JSON amb els resultats registrats a la base de dades.
