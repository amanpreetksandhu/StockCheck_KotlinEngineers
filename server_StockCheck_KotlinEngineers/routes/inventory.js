const express = require('express');
const router = express.Router();
const inventoryController = require('../controllers/inventoryController');

// Routes
router.get('/', inventoryController.getAllItems);
router.get('/:id', inventoryController.getItemById);
router.post('/', (req, res) => inventoryController.createItem(req, res));
router.put('/:id', (req, res) => inventoryController.updateItem(req, res));
router.delete('/:id', (req, res) => inventoryController.deleteItem(req, res));
router.get('/location/:locationId', inventoryController.getInventoryByLocationId);



//Backup
// router.post('/', inventoryController.createItem);
// router.put('/:id', inventoryController.updateItem);
// router.delete('/:id', inventoryController.deleteItem);

module.exports = router;
