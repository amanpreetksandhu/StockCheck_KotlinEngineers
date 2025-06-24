const Location = require("../models/Location");
//Validaton for email
function isValidEmail(email) {
  return email.includes('@');
}
//Validaton for phone number
function isValidPhone(phone) {
  const phoneRegex = /^[0-9\-\+]{9,15}$/;
  return phoneRegex.test(phone);
}
//Read all locations
async function getAllLocations(req, res) {
  try {
    const locations = await Location.find();
    res.json(locations);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
}

// Read by id
async function getLocationById(req, res) {
  try {
    const location = await Location.findById(req.params.id);
    if (!location)
      return res.status(404).json({ message: "Location not found" });
    res.json(location);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
}

//Create location
async function createLocation(req, res) {
  const { name, address, city, country, contactName, contactPosition, contactEmail, contactPhone } = req.body;

  if (!isValidEmail(contactEmail)) {
    return res.status(400).json({ message: 'Invalid email format.' });
  }
  if (!isValidPhone(contactPhone)) {
    return res.status(400).json({ message: 'Invalid phone number format.' });
  }

  const location = new Location({ name, address, city, country, contactName, contactPosition, contactEmail, contactPhone });
  try {
    const newLoc = await location.save();
    res.status(201).json(newLoc);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
};

//Update location
async function updateLocation(req, res) {
  try {
    const updated = await Location.findByIdAndUpdate(req.params.id, req.body, { new: true });
    res.json(updated);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
};

//Delete location
async function deleteLocation(req, res) {
  try {
    await Location.findByIdAndDelete(req.params.id);
    res.json({ message: "Deleted location" });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
}

module.exports = {
  getAllLocations,
  getLocationById,
  createLocation,
  deleteLocation,
  updateLocation,
};
