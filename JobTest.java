import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class JobTest {

    @Test
    void saveToFile() {
        Job job = new Job("2", "Software Development", "Develop a web app", "2025-12-31", "12345");
        job.saveToFile();
        String filePath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "JobRecords.txt";
        File file = new File(filePath);
        assertTrue(file.exists(), "File should be created after saving job.");
    }

    @Test
    void createOrUpdateFile() {
        String filePath = Job.createUpdateFile("TestJobRecords.txt");
        File file = new File(filePath);
        assertTrue(file.exists(), "File should be created or updated.");
        file.delete(); // Cleanup
    }

    @Test
    void stringDateTime() {
        String dateTime = Job.stringDateTime();
        assertNotNull(dateTime, "Date time string should not be null.");
        assertTrue(dateTime.matches("\\d{2}-\\d{2}-\\d{4}_\\d{2}-\\d{2}-\\d{2}"), "Date time format should match dd-MM-yyyy_HH-mm-ss");
    }
}
