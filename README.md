🏠 Smart Home Automation Console (SHAC 2.0)
This project is a Java-based **Smart Home Automation Console (SHAC 2.0)** that simulates controlling home devices such as lights, fans, and air conditioners. It provides a secure login system, device management, adjustable settings, and a console-based interactive menu all built using Object-Oriented Programming (OOP) principles.

---

🚀 Project Overview  
SHAC 2.0 is a **console-driven home automation simulation system**.  
- Users can manage multiple smart devices.  
- Devices can be turned ON/OFF, adjusted, added, or removed dynamically.  
- Secure access with password authentication.  
- Implements **Abstraction, Inheritance, Encapsulation, and Polymorphism** throughout.  

**Key Features:**  
- 🔐 **Password Protection** (with setup, login attempts, and password change options)  
- 💡 **Light Control** (on/off, adjustable brightness 0–100%)  
- 🌬 **Fan Control** (on/off, adjustable speed 1–5)  
- ❄️ **Air Conditioner Control** (on/off, change mode [Cool, Heat, Fan, Auto], temperature 16–30°C)  
- ➕ **Add/Remove Devices** dynamically  
- 📋 **Device Status Dashboard**  
- 🧑‍💻 **Console Menu Interface** for easy interaction  

---

📊 OOP Concepts Applied  
1️⃣ **Abstraction** → Abstract `Device` class defines common behavior.  
2️⃣ **Inheritance** → `Light`, `Fan`, and `AirConditioner` extend `Device`.  
3️⃣ **Polymorphism** → Overridden methods (`turnOn()`, `turnOff()`, `adjustSettings()`) provide unique behavior.  
4️⃣ **Encapsulation** → Private attributes with controlled access via getters/setters.  

---

📂 Project Structure  
```

src/
└── com.mycompany.smarthomeconsole/
├── Device.java           # Abstract parent class
├── Light.java            # Light device with brightness control
├── Fan.java              # Fan device with speed settings
├── AirConditioner.java   # AC device with temp & mode settings
├── SmartHomeSystem.java  # Core system handling devices & authentication
└── SmartHomeConsole.java # Main class (entry point with UI)

````

---

🛠️ Technologies & Tools  
- **Language:** Java  
- **Paradigm:** Object-Oriented Programming (OOP)  
- **Platform:** Console-based interactive system  

---

📖 How to Run  
1. Clone this repository:  
   ```bash
   git clone https://github.com/your-username/smart-home-console.git
````

2. Open the project in your IDE (NetBeans, IntelliJ, Eclipse, or VS Code with Java).
3. Compile and run `SmartHomeConsole.java`.
4. On first run → set a system password.
5. Use the **main menu** to manage devices.

---

💻 Usage Example

```
Welcome to SHAC 2.0 (Smart Home Automation Console)

=============== FIRST TIME SETUP ===============
Please set up your system password.
Password set successfully!

Enter password to access the system: ****
Login successful! Welcome back to SHAC 2.0.

*********** SHAC 2.0 ***********
[1] View All Devices
[2] Turn On Device
[3] Turn Off Device
[4] Adjust Device Settings
[5] Add New Device
[6] Remove Device
[7] Change Password
[8] Logout
Select an option (1-8):
```

---
