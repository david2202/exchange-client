package au.com.david.exchange;

/**
 * Generic interface for building one object from another.
 * 
 * @author howed
 * 
 * @param <I>
 *            Input object type.
 * @param <O>
 *            Output object type.
 */
public interface Builder<I, O> {

	/**
	 * Builds a new object.
	 * 
	 * @param source
	 *            The source object.
	 * @return The built object.
	 */
	O build(I source);
}
