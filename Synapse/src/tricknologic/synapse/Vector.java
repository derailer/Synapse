package tricknologic.synapse;

public class Vector {
	public float x, y, z = 0.0f;
	
	Vector() {
		x = y = z = 0.0f;
	}
	
	Vector(float inX, float inY, float inZ) {
		x = inX;
		y = inY;
		z = inZ;
	}
	
	Vector(float inX, float inY) {
		x = inX;
		y = inY;
		z = 0.0f;
	}
	
	Vector add(Vector other) {
		return new Vector(x + other.x, y + other.y, z + other.z);
	}
	
	Vector sub(Vector other) {
		return new Vector(x - other.x, y - other.y, z - other.z);
	}
}
