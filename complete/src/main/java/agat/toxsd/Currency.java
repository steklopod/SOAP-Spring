package agat.toxsd;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "currency")
@XmlEnum
public enum Currency {
    GBP,
    EUR,
    PLN;

    public String value() {
        return name();
    }
    public static Currency fromValue(String v) {
        return valueOf(v);
    }

}
