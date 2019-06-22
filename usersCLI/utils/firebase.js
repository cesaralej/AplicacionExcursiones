const admin = require('firebase-admin');

const serviceAccount = require('../data/tickettoto-firebase-adminsdk-jmmzl-45be2446bc.json');

const PROJECT_NAME = 'tickettoto';

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: 'https://tickettoto.firebaseio.com',
});

const auth = admin.auth();

const storage = admin.storage().bucket(`gs://${PROJECT_NAME}.appspot.com`);

const db = admin.firestore();
db.settings({ timestampsInSnapshots: true });

const uploadFile = async function uploadFile(storagePath, filePath) {
  const fileName = `${new Date().getTime()}.${filePath.split('.')[filePath.split('.').length-1]}`;
  const options = {
    destination: storage.file(`${storagePath}${fileName}`),
    resumable: false,
  };
  try {
    await storage.upload(filePath, options);
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
  db,
  uploadFile,
  deleteFile,
};
