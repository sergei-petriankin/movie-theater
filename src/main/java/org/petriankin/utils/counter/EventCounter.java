package org.petriankin.utils.counter;

import lombok.Getter;
import lombok.Setter;
import org.petriankin.utils.counter.enums.EventCounterCases;

@Getter
@Setter
public class EventCounter {
    private Long id;
    private EventCounterCases eventCounterCases;
    private Long count;
}
