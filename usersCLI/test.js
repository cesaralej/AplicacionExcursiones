const { firebase } = require('./utils/firebase');

const db = firebase.ref('users').orderByKey().on("child_added", function(snapshot) {
  console.log(snapshot.key);
});
