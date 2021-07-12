import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Simulator {
private ArrayList<String> instructions =new ArrayList<>();
int cycle;
int [] memory;
AddReservationStation[] addRev;
MultReservationStation[] mulRev;
LoadBuffer[] loadBuf;
StoreBuffer[] storeBuff;
String [] regFile;
int addCycles = 3;
int mulCycles = 5;
int loadCycles =2;
int storeCycles =2;

    public Simulator() {
	this.addRev = new AddReservationStation[3];
	for(int i=0;i<addRev.length;i++)
		addRev[i] = new AddReservationStation();
	this.mulRev = new MultReservationStation[2];
	for(int i=0;i<mulRev.length;i++)
		mulRev[i] = new MultReservationStation();
	this.loadBuf = new LoadBuffer[2];
	for(int i=0;i<loadBuf.length;i++)
		loadBuf[i] = new LoadBuffer();
	this.storeBuff = new StoreBuffer[2];
	for(int i=0;i<storeBuff.length;i++)
		storeBuff[i] = new StoreBuffer();
	regFile=new String[32];
	this.loadRegFile();
	
	//===============================
	memory=new int [100];
	for(int i=0;i<100;i++)
		memory[i]=5;
	memory[10]=777;
	//===============================
}
    
    
public  void loadRegFile() {
	for(int i=0;i<regFile.length;i++) {
		//Dummy value
		regFile[i]="00000000000000000000000000000000";
	}
	regFile[0]="00000000000000000000000000000000";
	regFile[1]="00000000000000000000000000000001";
	regFile[2]="00000000000000000000000000000010";
	/*regFile[3]="00000000000000000000000000000011";
	regFile[4]="00000000000000000000000000000100";
	regFile[5]="00000000000000000000000000000101";
	regFile[6]="00000000000000000000000000000110";
	regFile[7]="00000000000000000000000000000111";
	regFile[8]="00000000000000000000000000001000";
	regFile[9]="00000000000000000000000000001001";
	regFile[10]="00000000000000000000000000001010";
	regFile[11]="00000000000000000000000000001011";
	regFile[12]="00000000000000000000000000001100";
	regFile[13]="00000000000000000000000000001101";
	regFile[14]="00000000000000000000000000001110";
	regFile[15]="00000000000000000000000000001111";
	regFile[16]="00000000000000000000000000010000";
	
	regFile[17]="00000000000000000000000000010001";
	regFile[18]="00000000000000000000000000010010";
	regFile[19]="00000000000000000000000000010011";
	regFile[20]="00000000000000000000000000010100";
	regFile[21]="00000000000000000000000000010101";
	regFile[22]="00000000000000000000000000010110";
	regFile[23]="00000000000000000000000000010111";
	regFile[24]="00000000000000000000000000011000";
	regFile[25]="00000000000000000000000000011001";
	regFile[26]="00000000000000000000000000011010";
	regFile[27]="00000000000000000000000000011011";
	regFile[28]="00000000000000000000000000011100";
	regFile[29]="00000000000000000000000000011101";
	regFile[30]="00000000000000000000000000011110";
	regFile[31]="00000000000000000000000000011111";*/
}
public void getInstructions() {
	System.out.println("Please write $ after finalizing your input :)");
	Scanner sc = new Scanner(System.in);
	while(true) {
		String s = sc.nextLine();
		if(s.equals("$"))
			return;
		if(s.length()==32)
		instructions.add(s);
		else {
			System.out.println("Length Error");
		}
	}
}

public static int convertToDec(String s) {
	int x =0;
	for(int i =0; i<s.length();i++) {
		if(s.charAt(s.length()-i-1)=='1')
			x+= Math.pow(2, i);
	}
	return x;
}
//================================================================

public void start(){
	getInstructions();
	
	while(!instructions.isEmpty()||running()) {
		System.out.println("in Cycle ==>"+cycle);
	
		for (int i = 0; i < instructions.size(); i++) {
			
			String instruction = instructions.get(i);
			boolean inserted = false;
			switch(instruction.substring(0, 6)) {
			case "000000" :inserted=simulateArethmaticOperations(instruction);break;
			case "100011" : inserted =simulateLoad(instruction);break;
			case "101011" : inserted= simulateStore(instruction);break;
			};
			if(inserted)
				instructions.remove(i);
			
			
			
			//======================
			if(inserted)
				break;
			
		}
		check();
		cycle++;
		
		//==========================================================================
		System.out.print("memory[9]="+memory[9]+"  memory[10]="+memory[10]);
		printRegFile();
		System.out.println();
		//==========================================================================
		System.out.println("======================ADD_RISERVATION_STATION=================");
		for (int i = 0; i < addRev.length; i++) {
			addRev[i].print();
			System.out.println();
		}
		System.out.println("======================MUL_RISERVATION_STATION=================");
		for (int i = 0; i < mulRev.length; i++) {
			mulRev[i].print();
			System.out.println();
		}
		
		System.out.println("=====================LOAD_BUFFER=================");
		
		for (int i = 0; i < loadBuf.length; i++) {
			loadBuf[i].print();
			System.out.println();
		}
		
System.out.println("=====================Store_BUFFER=================");
		
		for (int i = 0; i < storeBuff.length; i++) {
			storeBuff[i].print();
			System.out.println();
		}

		System.out.println("//=================================================\\");
		System.out.println("//=================================================\\");
		System.out.println("//=================================================\\");
		System.out.println("//=================================================\\");
		System.out.println("//=================================================\\");
		System.out.println("//=================================================\\");
		System.out.println("//=================================================\\");
		System.out.println("//=================================================\\");
		System.out.println("//=================================================\\");

		if(cycle>25)
			break;
	}
	//TODO: a method to empty reservation stations;put an empty flag for each array
	
}
public void check() {
	checkAdd();
	checkMul();
	checkLoad();
	checkStore();
}
//================================================
public boolean running(){
	boolean flag=false;
	// Add reservations
		for (int i = 0; i < addRev.length; i++) {
			flag=flag||addRev[i].isBusy();
		}
		
		
   // Mul reservations
				for (int i = 0; i < mulRev.length; i++) {
					flag=flag||mulRev[i].isBusy();
				}
				
	// load Buffers
				for (int i = 0; i < loadBuf.length; i++) {
					flag=flag||loadBuf[i].isBusy();
				}
				
	// load Buffers
				for (int i = 0; i < storeBuff.length; i++) {
					flag=flag||storeBuff[i].isBusy();
				}
	
	
	
	
	return flag;
}
//=======================================================================
public void updateAll(int result,String destination){
	//update regfile
	for (int i = 0; i <32; i++) {
		if(regFile[i].equals(destination))
		{
			String bin = Integer.toBinaryString(result);
			while(bin.length()<32)
				bin=0+bin;
			regFile[i]=bin;
		}
	}
	
	
	//udate Add reservations
	for (int i = 0; i < addRev.length; i++) {
		if(addRev[i].getQj()!=null&&addRev[i].getQj().equals(destination)){
			addRev[i].setQj(null);
			addRev[i].setVj(result);
			if(addRev[i].getQj()==null&&addRev[i].getQk()==null)
				addRev[i].setWrittenAt(cycle+addCycles+1);
			
		}
		if(addRev[i].getQk()!=null&&addRev[i].getQk().equals(destination)){
			addRev[i].setQk(null);
			addRev[i].setVk(result);
			if(addRev[i].getQj()==null&&addRev[i].getQk()==null)
				addRev[i].setWrittenAt(cycle+addCycles+1);
		}
		
	}
	
	//udate Mul reservations
		for (int i = 0; i < mulRev.length; i++) {
			if(mulRev[i].getQj()!=null&&mulRev[i].getQj().equals(destination)){
				mulRev[i].setQj(null);
				mulRev[i].setVj(result);
				if(mulRev[i].getQj()==null&&mulRev[i].getQk()==null)
					mulRev[i].setWrittenAt(cycle+mulCycles+1);
				
			}
			if(mulRev[i].getQk()!=null&&mulRev[i].getQk().equals(destination)){
				mulRev[i].setQk(null);
				mulRev[i].setVk(result);
				if(mulRev[i].getQj()==null&&mulRev[i].getQk()==null)
					mulRev[i].setWrittenAt(cycle+mulCycles+1);
			}
			
		}
	
	
	
	//update loadBuffers
		for (int i = 0; i < loadBuf.length; i++) {
			if(loadBuf[i].getQ()!=null&&loadBuf[i].getQ().equals(destination)){
				loadBuf[i].setQ(null);
				
				loadBuf[i].setAddress(loadBuf[i].getAddress()+result);
				loadBuf[i].setWrittenAt(cycle+loadCycles+1);
				
			}
			}
		//update StoreBuffers
		for (int i = 0; i < storeBuff.length; i++) {
			if(storeBuff[i].getQ()!=null&&storeBuff[i].getQ().equals(destination)){
				storeBuff[i].setQ(null);
				
				storeBuff[i].setAddress(storeBuff[i].getAddress()+result);
				//storeBuff[i].setWrittenAt(cycle+storeCycles+1);
				if(storeBuff[i].getQValue()==null&&storeBuff[i].getQ()==null)
					storeBuff[i].setWrittenAt(cycle+storeCycles+1);
			}
			
			if(storeBuff[i].getQValue()!=null&&storeBuff[i].getQValue().equals(destination)){
				storeBuff[i].setQValue(null);
				
				storeBuff[i].setValue(result);
				//storeBuff[i].setWrittenAt(cycle+storeCycles+1);
				if(storeBuff[i].getQValue()==null&&storeBuff[i].getQ()==null)
					storeBuff[i].setWrittenAt(cycle+storeCycles+1);
				}
			
				
			}
}


public void checkAdd() {
	int result;
for (int i = 0; i < addRev.length; i++) {
	if(addRev[i].getWrittenAt()==cycle&&cycle!=0){
		if(addRev[i].getOp()==Operation.ADD)
	result=addRev[i].getVj()+addRev[i].getVk();
		else
	result=addRev[i].getVj()-addRev[i].getVk();
		
		updateAll(result,"Add "+i);
		addRev[i].reset();
	}
	
}

}
public void checkMul() {
	int result;
	for (int i = 0; i < mulRev.length; i++) {
		if(mulRev[i].getWrittenAt()==cycle&&cycle!=0){
			if(mulRev[i].getOp()==Operation.MUL)
		result=mulRev[i].getVj()*mulRev[i].getVk();
			else
		result=mulRev[i].getVj()/mulRev[i].getVk();
			
			updateAll(result,"Mul "+i);
			mulRev[i].reset();
		}
		
	}
}
public void checkLoad() {
	/*we have a memory size of 100
	 * and initialy all values are 5
	 * if the adress is greater than 100 we load zero 
	 *
	 **/
	int result;
	for (int i = 0; i < loadBuf.length; i++) {
		if(loadBuf[i].getWrittenAt()==cycle&&cycle!=0){
			if(loadBuf[i].getAddress()<100)
		result=memory[loadBuf[i].getAddress()];
			else
		result=0;
			updateAll(result,"Load "+i);
			loadBuf[i].reset();
		}
		
	}
}
public void checkStore() {
	
	for (int i = 0; i < storeBuff.length; i++) {
		if(storeBuff[i].getWrittenAt()==cycle&&cycle!=0){
			if(storeBuff[i].getAddress()<100)
		memory[storeBuff[i].getAddress()]=storeBuff[i].getValue();
			else
		memory[0]=storeBuff[i].getValue();
			
                  storeBuff[i].reset();
		}
		
	}
}
public boolean simulateArethmaticOperations(String instruction) {
	
	
	switch(instruction.substring(26,32)) {///////////////////////////////////
	
		case "100000": 
		case "100010":{
			
			int i = 0;
			for(i=0;i<addRev.length;i++) {
				if(!addRev[i].isBusy())
					break;
			}
			
			if(i==addRev.length)
				return false;
			AddReservationStation ad = addRev[i];
			Operation op = instruction.substring(26,32).equals("100000")?Operation.ADD:Operation.SUB;
			ad.setBusy(true);
			ad.setOp(op);
			//instructions.add("000000$01010$01011$01001$00000$100000");
			int rs = convertToDec(instruction.substring(6,11));
			int rt = convertToDec(instruction.substring(11,16));
			int rd =convertToDec(instruction.substring(16,21));
			
			
				if(regFile[rs].length()==32){
					int vj = convertToDec(regFile[rs]);
					ad.setVj(vj);
				}
				else
					ad.setQj(regFile[rs]);
				
				
		       if(regFile[rt].length()==32){
				int vk = convertToDec(regFile[rt]);
				ad.setVk(vk);
				}
				else
				ad.setQk(regFile[rt]);
		       
			
			if(ad.getQj() ==null && ad.getQk()==null)
				ad.setWrittenAt(this.cycle+addCycles+1);
			
			this.regFile[rd]="Add "+i;
			break;
		}
		case "011000":
		case "011010":{
			
			int i = 0;
			for(i=0;i<mulRev.length;i++) {
				if(!mulRev[i].isBusy())
					break;
			}
			if(i==mulRev.length)
				return false;
			MultReservationStation mul = mulRev[i];
			Operation op = instruction.substring(26,32).equals("011000")?Operation.MUL:Operation.DIV;
			mul.setBusy(true);
			mul.setOp(op);
			int rs = convertToDec(instruction.substring(6,11));
			int rt = convertToDec(instruction.substring(11,16));
			int rd =convertToDec(instruction.substring(16,21));
			
			if(regFile[rs].length()==32){
				int vj = convertToDec(regFile[rs]);
				mul.setVj(vj);
			}
			else mul.setQj(regFile[rs]);
			
			if(regFile[rt].length()==32){
				int vk = convertToDec(regFile[rt]);
				mul.setVk(vk);
			}
			else mul.setQk(regFile[rt]);
			
			
			
			if(mul.getQj() ==null && mul.getQk()==null)
				mul.setWrittenAt(this.cycle+mulCycles+1);
			this.regFile[rd]="Mul "+i;
			break;
		}
	}
	
	return true;
	
}
public boolean simulateLoad(String instruction) {
	int i = 0;
	for(i=0;i<loadBuf.length;i++) {
		if(!loadBuf[i].isBusy())
			break;
	}
	if(i==loadBuf.length)
		return false;
	loadBuf[i].setBusy(true);
	int rs = convertToDec(instruction.substring(6,11));
	int rt =convertToDec(instruction.substring(11,16));
	int address = convertToDec(instruction.substring(16));
	
	
	if(regFile[rs].length()==32){
		int offset = convertToDec(regFile[rs]);
		loadBuf[i].setAddress(address+offset);
	}
else {
		loadBuf[i].setQ(regFile[rs]);
		loadBuf[i].setAddress(address);
		}
	if(loadBuf[i].getQ()==null)
		loadBuf[i].setWrittenAt(this.cycle+loadCycles+1);
	
	this.regFile[rt]="Load "+i;
	return true;
}
public boolean simulateStore(String instruction) {
	int i = 0;
	for(i=0;i<storeBuff.length;i++) {
		if(!storeBuff[i].isBusy())
			break;
	}
	if(i==storeBuff.length)
		return false;
	storeBuff[i].setBusy(true);
	int rs = convertToDec(instruction.substring(6,11));
	int rt =convertToDec(instruction.substring(11,16));
	int address = convertToDec(instruction.substring(16));
	//this.regFile[rt]="Store "+i;            //========================================================================
	
	
	//storeBuff[i].setValue(convertToDec(regFile[rt]));
	
	if(regFile[rt].length()==32){
		storeBuff[i].setValue(convertToDec(regFile[rt]));
	}
	else
	{
		storeBuff[i].setQValue(regFile[rt]);
		
	}
	
	
	
	
	if(regFile[rs].length()==32) {
		int offset = convertToDec(regFile[rs]);
		storeBuff[i].setAddress(address+offset);
	}
	else{
		storeBuff[i].setQ(regFile[rs]);
		storeBuff[i].setAddress(address);
		}
	if(storeBuff[i].getQ()==null&&storeBuff[i].getQValue()==null)
		storeBuff[i].setWrittenAt(this.cycle+storeCycles+1);
	return true;
}


public void printRegFile(){
	int x;
	for (int i = 0; i <16; i++) {
		if(regFile[i].length()==32){
			x=convertToDec(regFile[i]);
			System.out.print(" R"+i+"=>"+x);
		}
		else
			System.out.print(" R"+i+" => "+regFile[i]);
		
		}
	System.out.println();
	for (int i = 16; i < 32; i++) {
		if(regFile[i].length()==32){
			x=convertToDec(regFile[i]);
			System.out.print(" R"+i+"=>"+x);
		}
		else
			System.out.print(" R"+i+" => "+regFile[i]);
		}
}


//============================================
public void dummyIns(){
	
	
	//==========================================================
	//==========================================================
	//==========================================================
	//==========================================================
	
	
	
	
	/* ADD 3 =1+2                   reg3=reg1+reg2        =======> reg3=3;
	 * 00000000001000100001100000100000
	 * 
	 * SW 3 7(2)                      memory[7+reg2]=reg3  ====>memory[9]=3
	 * 10101100010000110000000000000111
	  
	 * ADD 3 =3+3                    reg3=reg3+reg3       =====>reg3=6
	 * 00000000011000110001100000100000
	 * 
	 * MUL reg0=reg1*reg2                                 ======>   reg0=2
	 * 00000000001000100000000000011000
	 * 
	 * 
	 * 
	 * 
	 * LW 8 4(3)                       reg8=memory[4+reg3]     =======> reg8=memory[10]=777
	 * 10001100011010000000000000000100
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	
	
}

//============================================
public static void main(String[]args) {
	
	Simulator simu = new Simulator();
	//simu.dummyIns();
	simu.start();

	
}
}
