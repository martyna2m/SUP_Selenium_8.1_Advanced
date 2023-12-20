package models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class FilterRange {
    private BigDecimal from;
    private BigDecimal to;
    private Map<String, BigDecimal> rangeValues;

    public FilterRange(BigDecimal from, BigDecimal to) {
        this.from = from;
        this.to = to;

        rangeValues = new HashMap<>();
        rangeValues.put("from", this.from);
        rangeValues.put("to", this.to);

    }

    public BigDecimal getValue(String name) {
        return rangeValues.getOrDefault(name.toLowerCase(), null);
    }

}
