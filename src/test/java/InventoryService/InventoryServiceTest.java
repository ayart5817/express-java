package InventoryService;

import ComplexTask2.Task5.InventoryService;
import ComplexTask2.Task5.OutOfStockException;
import ComplexTask2.Task5.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Протестируйте добавление и извлечение товаров, проверив работу флага isInventoryOpen,
 *  фильтрацию по категории и цене, а также обработку исключений при отсутствии товаров.
 *
 *
 */
public class InventoryServiceTest {

    InventoryService inventoryService = new InventoryService();
    Product product1 = new Product("Помидоры", 60000);
    Product product2 = new Product("Огурцы", 30000);
    Product product3 = new Product("Колбаса", 100000);




    @Test
    @DisplayName("Проверка на Добавления валидного продукта на склад")
    public void addInventoryServiceProduct() throws OutOfStockException {
       assertDoesNotThrow(() ->inventoryService.addProduct("Овощи",product1));
    }

    @Test
    @DisplayName("Проверка на получение валидного продукта на склад")
    public void getInventoryServiceProduct() throws OutOfStockException {

       Product expectedProduct = new Product("Огурцы", 30000);
       inventoryService.addProduct("Овощи",expectedProduct);

        List<Product> actualProduct = inventoryService.getProduct("Овощи");
        assertNotNull(actualProduct);
        assertEquals(1, actualProduct.size());
        assertEquals("Огурцы", actualProduct.get(0).getProductName());
        assertEquals(30000, actualProduct.getFirst().getPrice());
    }

    @Test
    @DisplayName("Проверка фильтрации и категории")
    public void filterCategoryInventoryServiceProductTest() throws OutOfStockException {
        InventoryService inventoryService = new InventoryService();
        inventoryService.addProduct("Овощи",product1);
        inventoryService.addProduct("Огурцы",product2);
        inventoryService.addProduct("Бакалея",product3);

        List<Product> actualFilterPrice = inventoryService.filterPrice(30001, 99999);
        assertEquals("Помидоры", actualFilterPrice.getFirst().getProductName());
        actualFilterPrice = inventoryService.filterPrice(30001, 59999);
        assertTrue(actualFilterPrice.isEmpty());

    }

    @Test
    @DisplayName("Проверка исключений пустой товар, не верный фильтр")
    public void exceptionInventoryServiceProductTest() throws OutOfStockException {
        InventoryService inventoryService = new InventoryService();
        Product emptyProduct = new Product("",0);
        assertThrows(OutOfStockException.class, () -> inventoryService.addProduct("Кофе",emptyProduct));
        Product nullProduct = new Product(null,0);
        assertThrows(OutOfStockException.class, () -> inventoryService.addProduct("Кофе",nullProduct));
        assertThrows(NullPointerException.class, () -> inventoryService.addProduct("Пусто",null));
        inventoryService.addProduct("Огурцы", product1);
        assertThrows(IllegalArgumentException.class, () -> inventoryService.filterPrice(10,9));
    }

@Test
    @DisplayName("проверка флага isInventoryOpen")
    public void checkFlag() throws OutOfStockException {
    InventoryService inventoryService = new InventoryService();
    inventoryService.isInventoryOpen = false;
    assertThrows(OutOfStockException.class, ()-> inventoryService.addProduct("Огурцы", product1));

}


}
