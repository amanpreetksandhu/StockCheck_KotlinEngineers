const mongoose = require('mongoose');
const LocationSchema = new mongoose.Schema({
  name: String,
  address: String,
  contactName: String,
  contactInfo: String
});
module.exports = mongoose.model('Location', LocationSchema);