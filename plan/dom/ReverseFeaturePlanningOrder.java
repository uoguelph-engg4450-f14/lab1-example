package plan.dom;

import java.util.*;

/** Compares Features by in-plan desirability.
    More highly desirable features are &lt; less desirable ones
    in order that sort can sort them so that the most desired ones come out first.
    <p>
    The criteria are
    <ol>
        <li>higher priority
        <li>greater cumulative customer desirability
        <li>smaller sizing
    </ol>
 */
class ReverseFeaturePlanningOrder implements Comparator {
    /** Ensure only one instance. */
    private ReverseFeaturePlanningOrder() { }

    /** Returns the unique instance of this comparator */
    public static Comparator get() {
        return theInstance;
    }

    /** Compares two features to see which is least desirable to be in-plan.
     */
    public int compare(Object o1, Object o2) {
        Feature f1 = (Feature)o1;
        Feature f2 = (Feature)o2;
        // Return values:
        //    -1: f1 < f2
        //     0: f1 == f2
        //    +1: f1 > f2

        // priority first
        int c = f1.getPriority().compareTo(f2.getPriority());
        if (c != 0) return -c;

        // then desirability
        c = new Double(f1.customerDesirability()).compareTo(new Double(f2.customerDesirability()));
        if( c!= 0 ) return -c;

        // finally sizing
        return new Double(f1.getSizing()).compareTo(new Double(f2.getSizing()));
    }

    /** Compares two features for equal in-plan desirability. */
    public boolean equals(Object o1, Object o2) {
        return compare(o1,o2)==0;
    }


    private static Comparator theInstance = new ReverseFeaturePlanningOrder();
}
