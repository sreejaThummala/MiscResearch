public class Node {
	public int residualPower;
	public Coordinates coords = new Coordinates();
	public Node parent;
	
	Node(){
		coords = new Coordinates();
	}
	public int getResidualPower() {
		return residualPower;
	}
	public void setResidualPower(int residualPower) {
		this.residualPower = residualPower;
	}
	public Coordinates getCoords() {
		return coords;
	}
	public void setCoords(Coordinates coords) {
		this.coords = coords;
	}
	public Node getParent() {
		return parent;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coords == null) ? 0 : coords.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + residualPower;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (coords == null) {
			if (other.coords != null)
				return false;
		} else if (!coords.equals(other.coords))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (residualPower != other.residualPower)
			return false;
		return true;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}

}
