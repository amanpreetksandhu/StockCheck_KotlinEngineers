const User = require('../models/User');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

async function signup(req, res) {
    try {
      const { email, employeeId, password } = req.body;
  
      // Validate inputs (basic example)
      if (!email || !employeeId || !password) {
        return res.status(400).json({ message: 'All fields are required' });
      }
  
      // Check if user exists by email or employeeId
      const existingUser = await User.findOne({
        $or: [{ email }, { employeeId }]
      });
  
      if (existingUser) {
        return res.status(409).json({ message: 'Email or Employee ID already in use' });
      }
  
      // Hash password
      const saltRounds = 10;
      const hashedPassword = await bcrypt.hash(password, saltRounds);
  
      // Create new user
      const newUser = new User({
        email,
        employeeId,
        password: hashedPassword,
      });
  
      await newUser.save();
  
      res.status(201).json({ message: 'User created successfully' });
    } catch (error) {
      console.error(error);
      res.status(500).json({ message: 'Server error' });
    }
  }
  
  async function login(req, res) {
    const { employeeId, password } = req.body;
    try {
      const user = await User.findOne({ employeeId });
      if (!user) return res.status(400).json({ message: 'Invalid credentials' });
  
      const isMatch = await bcrypt.compare(password, user.password);
      if (!isMatch) return res.status(400).json({ message: 'Invalid credentials' });
  
      const token = jwt.sign({ id: user._id }, process.env.JWT_SECRET, { expiresIn: '1d' });
      res.status(200).json({ token, employeeId: user.employeeId });
    } catch (err) {
      res.status(500).json({ message: 'Server error' });
    }
  }
module.exports = { signup, login };
