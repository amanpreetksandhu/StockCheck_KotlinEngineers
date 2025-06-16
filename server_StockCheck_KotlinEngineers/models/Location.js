const mongoose = require('mongoose');
const LocationSchema = new mongoose.Schema({
  name: String,
  address: String,
  contactName: String,
  contactEmail: String,
  contactPhone: String
});

const Location = mongoose.model('Location', LocationSchema)
module.exports = Location;