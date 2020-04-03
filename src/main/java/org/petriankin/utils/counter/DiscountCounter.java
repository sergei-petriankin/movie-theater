package org.petriankin.utils.counter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountCounter {

    private Long id;
    private String discountStrategyName;
    private Long count;
}
