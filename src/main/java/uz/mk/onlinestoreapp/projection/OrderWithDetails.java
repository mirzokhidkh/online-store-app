package uz.mk.onlinestoreapp.projection;

import java.math.BigInteger;
import java.util.Date;

public interface OrderWithDetails {
    Integer getOrderId();

    Date getDate();

    BigInteger getTotalPrice();
}
