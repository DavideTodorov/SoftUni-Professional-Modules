package ex05BillsPaymentSystem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Month;
import java.time.Year;

@Entity
@Table(name = "credit_cards")
public class CreditCard extends BillingDetail{

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "expiration_month")
    private Month expirationMonth;

    @Column(name = "expiration_yeard")
    private Year expirationYear;



}
