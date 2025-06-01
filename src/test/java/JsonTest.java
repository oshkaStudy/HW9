import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.TransportOrder;
import pojo.Item;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    @Test
    @DisplayName("Проверка JSON-файла на соответствие данных")
    void jsonFileParsingTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("response.json")) {
            TransportOrder order = mapper.readValue(is, TransportOrder.class);

            //Данные order
            assertEquals(8374, order.getNumber());
            assertEquals("b6b3d217-bd1d-4688-980a-f90ac3aa8fb0", order.getOrderId());
            assertEquals("2025-05-05T07:11:53.363Z", order.getCreated());

            //Статус
            assertEquals(6, order.getStatus().getId());
            assertEquals("6", order.getStatus().getCode());
            assertEquals("Требует уточнения", order.getStatus().getName());

            //Груз
            assertEquals(12.000, order.getCargo().getTotalVolume());
            assertEquals(2.000, order.getCargo().getTotalWeight());
            assertEquals("Российский рубль", order.getCargo().getTotalPrice().getCurrency());
            assertEquals(1234.000, order.getCargo().getTotalPrice().getValue());

            //Массив items
            List<Item> items = order.getItems();
            assertEquals(2, items.size());

            assertEquals("Изолирующая монолитная муфта ИММ-530-12-ХЛ", items.get(0).getName());
            assertEquals(12, items.get(0).getQuantity());

            assertEquals("Прожекторная мачта ПМС35м43", items.get(1).getName());
            assertEquals(7, items.get(1).getQuantity());
        }
    }
}
