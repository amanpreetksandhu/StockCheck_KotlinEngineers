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
    io.emit('itemAdded', newItem);

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
    io.emit('itemUpdated', updatedItem);

    res.status(200).json(updatedItem);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.deleteItem = async (req, res) => {
  try {
    const deletedItem = await InventoryItem.findByIdAndDelete(req.params.id);

    const io = req.app.get('io');
    io.emit('itemDeleted', deletedItem);

    res.status(200).json(deletedItem);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};




/*
Backup


// Create new item
exports.createItem = async (req, res) => {
  try {
    const newItem = new InventoryItem(req.body);
    const savedItem = await newItem.save();
    res.status(201).json(savedItem);
  } catch (err) {
    res.status(400).json({ error: 'Failed to create item' });
  }
};

// Update item
exports.updateItem = async (req, res) => {
  try {
    const updated = await InventoryItem.findByIdAndUpdate(req.params.id, req.body, { new: true });
    if (!updated) return res.status(404).json({ error: 'Item not found' });
    res.json(updated);
  } catch (err) {
    res.status(400).json({ error: 'Failed to update item' });
  }
};

// Delete item
exports.deleteItem = async (req, res) => {
  try {
    const deleted = await InventoryItem.findByIdAndDelete(req.params.id);
    if (!deleted) return res.status(404).json({ error: 'Item not found' });
    res.json({ message: 'Item deleted' });
  } catch (err) {
    res.status(500).json({ error: 'Failed to delete item' });
  }
};
*/
