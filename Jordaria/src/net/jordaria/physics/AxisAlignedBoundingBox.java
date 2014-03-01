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
	public double minZ;
	public double maxZ;

	/**
	 * Construct a new AABB with the given values.
	 * 
	 * @param xmin The minimum x value
	 * @param ymin The minimum y value
	 * @param zmin The minimum z value
	 * @param xmax The maximum x value
	 * @param ymax The maximum y value
	 * @param zmax The maximum z value
	 */
	public AxisAlignedBoundingBox(double xmin, double ymin, double zmin, double xmax, double ymax, double zmax){
		this.minX = xmin;
		this.minY = ymin;
		this.minZ = zmin;
		this.maxX = xmax;
		this.maxY = ymax;
		this.maxZ = zmax;
	}
	
	/**
	 * Sets the given values.
	 * 
	 * @param xmin The minimum x value
	 * @param ymin The minimum y value
	 * @param zmin The minimum z value
	 * @param xmax The maximum x value
	 * @param ymax The maximum y value
	 * @param zmax The maximum z value
	 */
	public AxisAlignedBoundingBox setBounds(double xmin, double ymin, double zmin, double xmax, double ymax, double zmax){
		this.minX = xmin;
		this.minY = ymin;
		this.minZ = zmin;
		this.maxX = xmax;
		this.maxY = ymax;
		this.maxZ = zmax;
		return this;
	}

	/**
	 * Expands the AABB by the given amount in both directions.
	 * 
	 * @param x Distance to expand x by
	 * @param y Distance to expand y by
	 * @param z Distance to expand z by
	 * 
	 * @return The same AABB
	 */
	public AxisAlignedBoundingBox expand(double x, double y, double z)
	{
		this.minX -= x;
		this.minY -= y;
		this.minZ -= z;
		this.maxX += x;
		this.maxY += y;
		this.maxZ += z;
		return this;
	}

	/**
	 * Contracts the AABB by the given amount in both directions.
	 * 
	 * @param x Distance to contract x by
	 * @param y Distance to contract y by
	 * @param z Distance to contract z by
	 * 
	 * @return The same AABB
	 */
	public AxisAlignedBoundingBox contract(double x, double y, double z)
	{
		this.minX += x;
		this.minY += y;
		this.minZ += z;
		this.maxX -= x;
		this.maxY -= y;
		this.maxZ -= z;
		return this;
	}

	/**
	 * Returns true if the two AABB's intersect, false otherwise. 
	 * They may share edges.
	 * 
	 * @param other The other AABB to test collision with
	 * 
	 * @return True if they intersect, false otherwise
	 */
	public boolean intersectsWith(AxisAlignedBoundingBox other)
	{
		return other.maxX >= this.minX && other.minX <= this.maxX 
				? (other.maxY >= this.minY && other.minY <= this.maxY 
						? other.maxZ >= this.minZ && other.minZ <= this.maxZ : false) : false;
	}
	
	/**
	 * Returns true if this is inside the other AABB, false otherwise. 
	 * They may share edges.
	 * 
	 * @param other The other AABB to test collision with
	 * 
	 * @return True if this is completely inside the other, false otherwise
	 */
	public boolean isInside(AxisAlignedBoundingBox other)
	{
		return other.maxX >= this.maxX && other.minX <= this.minX 
				? (other.maxY >= this.maxY && other.minY <=this.minY 
						? other.maxZ >= this.maxZ && other.minZ <= this.minZ : false) : false;
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
		this.minZ = other.minZ;
		this.maxX = other.maxX;
		this.maxY = other.maxY;
		this.maxZ = other.maxZ;
	}
}
