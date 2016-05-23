package org.iotlabs.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Java 에서 Linux Console 명령을 호출 한다.
 * @author JongKwang
 *
 */
public class ConsoleCmd {
	
	public ConsoleCmd() {
		
	}
	
	public static void main(String[] args) {
		System.out.println("- ConsoleCmd 시작 #####");
		String s = null;
		
		try {
			Process process = new ProcessBuilder("ls", "-al").start();

			// 출력 읽어 오기
			BufferedReader stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			// 읽어온 출력을 화면에 표시
			while ((s = stdOut.readLine()) != null)
				System.out.println(s);
			while ((s = stdError.readLine()) != null)
				System.err.println(s);

			System.out.println("- process.exitValue() : " + process.exitValue());
			System.exit(process.exitValue());
			
		} catch (IOException e) { // 에러 처리
			e.printStackTrace();
		}
	}
}