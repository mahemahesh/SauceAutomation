package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class InventoryPage {
    private WebDriver driver;

    private By inventoryItems = By.className("inventory_item");
    private By cartIcon = By.className("shopping_cart_link");
    private By cartBadge = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addItemToCartByName(String itemName) {
        List<org.openqa.selenium.WebElement> items = driver.findElements(inventoryItems);
        for (org.openqa.selenium.WebElement item : items) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equalsIgnoreCase(itemName)) {
                item.findElement(By.tagName("button")).click();
                break;
            }
        }
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }

    public String getCartItemCount() {
        return driver.findElement(cartBadge).getText();
    }
	
}
