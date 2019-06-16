const admin = require('firebase-admin');

const serviceAccount = require('../data/tickettoto-firebase-adminsdk-jmmzl-45be2446bc.json');

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: 'https://tickettoto.firebaseio.com',
});

const auth = admin.auth();

const firebase = admin.database().ref();

const uploadFile = async function uploadFile(storagePath, file) {
  const fileName = `${new Date().getTime()}.${file.mimetype.split('/')[1]}`;
  const options = {
    destination: storage.file(`${storagePath}${fileName}`),
    resumable: false,
  };
  try {
    const path = await tmpFile(file.buffer);
    await storage.upload(path, options);
    const signedUrls = await storage.file(`${storagePath}${fileName}`).getSignedUrl({
      action: 'read',
      expires: '03-09-2491',
    });
    return { url: signedUrls[0], ref: `${storagePath}${fileName}` };
  } catch (e) {
    return e;
  }
};

const deleteFile = async filename => storage.file(filename).delete();

module.exports = {
  auth,
  firebase,
  uploadFile,
  deleteFile,
};
