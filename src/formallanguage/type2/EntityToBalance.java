package formallanguage.type2;

public class EntityToBalance{
	private String label;
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	private int position;
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public EntityToBalance() {
	}
	
	public EntityToBalance(String label, int position) {
		setLabel(label);
		setPosition(position);
	}
	
	@Override
	public String toString() {
		return "{" + getLabel() + " : " + getPosition() + "}";
	}
}