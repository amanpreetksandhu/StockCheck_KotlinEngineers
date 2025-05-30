const mongoose = require('mongoose');

const userSchema = new mongoose.Schema({
  email: {
    type: String,
    required: true,
    unique: true,       // no duplicate emails
    lowercase: true,
    trim: true,
  },
  employeeId: {
    type: String,
    required: true,
    unique: true,       // no duplicate employee IDs
  },
  password: {
    type: String,
    required: true,
  },
}, { timestamps: true });

const User = mongoose.model('User', userSchema);

module.exports = User;
