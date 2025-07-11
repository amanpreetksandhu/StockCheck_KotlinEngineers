const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
const path = require('path');
//notifications handler =====================\
const http = require('http'); 
const { Server } = require('socket.io'); 
// ======================================/
const authRoutes = require('./routes/auth')
const locationsRoute = require('./routes/location')
const inventoryRoutes = require('./routes/inventory');
const uploadRoute = require('./routes/upload'); // Images handler


require('dotenv').config();

const app = express();

//notifications handler ===================================\
const server = http.createServer(app); // Use custom HTTP server
const io = new Server(server, {
  cors: {
    origin: "*", // You can restrict it to your Android IP if needed
    methods: ["GET", "POST"]
  }
});

// Make the io instance available to routes
app.set('io', io); // <- This line makes io accessible in your route files

// =================================================/

app.use(cors());
app.use(express.json());
app.use('/uploads', express.static(path.join(__dirname, 'uploads'))); // Images storage

mongoose.connect(process.env.MONGO_URI)
  .then(() => console.log('MongoDB connected'))
  .catch((err) => console.log(err));

app.use('/', authRoutes);
app.use('/locations', locationsRoute)
app.use('/inventory', inventoryRoutes);
app.use('/api', uploadRoute); // Pipeline to images

io.on('connection', (socket) => {
  console.log('Client connected:', socket.id);

  socket.on('disconnect', () => {
    console.log('Client disconnected:', socket.id);
  });
});


app.listen(process.env.PORT, () => console.log(`Server running on port ${process.env.PORT}`));
