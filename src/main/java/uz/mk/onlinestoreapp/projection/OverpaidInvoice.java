package uz.mk.onlinestoreapp.projection;

import java.math.BigInteger;

public interface OverpaidInvoice {
    Integer getInvoiceId();

    BigInteger getReimbursedValue();
}
