package uz.mk.onlinestoreapp.projection;

import java.math.BigInteger;

public interface HighDemandProduct {
    Integer getProductId();

    BigInteger getCountOfOrdered();
}
