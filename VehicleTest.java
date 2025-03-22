import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void saveToFileVehicle() {
        Vehicle vehicle = new Vehicle("2" , "Ford" , "Model" , "12345678901234567" , "34256");
        vehicle.saveToFileVehicle();
        String filePath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Vehicle.txt";
        File file = new File(filePath);
        assertTrue(file.exists() , "File should be created after saving Vehicle.");
    }
    @Test
    void createUpdateFile() {
        String filePath = Vehicle.createUpdateFile("TestVehicle.txt");
        File file = new File(filePath);
        assertTrue(file.exists() , "File should be created or updated.");
        file.delete();
    }

    @Test
    void timeStampVehicle() {
        String dateTime = Vehicle.timeStampVehicle();
        assertNotNull(dateTime , "TimeStamp should not be NULL.");
        assertTrue(dateTime.matches("\\d{2}-\\d{2}-\\d{4}_\\d{2}-\\d{2}-\\d{2}"));
    }
}
