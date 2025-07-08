const express = require('express');
const router = express.Router();

const inventoryController = require('../controllers/inventoryController');

router.get('/', locationController.getAllInventories);
router.get('/:id', locationController.getInventoriesByLocationId);
router.post('/', locationController.createInventory);
router.put('/:id', locationController.updateInventory);
router.delete('/:id', locationController.deleteInventory);

module.exports = router;
