const mongoose = require('mongoose');
const InventorySchema = new mongoose.Schema({
    id: String,
    location_id: String,
    item_name: String,
    description: String,
    category: String,
    status: String,
    quantity: Number,
});

const Inventory = mongoose.model('Inventory', InventorySchema)
module.exports = Inventory;