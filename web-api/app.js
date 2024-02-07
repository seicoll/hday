const express = require('express');
const app = express();
const mysql = require('mysql2');

const connection = mysql.createConnection({
  host: 'localhost',
  user: 'sergi', // Usuari Base de dades
  password: '1234', // Contrasenya Base de dades
  database: 'quiz'
});

connection.connect(error => {
  if (error) throw error;
  console.log('Connectat a la base de dades');
});

//La ruta arrel / redirigeix /example.html
app.get('/', (req, res) => {
  res.sendFile(__dirname + '/example.html');
});

//La ruta /check envia al formulari
app.get('/check', (req, res) => {
  res.sendFile(__dirname + '/check.html');
});

app.get("/api/results", (req, res) => {
  connection.query("SELECT * FROM results", (error, results) => {
    if (error) throw error;
    res.send(results);
  });
});

app.get("/api/check", (req, res) => {
  // Donat un id de prova i clau, comprova si és correcte.
  // GET /check?id=1&key=abc

  let username = req.query.username;
  let id = req.query.id;
  let key = req.query.key;

  connection.query("SELECT * FROM questions WHERE id=" + id + " AND answer='" + key + "'", (error, results) => {
    /*if (error) {
      throw error
    }
    else {*/
    if (results && results.length) {
      //Si s'ha introduit la clau correcte

      if (!resultExists(username, id))
        //Si no s'havia registrat aquesta prova superada ho inserim
        connection.query("INSERT INTO results (username, solved) VALUES ('" + username + "', " + id + ")"), (error, results) => {
          if (error) throw error
        };

      res.set('Content-Type', 'text/html');
      res.send("Prova superada!");
    }
    else {
      //Clau incorrecta
      res.set('Content-Type', 'text/html');
      res.send("Clau no correcta.");
    }
    /*};*/
  });
});

//Iniciem l'aplicació web
const port = 3000;
app.listen(port, () => {
  console.log(`API inicialitzada en el port ${port}! http://localhost:${port}`);
});

function resultExists(username, idQuestion) {
  //Comprovem si ja s'havia registrat aquesta prova superada

  connection.query("SELECT * FROM results WHERE username='" + username + "' AND solved=" + idQuestion, (error, results) => {
    if (error) throw error
    if (results.length) {
      //Si ja s'havia registrat que l'usuari havia superat la prova
      return true
    } else {
      return false;
    };
  });
};
