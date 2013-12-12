package net.jordaria.physics;

public class AxisAlignedBoundingBox {
	public double minX;
	public double minY;
	public double minZ;
	public double maxX;
	public double maxY;
	public double maxZ;

	public AxisAlignedBoundingBox(double xmin, double ymin, double zmin, double xmax, double ymax, double zmax){
		this.minX = xmin;
		this.minY = ymin;
		this.minZ = zmin;
		this.maxX = xmax;
		this.maxY = ymax;
		this.maxZ = zmax;
	}
	public AxisAlignedBoundingBox setBounds(double xmin, double ymin, double zmin, double xmax, double ymax, double zmax){
		this.minX = xmin;
		this.minY = ymin;
		this.minZ = zmin;
		this.maxX = xmax;
		this.maxY = ymax;
		this.maxZ = zmax;
		return this;
	}

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

	public boolean intersectsWith(AxisAlignedBoundingBox other)
	{
		return other.maxX > this.minX && other.minX < this.maxX 
				? (other.maxY > this.minY && other.minY < this.maxY 
						? other.maxZ > this.minZ && other.minZ < this.maxZ : false) : false;
	}

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
