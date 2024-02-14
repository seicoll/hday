# Client node per la nostra DB

Recorda que el codi node-js es pot llegir quan s'hi té accés. Si el fitxer `app.js` cau en mans equivocades es podrà llegir la contrasenya i accedir a la base de dades.

## Instal·la
```bash
sudo apt update
sudo apt install node
sudo apt install npm
npm i express
npm i mysql2@3.9.1
```

## Configura
Edita el fitxer app.js per posar les credencials correctes

## Executa
```bash
node app.js
```

## Prova
El client node obre una api a `localhost:3000/results` on es proveix un fitxer JSON amb els resultats registrats a la base de dades.
A `localhost:3000/check` es disposa d'un formulari per validar les claus de proves.


## Start node server on machine startup
Si volem que el servidor node s'iniciï automàticament al iniciar l'equip i iniciï l'API pots seguir el tutorial: 
https://www.digitalocean.com/community/tutorials/how-to-set-up-a-node-js-application-for-production-on-ubuntu-18-04#step-3-installing-pm2

