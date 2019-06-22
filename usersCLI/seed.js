const path = require('path');
const { db, uploadFile } = require('./utils/firebase');
const usersData = require('./data/fakedata.json');

  // Buffer mydata
  var BUFFER = bufferFile('../public/mydata.png');

  function bufferFile(relPath) {
    return ; // zzzz....
  }

const usersCollection = db.collection('users');

(async () => {
  for (user of usersData) {
    const profilePicture = await uploadFile('profilePictures/', path.join(__dirname, `./data/${user.profilePicturePath}`));
    delete user.profilePicturePath
    const result = await usersCollection.add(Object.assign(user, { profilePicture }));
    console.log(result);
  }
})();
 