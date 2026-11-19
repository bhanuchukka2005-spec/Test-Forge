// Reference snippet for Lab 9. Add this to the standalone Selenium Maven test project.
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteGridLoginTest {
    public static void main(String[] args) throws Exception {
        String gridUrl = System.getenv().getOrDefault("SELENIUM_GRID_URL", "http://localhost:4444");
        WebDriver driver = new RemoteWebDriver(new URL(gridUrl), new ChromeOptions());
        try {
            driver.get("http://localhost:8080"); // When Grid runs in a container, use host.docker.internal on Windows.
            driver.findElement(By.id("username")).sendKeys("student");
            driver.findElement(By.id("password")).sendKeys("Student@123");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            if (!driver.findElement(By.id("dashboard")).isDisplayed()) throw new AssertionError("Dashboard not visible");
            System.out.println("GRID TEST PASSED");
        } finally { driver.quit(); }
    }
}
