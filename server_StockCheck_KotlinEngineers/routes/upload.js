const express = require('express');
const multer = require('multer');
const path = require('path');
const fs = require('fs');

const router = express.Router();

// Set the storage destination and filename
const storage = multer.diskStorage({
  destination: (req, file, cb) => {
    const uploadDir = path.join(__dirname, '..', 'uploads');
    if (!fs.existsSync(uploadDir)) {
      fs.mkdirSync(uploadDir); // Create folder if it doesn't exist
    }
    cb(null, uploadDir);
  },
  filename: (req, file, cb) => {
    // Use original file name with a timestamp to avoid conflicts
    const uniqueName = `${Date.now()}-${file.originalname}`;
    cb(null, uniqueName);
  },
});

// Middleware for handling image uploads
const upload = multer({ storage });

// ✅ Endpoint to handle the upload
router.post('/upload', upload.single('file'), (req, res) => {
  if (!req.file) {
    return res.status(400).json({ message: 'No file uploaded' });
  }

  
  const imageUrl = `http://10.0.2.2:5000/uploads/${req.file.filename}`;

  res.status(200).json({ imageUrl }); // essa key é lida no app Android
});

module.exports = router;
