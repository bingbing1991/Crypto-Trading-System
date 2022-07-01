package crypto.trading.system.dto;

public class JsonWrapperObject
{
	private Object data;
	
	private String Status;
	
	private String Description;
	
	public JsonWrapperObject()
	{}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
	
}
