public class FLamp
{
	public static void main(String[]args){
		FMain led = new FMain();
		FMain halogen = new FMain(); 
	
		halogen.turnOff();
		led.turnOff();
	}
}