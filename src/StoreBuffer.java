
public class StoreBuffer {
	private boolean busy;
	private int address;
	private int value;
	private String q;
	private String qValue;
	private int writtenAt;
	
	public int getWrittenAt() {
		return writtenAt;
	}
	public void setWrittenAt(int writtenAt) {
		this.writtenAt = writtenAt;
	}
	public boolean isBusy() {
		return busy;
	}
	public void setBusy(boolean busy) {
		this.busy = busy;
	}
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	
	public String getQValue() {
		return qValue;
	}
	public void setQValue(String qValue) {
		this.qValue = qValue;
	}
	
	public void reset() {
		this.busy=false;
		this.address=0;
		this.value=0;
		this.q=null;
		this.qValue=null;
		this.writtenAt=0;
	}
	public void print(){
		System.out.print("Address "+this.address);
		System.out.print("|| Value "+this.value);
		System.out.print("|| Q "+this.q);
		System.out.print("|| QV "+this.qValue);
		
	    System.out.print("|| busy "+this.busy);
		System.out.print("|| writtenA "+this.writtenAt);
	}
}
