const mongoose = require('mongoose');

const inventoryItemSchema = new mongoose.Schema({
  name: { type: String, required: true },
  description: { type: String, default: "" },
  category: { type: String, default: "" },
  qty: { type: Number, required: true },
  price: { type: Number, default: 0.0 },
  imageUrl: { type: String, default: "" },
  locationId: { type: mongoose.Schema.Types.ObjectId, ref: 'Location', required: false },
  status: { type: String, enum: ['In Stock', 'Out of Stock'], default: 'In Stock' }
}, { timestamps: true });

module.exports = mongoose.model('InventoryItem', inventoryItemSchema);
