require('dotenv').config() // A toujours mettre tout en haut, permet de config le .env
const express = require('express')
const connection = require('./db-config')

connection.connect((error) => {
    if (error) {
        console.error(error)
    } else {
        console.log("connected to mysql")
    }
})

const app = express()

app.listen(process.env.PORT, () => {
    console.log(`Server is running on port ${process.env.PORT}`)  // Ajout d'un callback
}) // ici on lit le port du .env (attention, on ne doit pas mettre d'espace dans le .env)

module.exports = app
