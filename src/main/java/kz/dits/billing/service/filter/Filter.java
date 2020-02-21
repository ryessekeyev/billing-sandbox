package kz.dits.billing.service.filter;

import java.util.List;

/**
 * Base class for the various attribute filters. It can be added to a criteria class as a member, to support the
 * following query parameters:
 * <pre>
 *      fieldName.equals='something'
 *      fieldName.specified=true
 *      fieldName.specified=false
 *      fieldName.in='something','other'
 * </pre>
 */
public class Filter<FIELD_TYPE> {

    private FIELD_TYPE equals;
    private Boolean specified;
    private List<FIELD_TYPE> in;

    public FIELD_TYPE getEquals() {
        return equals;
    }

    public void setEquals(FIELD_TYPE equals) {
        this.equals = equals;
    }

    public Boolean getSpecified() {
        return specified;
    }

    public void setSpecified(Boolean specified) {
        this.specified = specified;
    }

    public List<FIELD_TYPE> getIn() {
        return in;
    }

    public void setIn(List<FIELD_TYPE> in) {
        this.in = in;
    }

    @Override
    public String toString() {
        return "Filter ["
            + (getEquals() != null ? "equals=" + getEquals() + ", " : "")
            + (getIn() != null ? "in=" + getIn() : "")
            + (getSpecified() != null ? "specified=" + getSpecified() : "")
            + "]";
    }

}
