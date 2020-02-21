package kz.dits.billing.service.filter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Filter class for {@link ZonedDateTime} type attributes.
 *
 * @see RangeFilter
 */
public class ZonedDateTimeFilter extends RangeFilter<ZonedDateTime> {

    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public void setEquals(ZonedDateTime equals) {
        super.setEquals(equals);
    }

    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public void setGreaterThan(ZonedDateTime equals) {
        super.setGreaterThan(equals);
    }

    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public void setGreaterOrEqualThan(ZonedDateTime equals) {
        super.setGreaterOrEqualThan(equals);
    }

    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public void setLessThan(ZonedDateTime equals) {
        super.setLessThan(equals);
    }

    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public void setLessOrEqualThan(ZonedDateTime equals) {
        super.setLessOrEqualThan(equals);
    }

    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public void setIn(List<ZonedDateTime> in) {
        super.setIn(in);
    }
}
