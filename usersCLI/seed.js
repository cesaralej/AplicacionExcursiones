const { firebase } = require('./utils/firebase');
const csv = require('csv-parser');
const fs = require('fs');

const db = firebase.ref('users');

const users = [];
 
fs.createReadStream('./data/fakedatabase.csv')
  .pipe(csv())
  .on('data', (data) => {
    const {
      Name,
      Phone,
      Email,
      day,
      month,
      year,
      School,
      Destination,
      Passports
    } = data;
    users.push(db.push().set({
      name: Name,
      phone: Phone,
      email: Email,
      day,
      month,
      year,
      school: School,
      destination: Destination,
      passports: Passports,
      status: false,
    }));
  })
  .on('end', () => {
    Promise.all(users)
      .then(res => {
        console.log(res);        
        process.exit(0);
      });
  });
