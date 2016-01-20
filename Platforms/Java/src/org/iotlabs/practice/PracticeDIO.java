package org.iotlabs.practice;

import java.io.IOException;
import jdk.dio.gpio.GPIOPin
import jdk.dio.DeviceManager

public class PracticeDIO {

	public static void main(String[] args) {
		try {
			GPIOPin pin = (GPIOPin) DeviceManager.open( 18 );
			boolean flag = false;
			
			for( int i=0; i< 5; i++ ) {
				pin.setValue(flag);
				Thread.sleep(1000 * 2) // 2sec
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
