const { auth } = require('./utils/firebase');
const trainers = require('./data/trainers.json');

(async () => {
  try {
    for (trainer of trainers) {
      const { email, password } = trainer;
      const user = await auth.getUserByEmail(email);
      if (user) {
        await auth.updateUser(Object.assign(user, { password }));
      } else {
        await auth.createUser({ email, password });
      }
    }
    console.log('Trainers added or updated sucessfully!');
  } catch(err) {
    console.error(err);
  } finally {
    process.exit(0);
  }
})();
