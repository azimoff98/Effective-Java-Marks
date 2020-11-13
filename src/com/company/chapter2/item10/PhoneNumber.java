package com.company.chapter2.item10;

import java.util.Comparator;

import static java.util.Comparator.*;

public class PhoneNumber implements Comparable<PhoneNumber> {

    private static final Comparator<PhoneNumber> COMPARATOR =
            comparingInt((PhoneNumber pn) -> pn.areaCode)
                    .thenComparing(pn -> pn.prefix)
                    .thenComparing(pn -> pn.lineNum);

    private final short areaCode, prefix, lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "area code");
        this.prefix = rangeCheck(prefix, 999, "prefix");
        this.lineNum = rangeCheck(lineNum, 9999, "line num");
    }

    private static short rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max)
            throw new IllegalArgumentException(arg + ": " + val);
        return (short) val;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PhoneNumber))
            return false;
        PhoneNumber pn = (PhoneNumber) o;
        return pn.lineNum == lineNum && pn.prefix == prefix
                && pn.areaCode == areaCode;
    }

    @Override
    public int hashCode() {
        int result = Short.hashCode(areaCode);
        result = 31 * result + Short.hashCode(prefix);
        result = 31 * result + Short.hashCode(lineNum);
        return result;
    }

    @Override
    public int compareTo(PhoneNumber o) {
//        int result = Short.compare(areaCode, o.areaCode);
//        if(result == 0){
//            result = Short.compare(prefix, o.prefix);
//            if(result == 0){
//                result = Short.compare(lineNum, o.lineNum);
//            }
//        }
//        return result;

        return COMPARATOR.compare(this, o);
    }
}
