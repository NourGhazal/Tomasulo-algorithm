
public class MultReservationStation {
	private Operation op;
	private int vk;
	private int vj;
	private String qk;
	private String qj;
	boolean busy;
	private int writtenAt;
	
	
	public int getWrittenAt() {
		return writtenAt;
	}
	public void setWrittenAt(int writtenAt) {
		this.writtenAt = writtenAt;
	}
	public Operation getOp() {
		return op;
	}
	public void setOp(Operation op) {
		this.op = op;
	}
	public int getVk() {
		return vk;
	}
	public void setVk(int vk) {
		this.vk = vk;
	}
	public int getVj() {
		return vj;
	}
	public void setVj(int vj) {
		this.vj = vj;
	}
	public String getQk() {
		return qk;
	}
	public void setQk(String qk) {
		this.qk = qk;
	}
	public String getQj() {
		return qj;
	}
	public void setQj(String qj) {
		this.qj = qj;
	}
	public boolean isBusy() {
		return busy;
	}
	public void setBusy(boolean busy) {
		this.busy = busy;
	}
	public void reset() {
		this.op=null;
		this.vk=0;
		this.vj=0;
		this.qk=null;
		this.qj=null;
		this.busy=false;
		this.setWrittenAt(0);
	}
	public void print(){
		System.out.print("Operation => "+this.op);
		System.out.print(" || VK => "+this.vk);
		System.out.print(" || VJ => "+this.vj);
		System.out.print(" || QK => "+this.qk);
		System.out.print(" || QJ => "+this.qj);
		System.out.print(" || busy => "+this.busy);
		System.out.print(" || writtenA => "+this.writtenAt);
	}

}
