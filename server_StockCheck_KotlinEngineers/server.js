const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
const authRoutes = require('./routes/auth')
const locationsRoute = require('./routes/location')
const inventoryRoutes = require('./routes/inventory');
require('dotenv').config();

const app = express();

app.use(cors());
app.use(express.json());

mongoose.connect(process.env.MONGO_URI)
  .then(() => console.log('MongoDB connected'))
  .catch((err) => console.log(err));

app.use('/', authRoutes);
app.use('/locations', locationsRoute)
app.use('/inventory', inventoryRoutes);
app.listen(process.env.PORT, () => console.log(`Server running on port ${process.env.PORT}`));
