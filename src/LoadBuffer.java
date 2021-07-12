
public class LoadBuffer {
private boolean busy;
private int address;
private String q;
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
public String getQ() {
	return q;
}
public void setQ(String q) {
	this.q = q;
}
public void reset() {
	this.busy=false;
	this.address=0;
	this.q=null;
	this.writtenAt=0;
}

public void print(){
	System.out.print("Address "+this.address);
	System.out.print("|| Q "+this.q);
    System.out.print("|| busy "+this.busy);
	System.out.print("|| writtenA "+this.writtenAt);
}
}
