package ua.com.tracksee.dto;

import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.UserEntity;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Ruslan Gunavardana
 */
public class TaxiOrdersDTO {
    private BigInteger count;
    private TaxiOrderDTO[] items;

    public TaxiOrdersDTO(BigInteger count, List<TaxiOrderEntity> entities) {
        this.count = count;
        if (entities != null) {
            items = new TaxiOrderDTO[entities.size()];
            for (int i = 0; i < entities.size(); i++) {
                items[i] = new TaxiOrderDTO(entities.get(i));
            }
        }
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }

    public TaxiOrderDTO[] getItems() {
        return items;
    }

    public void setItems(TaxiOrderDTO[] items) {
        this.items = items;
    }
}
