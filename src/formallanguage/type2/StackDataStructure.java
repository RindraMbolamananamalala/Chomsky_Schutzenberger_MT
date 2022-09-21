package formallanguage.type2;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class StackDataStructure extends ArrayList<Object>{
	
	public void push(Object object) {
		this.add(object);
	}
	
	public Object pop(){
		Object result=this.get(this.size() - 1);
		this.remove(this.size() - 1);
		return result;
	}
	
	public Object peek() {
		return this.get(this.size() - 1);
	}
}