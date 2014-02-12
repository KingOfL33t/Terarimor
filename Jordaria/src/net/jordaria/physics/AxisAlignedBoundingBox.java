package net.jordaria.physics;

/**
 * An AABB for various purposes.
 * 
 * @author Ches Burks
 *
 */
public class AxisAlignedBoundingBox {
	public double minX;
	public double minY;
	public double maxX;
	public double maxY;

	/**
	 * Construct a new AABB with the given values.
	 * 
	 * @param xmin The minimum x value
	 * @param ymin The minimum y value
	 * @param xmax The maximum x value
	 * @param ymax The maximum y value
	 */
	public AxisAlignedBoundingBox(double xmin, double ymin, double xmax, double ymax){
		this.minX = xmin;
		this.minY = ymin;
		this.maxX = xmax;
		this.maxY = ymax;
	}
	
	/**
	 * Sets the given values.
	 * 
	 * @param xmin The minimum x value
	 * @param ymin The minimum y value
	 * @param xmax The maximum x value
	 * @param ymax The maximum y value
	 */
	public AxisAlignedBoundingBox setBounds(double xmin, double ymin, double xmax, double ymax){
		this.minX = xmin;
		this.minY = ymin;
		this.maxX = xmax;
		this.maxY = ymax;
		return this;
	}

	/**
	 * Expands the AABB by the given amount in both directions.
	 * 
	 * @param x Distance to expand x by
	 * @param y Distance to expand y by
	 * 
	 * @return The same AABB
	 */
	public AxisAlignedBoundingBox expand(double x, double y)
	{
		this.minX -= x;
		this.minY -= y;
		this.maxX += x;
		this.maxY += y;
		return this;
	}

	/**
	 * Contracts the AABB by the given amount in both directions.
	 * 
	 * @param x Distance to contract x by
	 * @param y Distance to contract y by
	 * 
	 * @return The same AABB
	 */
	public AxisAlignedBoundingBox contract(double x, double y)
	{
		this.minX += x;
		this.minY += y;
		this.maxX -= x;
		this.maxY -= y;
		return this;
	}

	/**
	 * Returns true if the two AABB's intersect, false otherwise.
	 * 
	 * @param other The other AABB to test collision with
	 * @return True if they intersect, false otherwise
	 */
	public boolean intersectsWith(AxisAlignedBoundingBox other)
	{
		return other.maxX > this.minX && other.minX < this.maxX 
				? (other.maxY > this.minY && other.minY < this.maxY) : false;
	}
	
	/**
	 * Returns true if this is inside the other AABB, false otherwise.
	 * 
	 * @param other The other AABB to test collision with
	 * @return True if this is completely inside the other, false otherwise
	 */
	public boolean isInside(AxisAlignedBoundingBox other)
	{
		return other.maxX > this.maxX && other.minX < this.minX 
				? (other.maxY > this.maxY && other.minY < this.minY) : false;
	}

	/**
	 * Copy the values from the other AABB to this AABB.
	 * 
	 * @param other The AABB to copy values from
	 */
	public void copyValues(AxisAlignedBoundingBox other)
	{
		this.minX = other.minX;
		this.minY = other.minY;
		this.maxX = other.maxX;
		this.maxY = other.maxY;
	}
}
