package com.mycompany.smarthomeconsole;
import java.util.ArrayList;
import java.util.Scanner;

// Abstraction
abstract class Device {
    // Encapsulation
    private String deviceName;
    private boolean isOn;
    private String deviceType;

    public Device(String deviceName, String deviceType) {
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.isOn = false;   
    }

    //getters
    public String getDeviceName() {
        return deviceName;
    }

    public boolean getStatus() {
        return isOn;
    }

    public String getDeviceType() {
        return deviceType;
    }
    //setter
    protected void setStatus(boolean status) {
        this.isOn = status;
    }

    public abstract void turnOn();
    public abstract void turnOff();

    
    public void basicTurnOn() {
        setStatus(true);
        System.out.println(deviceName + " is now ON");
    }

    public void basicTurnOff() {
        setStatus(false);
        System.out.println(deviceName + " is now OFF");
    }


    public void displayStatus() {
        String status = isOn ? "ON" : "OFF";
        System.out.println(deviceType + " - " + deviceName + ": " + status);
    }

   
    public void adjustSettings(Scanner scanner) {
        System.out.println("No adjustable settings for this device.");
    }
}

// Inheritance 
class Light extends Device {
    private int brightness;

    public Light(String deviceName) {
        super(deviceName, "Light");
        this.brightness = 0;
    }
    //Polymorphism

    @Override
    public void turnOn() {
        setStatus(true);
        if (this.brightness == 0) {
            this.brightness = 75; 
        }
        System.out.println(getDeviceName() + " (Light) is now ON with brightness: " + brightness + "%");
    }

    @Override
    public void turnOff() {
        setStatus(false);
        System.out.println(getDeviceName() + " (Light) is now OFF");
    }

    @Override
    public void displayStatus() {
        String status = getStatus() ? "ON" : "OFF";
        System.out.println(getDeviceType() + " - " + getDeviceName() + ": " + status +
                          (getStatus() ? " (Brightness: " + brightness + "%)" : ""));
    }

    @Override
    public void adjustSettings(Scanner scanner) {
        if (!getStatus()) {
            System.out.println("Please turn on the light first!");
            return;
        }

        System.out.println("Current brightness: " + brightness + "%");
        System.out.print("Enter new brightness (0-100): ");
        String input = scanner.nextLine();

        if (SmartHomeConsole.isValidNumber(input)) {
            int newBrightness = Integer.parseInt(input);
            if (newBrightness >= 0 && newBrightness <= 100) {
                this.brightness = newBrightness;
                if (newBrightness == 0) {
                    setStatus(false);
                    System.out.println(getDeviceName() + " turned OFF (brightness set to 0)");
                } else {
                    System.out.println(getDeviceName() + " brightness set to " + brightness + "%");
                }
            } else {
                System.out.println("Brightness must be between 0-100!");
            }
        } else {
            System.out.println("Please enter a valid number!");
        }
    }

    public int getBrightness() {
        return brightness;
    }
}

// Inheritance
class Fan extends Device {
    private int speed;

    public Fan(String deviceName) {
        super(deviceName, "Fan");
        this.speed = 0;
    }

    @Override
    public void turnOn() {
        setStatus(true);
        if (this.speed == 0) {
            this.speed = 3; 
        }
        System.out.println(getDeviceName() + " (Fan) is now ON at speed: " + speed);
    }

    @Override
    public void turnOff() {
        setStatus(false);
        System.out.println(getDeviceName() + " (Fan) is now OFF");
    }

    @Override
    public void displayStatus() {
        String status = getStatus() ? "ON" : "OFF";
        System.out.println(getDeviceType() + " - " + getDeviceName() + ": " + status +
                          (getStatus() ? " (Speed: " + speed + ")" : ""));
    }

    @Override
    public void adjustSettings(Scanner scanner) {
        if (!getStatus()) {
            System.out.println("Please turn on the fan first!");
            return;
        }

        System.out.println("Current speed: " + speed);
        System.out.print("Enter new speed (1-5): ");
        String input = scanner.nextLine();

        if (SmartHomeConsole.isValidNumber(input)) {
            int newSpeed = Integer.parseInt(input);
            if (newSpeed >= 1 && newSpeed <= 5) {
                this.speed = newSpeed;
                System.out.println(getDeviceName() + " speed set to " + speed);
            } else {
                System.out.println("Speed must be between 1-5!");
            }
        } else {
            System.out.println("Please enter a valid number!");
        }
    }

    public int getSpeed() {
        return speed;
    }
}

// Inheritance
class AirConditioner extends Device {
    private int temperature;
    private String mode;

    public AirConditioner(String deviceName) {
        super(deviceName, "Air Conditioner");
        this.temperature = 24;
        this.mode = "Cool";
    }

    @Override
    public void turnOn() {
        setStatus(true);
        System.out.println(getDeviceName() + " (AC) is now ON - Mode: " + mode + ", Temperature: " + temperature + "°C");
    }

    @Override
    public void turnOff() {
        setStatus(false);
        System.out.println(getDeviceName() + " (AC) is now OFF");
    }

    @Override
    public void displayStatus() {
        String status = getStatus() ? "ON" : "OFF";
        System.out.println(getDeviceType() + " - " + getDeviceName() + ": " + status +
                          (getStatus() ? " (Mode: " + mode + ", Temp: " + temperature + "°C)" : ""));
    }

    @Override
    public void adjustSettings(Scanner scanner) {
        if (!getStatus()) {
            System.out.println("Please turn on the AC first!");
            return;
        }

        System.out.println("\n--- AC Settings ---");
        System.out.println("Current Mode: " + mode + ", Temperature: " + temperature + "°C");
        System.out.println("[1] Change Temperature");
        System.out.println("[2] Change Mode");
        System.out.print("Select option (1-2): ");

        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            System.out.print("Enter new temperature (16-30°C): ");
            String tempInput = scanner.nextLine();

            if (SmartHomeConsole.isValidNumber(tempInput)) {
                int newTemp = Integer.parseInt(tempInput);
                if (newTemp >= 16 && newTemp <= 30) {
                    this.temperature = newTemp;
                    System.out.println(getDeviceName() + " temperature set to " + temperature + "°C");
                } else {
                    System.out.println("Temperature must be between 16-30°C!");
                }
            } else {
                System.out.println("Please enter a valid number!");
            }
        } else if (choice.equals("2")) {
            System.out.println("Available modes:");
            System.out.println("[1] Cool  [2] Heat  [3] Fan  [4] Auto");
            System.out.print("Select mode (1-4): ");

            String modeChoice = scanner.nextLine();
            String[] modes = {"Cool", "Heat", "Fan", "Auto"};

            if (SmartHomeConsole.isValidNumber(modeChoice)) {
                int modeIndex = Integer.parseInt(modeChoice);
                if (modeIndex >= 1 && modeIndex <= 4) {
                    this.mode = modes[modeIndex - 1];
                    System.out.println(getDeviceName() + " mode set to " + mode);
                } else {
                    System.out.println("Please select 1-4!");
                }
            } else {
                System.out.println("Please enter a valid number!");
            }
        } else {
            System.out.println("Invalid option!");
        }
    }

    public int getTemperature() {
        return temperature;
    }

    public String getMode() {
        return mode;
    }
}


class SmartHomeSystem {
    private ArrayList<Device> devices;
    private String systemPassword;
    private String previousSystemPassword; 
    private boolean isLoggedIn;
    private boolean isPasswordSet;

    public SmartHomeSystem() {
        this.devices = new ArrayList<>();
        this.systemPassword = null;
        this.previousSystemPassword = null; 
        this.isLoggedIn = false;
        this.isPasswordSet = false;
        initializeDevices();
    }

    private void initializeDevices() {
        
        devices.add(new Light("Living Room Light"));
        devices.add(new Light("Bedroom Light"));
        devices.add(new Fan("Ceiling Fan"));
        devices.add(new AirConditioner("Main AC"));
    }

    public boolean isPasswordSet() {
        return isPasswordSet;
    }

    public void setupPassword(Scanner scanner) {
        System.out.println("\n=============== FIRST TIME SETUP ===============");
        System.out.println("\t       Welcome to SHAC 2.0!");
        System.out.println();
        System.out.println("\tPlease set up your system password.");
        System.out.println();
        System.out.println("Password Requirements:");
        System.out.println("- At least 6 characters long");
        System.out.println("- Cannot be empty or contain only spaces");
        System.out.println("================================================");
        System.out.println();

        while (!isPasswordSet) {
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();

            if (isValidPassword(newPassword)) {
                System.out.print("Confirm password: ");
                String confirmPassword = scanner.nextLine();

                if (newPassword.equals(confirmPassword)) {
                    this.systemPassword = newPassword;
                    this.isPasswordSet = true;
                    System.out.println("Password set successfully!");
                    System.out.println("Your SHAC 2.0 system is now ready to use.");
                } else {
                    System.out.println("Passwords do not match! Please try again.");
                }
            } else {
                System.out.println("Invalid password! Please ensure it meets the requirements.");
            }
        }
    }

    private boolean isValidPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Password cannot be empty!");
            return false;
        }

        if (password.length() < 6) {
            System.out.println("Password must be at least 6 characters long!");
            return false;
        }

        return true;
    }

    public boolean login(String password) {
        if (password.equals(systemPassword)) {
            isLoggedIn = true;
            System.out.println("Login successful!");
            System.out.println("Welcome back to SHAC 2.0");

            showDeviceNotification();

            return true;
        } else {
            System.out.println("Invalid password! Access denied.");
            return false;
        }
    }


    private void showDeviceNotification() {
        System.out.println("\n" + "=".repeat(45));
        System.out.println("\t\t DEVICE STATUS");
        System.out.println("=".repeat(45));
        System.out.println("- You have " + devices.size() + " devices configured in your system.");


        System.out.println("\n- Enter 1 to view your devices.");
        System.out.println("=".repeat(45));
    }

    public boolean isUserLoggedIn() {
        return isLoggedIn;
    }

    public void displayAllDevices() {
        System.out.println("\n************** DEVICE'S STATUS **************");
        for (int i = 0; i < devices.size(); i++) {
            System.out.print((i + 1) + ". ");
            devices.get(i).displayStatus();
        }
        System.out.println("********************************************");
    }

    public void turnOnDevice(int deviceIndex) {
        if (deviceIndex >= 0 && deviceIndex < devices.size()) {
            devices.get(deviceIndex).turnOn();
        } else {
            System.out.println("Invalid device number!");
        }
    }

    public void turnOffDevice(int deviceIndex) {
        if (deviceIndex >= 0 && deviceIndex < devices.size()) {
            devices.get(deviceIndex).turnOff();
        } else {
            System.out.println("Invalid device number!");
        }
    }

    public void adjustDeviceSettings(int deviceIndex, Scanner scanner) {
        if (deviceIndex >= 0 && deviceIndex < devices.size()) {
            devices.get(deviceIndex).adjustSettings(scanner);
        } else {
            System.out.println("Invalid device number!");
        }
    }

    public void addNewDevice(Scanner scanner) {
        System.out.println("\n----- Add New Device -----");
        System.out.println("[1] Light");
        System.out.println("[2] Fan");
        System.out.println("[3] Air Conditioner");
        System.out.print("Select device type (1-3): ");

        String deviceType = scanner.nextLine();

        if (!SmartHomeConsole.isValidNumber(deviceType)) {
            System.out.println("Please enter a valid number!");
            return;
        }

        int typeChoice = Integer.parseInt(deviceType);

        if (typeChoice < 1 || typeChoice > 3) {
            System.out.println("Please select 1-3!");
            return;
        }

        System.out.print("Enter device name: ");
        String deviceName = scanner.nextLine().trim();

        if (deviceName.isEmpty()) {
            System.out.println("Device name cannot be empty!");
            return;
        }

    
        for (Device device : devices) {
            if (device.getDeviceName().equalsIgnoreCase(deviceName)) {
                System.out.println("A device with this name already exists!");
                return;
            }
        }

        Device newDevice = null;

        switch (typeChoice) {
            case 1:
                newDevice = new Light(deviceName);
                break;
            case 2:
                newDevice = new Fan(deviceName);
                break;
            case 3:
                newDevice = new AirConditioner(deviceName);
                break;
        }

        devices.add(newDevice);
        System.out.println(newDevice.getDeviceType() + " '" + deviceName + "' added successfully!");
    }

    public void removeDevice(Scanner scanner) {
        if (devices.isEmpty()) {
            System.out.println("No devices to remove!");
            return;
        }

        displayAllDevices();
        System.out.print("Enter device number to remove: ");
        String deviceNum = scanner.nextLine();

        if (SmartHomeConsole.isValidNumber(deviceNum)) {
            int deviceIndex = Integer.parseInt(deviceNum) - 1;
            if (deviceIndex >= 0 && deviceIndex < devices.size()) {
                Device removedDevice = devices.remove(deviceIndex);
                System.out.println(removedDevice.getDeviceName() + " removed successfully!");
            } else {
                System.out.println("Invalid device number!");
            }
        } else {
            System.out.println("Please enter a valid number!");
        }
    }

    public void changePassword(Scanner scanner) {
        System.out.println("\n------- Change Password -------");
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();

        if (!currentPassword.equals(systemPassword)) {
            System.out.println("Current password is incorrect!");
            return;
        }

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();


        if (newPassword.equals(systemPassword)) {
            System.out.println("New password cannot be the same as the current password!");
            return;
        }

        
        if (previousSystemPassword != null && newPassword.equals(previousSystemPassword)) {
            System.out.println("New password cannot be the same as your immediately previous password!");
            return;
        }

        if (!isValidPassword(newPassword)) {
            return;
        }

        System.out.print("Confirm new password: ");
        String confirmPassword = scanner.nextLine();

        if (newPassword.equals(confirmPassword)) {
            this.previousSystemPassword = this.systemPassword;
            this.systemPassword = newPassword; 
            System.out.println("Password changed successfully!");
        } else {
            System.out.println("Passwords do not match!");
        }
    }

    public int getDeviceCount() {
        return devices.size();
    }

    public void logout() {
        isLoggedIn = false;
        System.out.println("Logged out successfully. Goodbye!");
    }
}

// Main class
public class SmartHomeConsole {

    public static boolean isValidNumber(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SmartHomeSystem homeSystem = new SmartHomeSystem();

        System.out.println("\t\t  Welcome to SHAC 2.0 \n\t   (Smart Home Automation Console)");
        System.out.println("*******************************************************");

        
        if (!homeSystem.isPasswordSet()) {
            homeSystem.setupPassword(scanner);
        }

        
        boolean loginSuccessful = false;
        int loginAttempts = 0;
        int maxAttempts = 3;

        while (!loginSuccessful && loginAttempts < maxAttempts) {
            System.out.print("\nEnter password to access the system: ");
            String enteredPassword = scanner.nextLine();

            loginSuccessful = homeSystem.login(enteredPassword);
            loginAttempts++;

            if (!loginSuccessful && loginAttempts < maxAttempts) {
                System.out.println("Attempts remaining: " + (maxAttempts - loginAttempts));
            }
        }

        if (!loginSuccessful) {
            System.out.println("Maximum login attempts exceeded. System locked!");
            scanner.close();
            return;
        }

        
        while (homeSystem.isUserLoggedIn()) {
            System.out.println("\n*********** SHAC 2.0 ***********");
            System.out.println("---------- Main Menu -----------");
            System.out.println("[1] View All Devices");
            System.out.println("[2] Turn On Device");
            System.out.println("[3] Turn Off Device");
            System.out.println("[4] Adjust Device Settings");
            System.out.println("[5] Add New Device");
            System.out.println("[6] Remove Device");
            System.out.println("[7] Change Password");
            System.out.println("[8] Logout");
            System.out.print("Select an option (1-8): ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                homeSystem.displayAllDevices();

            } else if (choice.equals("2")) {
                homeSystem.displayAllDevices();
                System.out.print("Enter device number to turn ON: ");
                String deviceNum = scanner.nextLine();

                if (isValidNumber(deviceNum)) {
                    int deviceIndex = Integer.parseInt(deviceNum) - 1;
                    homeSystem.turnOnDevice(deviceIndex);
                } else {
                    System.out.println("Please enter a valid number!");
                }

            } else if (choice.equals("3")) {
                homeSystem.displayAllDevices();
                System.out.print("Enter device number to turn OFF: ");
                String deviceNum = scanner.nextLine();

                if (isValidNumber(deviceNum)) {
                    int deviceIndex = Integer.parseInt(deviceNum) - 1;
                    homeSystem.turnOffDevice(deviceIndex);
                } else {
                    System.out.println("Please enter a valid number!");
                }

            } else if (choice.equals("4")) {
                homeSystem.displayAllDevices();
                System.out.print("Enter device number to adjust settings: ");
                String deviceNum = scanner.nextLine();

                if (isValidNumber(deviceNum)) {
                    int deviceIndex = Integer.parseInt(deviceNum) - 1;
                    homeSystem.adjustDeviceSettings(deviceIndex, scanner);
                } else {
                    System.out.println("Please enter a valid number!");
                }

            } else if (choice.equals("5")) {
                homeSystem.addNewDevice(scanner);

            } else if (choice.equals("6")) {
                homeSystem.removeDevice(scanner);

            } else if (choice.equals("7")) {
                homeSystem.changePassword(scanner);

            } else if (choice.equals("8")) {
                homeSystem.logout();

            } else {
                System.out.println("Invalid option! Please select 1-8.");
            }
        }

        scanner.close();
        System.out.println("SHAC 2.0 System Closed.");
    }
}
