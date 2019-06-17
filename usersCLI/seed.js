const { db } = require('./utils/firebase');
const csv = require('csv-parser');
const fs = require('fs');

const usersCollection = db.collection('users');

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
    users.push(usersCollection.add({
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
      profilePicture: 'https://firebasestorage.googleapis.com/v0/b/tickettoto.appspot.com/o/blank-profile-picture-973460_640.png?alt=media&token=946bcfea-47ce-448c-a82d-e669e9965241',
    }));
  })
  .on('end', () => {
    Promise.all(users)
      .then(res => {
        console.log(res);        
        process.exit(0);
      });
  });
