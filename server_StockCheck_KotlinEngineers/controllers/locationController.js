const Location = require("../models/Location");

//Read
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

//Create
async function createLocation(req, res) {
  const location = new Location(req.body);
  try {
    const newLoc = await location.save();
    res.status(201).json(newLoc);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
}

//Update
async function updateLocation(req, res) {
  try {
    const updated = await Location.findByIdAndUpdate(req.params.id, req.body, {
      new: true,
    });
    res.json(updated);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
}

//Delete
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
