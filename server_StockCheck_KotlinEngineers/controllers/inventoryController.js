const InventoryItem = require('../models/InventoryItem');

// Get all inventory items
exports.getAllItems = async (req, res) => {
  try {
    const items = await InventoryItem.find();
    res.json(items);
  } catch (err) {
    res.status(500).json({ error: 'Failed to fetch inventory items' });
  }
};

// Get single item by ID
exports.getItemById = async (req, res) => {
  try {
    const item = await InventoryItem.findById(req.params.id);
    if (!item) return res.status(404).json({ error: 'Item not found' });
    res.json(item);
  } catch (err) {
    res.status(500).json({ error: 'Failed to fetch item' });
  }
};

exports.createItem = async (req, res) => {
  try {
    const newItem = new InventoryItem(req.body);
    await newItem.save();

    const io = req.app.get('io');
    io.emit('itemAdded', newItem); // Emit event for new item

    res.status(201).json(newItem);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.updateItem = async (req, res) => {
  try {
    const updatedItem = await InventoryItem.findByIdAndUpdate(
      req.params.id,
      req.body,
      { new: true }
    );

    const io = req.app.get('io');
    io.emit('itemUpdated', updatedItem);  // Emit event for item update

    res.status(200).json(updatedItem);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.deleteItem = async (req, res) => {
  try {
    const deletedItem = await InventoryItem.findByIdAndDelete(req.params.id);

    const io = req.app.get('io');
    io.emit('itemDeleted', deletedItem); // Emit event for item deletion

    res.status(200).json(deletedItem);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};


