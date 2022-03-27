import java.io.Serializable;

/**
 * Class for combining two objects into a single object.
 */
public class ObjectCombiner implements Serializable {
    Object object1;
    Object object2;

    /**
     * Constructs an object from two other objects.
     * @param object1 the first object to combine
     * @param object2 the second object to combine
     */
    public ObjectCombiner(Object object1, Object object2) {
        this.object1 = object1;
        this.object2 = object2;
    }

    /**
     * Returns the first object from combined object.
     * @return the first object saved into the file
     */
    public Object getObject1() {
        return object1;
    }

    /**
     * Returns the second object from combined object.
     * @return the second object saved into the file
     */
    public Object getObject2() {
        return object2;
    }
}