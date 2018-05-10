package springcoban;

public class Vietnamese implements IPerson {
	private String name;
	private int id;
	private String abc;
	public void setAbc(String abc) {
		this.abc = abc;
	}
	public String talk() {
		
		return "Tôi là người Việt"+name+"-"+id+"-"+abc;
	}
	public Vietnamese(String name, int id) {
		this.name = name;
		this.id = id;
	}
	

}
