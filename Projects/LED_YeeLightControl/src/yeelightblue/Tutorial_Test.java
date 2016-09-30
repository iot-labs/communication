package yeelightblue;

import java.util.Scanner;

public class Tutorial_Test {
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		// OS : Linux 기반
		// Lib : commons-logging-1.1.1.jar / expectj-2.0.7.jar
		
		
		String device_no = "hci0"; 
		// Device Number
		
		YeeLightBlue x;
		
		// 제어 할 YeeLight MaxAddress 를 입력 
		x = new YeeLightBlue("78:A5:04:57:01:3F", device_no);
				
		Scanner scan = new Scanner(System.in);
		int ms;
		int ds;
		int cs;
		
		
		Thread.sleep(500);
		System.out.println("BLE 통신 완료");
		
		while(true){
			// dim : 밝기 조정
			System.out.print("1.on 2.off 3.dim 4.color: ");
			ms = scan.nextInt();
			if (ms == 1) {
				x.turnOn();
			} else if (ms == 2) {
				x.turnOff();
			} else if (ms == 3) {
				System.out.print("Insert num:");
				ds = scan.nextInt();
				x.intcontrol(255, 255, 255, ds);
			} else {
				System.out.print("1.red 2.blue 3.green: ");
				cs = scan.nextInt();
				if (cs == 1) {
					x.intcontrol(255, 0, 0, 100);
				} else if (cs == 2) {
					x.intcontrol(0, 0, 255, 100);
				} else if (cs == 3) {
					x.intcontrol(0, 255, 0, 100);
				}
			}
			Thread.sleep(2000);
		}
		
		
	}
}
