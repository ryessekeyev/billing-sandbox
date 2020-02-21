package kz.dits.billing.service.filter;

/**
 * Filter class for Comparable types, where less than / greater than / etc relations could be interpreted. It can be
 * added to a criteria class as a member, to support the following query parameters:
 * <pre>
 *      fieldName.equals=42
 *      fieldName.specified=true
 *      fieldName.specified=false
 *      fieldName.in=43,42
 *      fieldName.greaterThan=41
 *      fieldName.lessThan=44
 *      fieldName.greaterOrEqualThan=42
 *      fieldName.lessOrEqualThan=44
 * </pre>
 * Because problems with the type conversions, the descendant classes needs to be used, where the generic type parameter
 * is materalized.
 *
 * @param <FIELD_TYPE> the type of filter.
 * @see IntegerFilter
 * @see DoubleFilter
 * @see FloatFilter
 * @see LongFilter
 * @see LocalDateFilter
 * @see InstantFilter
 */
public class RangeFilter<FIELD_TYPE extends Comparable<? super FIELD_TYPE>> extends Filter<FIELD_TYPE> {

    private FIELD_TYPE greaterThan;
    private FIELD_TYPE lessThan;
    private FIELD_TYPE greaterOrEqualThan;
    private FIELD_TYPE lessOrEqualThan;

    public FIELD_TYPE getGreaterThan() {
        return greaterThan;
    }

    public void setGreaterThan(FIELD_TYPE greaterThan) {
        this.greaterThan = greaterThan;
    }

    public FIELD_TYPE getGreaterOrEqualThan() {
        return greaterOrEqualThan;
    }

    public void setGreaterOrEqualThan(FIELD_TYPE greaterOrEqualThan) {
        this.greaterOrEqualThan = greaterOrEqualThan;
    }

    public FIELD_TYPE getLessThan() {
        return lessThan;
    }

    public void setLessThan(FIELD_TYPE lessThan) {
        this.lessThan = lessThan;
    }

    public FIELD_TYPE getLessOrEqualThan() {
        return lessOrEqualThan;
    }

    public void setLessOrEqualThan(FIELD_TYPE lessOrEqualThan) {
        this.lessOrEqualThan = lessOrEqualThan;
    }

    @Override
    public String toString() {
        return "RangeFilter [" + (getGreaterThan() != null ? "greaterThan=" + getGreaterThan() + ", " : "")
            + (getGreaterOrEqualThan() != null ? "greaterOrEqualThan=" + getGreaterOrEqualThan() + ", " : "")
            + (getLessThan() != null ? "lessThan=" + getLessThan() + ", " : "")
            + (getLessOrEqualThan() != null ? "lessOrEqualThan=" + getLessOrEqualThan() + ", " : "")
            + (getEquals() != null ? "equals=" + getEquals() + ", " : "")
            + (getSpecified() != null ? "specified=" + getSpecified() : "")
            + (getIn() != null ? "in=" + getIn() : "")
            + "]";
    }

}
