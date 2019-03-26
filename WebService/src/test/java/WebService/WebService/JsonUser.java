package WebService.WebService;

public class JsonUser {
	
	String name;
	String job;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public JsonUser(String name, String job) {
		this.name = name;
		this.job = job;
	}
	
}
