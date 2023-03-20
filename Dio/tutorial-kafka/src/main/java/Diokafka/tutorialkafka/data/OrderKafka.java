package Diokafka.tutorialkafka.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderKafka {

    private String code;
    private String nameProduct;
    private BigDecimal value;

}
