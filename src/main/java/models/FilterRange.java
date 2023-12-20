package models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FilterRange {
    private BigDecimal from;
    private BigDecimal to;

    public FilterRange(BigDecimal from, BigDecimal to) {
        this.from = from;
        this.to = to;
    }
}
