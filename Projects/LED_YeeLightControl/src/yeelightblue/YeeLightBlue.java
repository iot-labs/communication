package yeelightblue;
import java.io.IOException;

import expectj.ExpectJ;
import expectj.Spawn;
import expectj.TimeoutException;

public class YeeLightBlue {

   Process p;
   String hexStr;
   ExpectJ expectinator = new ExpectJ();
   Spawn con;
   String pattern = ">";
   long time = 600;
   public YeeLightBlue(String mac_str, String device_no)
   {
      this.connect(mac_str, device_no);
   }
   
   public void discover()
   {
      //TODO: Some codes to use lescan to find the YeeLightBlue
   }
   
   public void connect(String mac_str, String device_no)
   {
      String[] cmdArr1 = {"sudo", "hciconfig", device_no, "down"};
      String[] cmdArr2 = {"sudo", "hciconfig", device_no, "up"};
      String[] cmdArr3 = {"sudo", "hcitool", "-i", device_no, "lecc", mac_str};
      
         try {
            p = Runtime.getRuntime().exec(cmdArr1);
            Thread.sleep(1000);
            p = Runtime.getRuntime().exec(cmdArr2);
            Thread.sleep(1000);
            p = Runtime.getRuntime().exec(cmdArr3);
            Thread.sleep(2000);
            con = expectinator.spawn("sudo gatttool -i hci0 -b 78:A5:04:57:01:3F -I");
            con.expect(pattern, time);
            con.send("connect\n");   

         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         
   }
   
   public void turnOff()
   {
      try {
         con.send("char-write-cmd 0x0025 2c2c2c302c2c2c2c2c2c2c2c2c2c2c2c2c2c\n");
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   public void turnOn()
   {
      try {
         con.send("char-write-cmd 0x0025 2c2c2c3130302c2c2c2c2c2c2c2c2c2c2c2c\n");
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   /*
   public void redColor()
   {
      try {
         con.send("char-write-cmd 0x0025 3235352c302c302c3130302c2c2c2c2c2c2c\n");
         
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   public void greenColor()
   {
      try {
         con.send("char-write-cmd 0x0025 302c3235352c302c3130302c2c2c2c2c2c2c\n");
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   public void blueColor()
   {
      try {
         con.send("char-write-cmd 0x0025 302c302c3235352c3130302c2c2c2c2c2c2c\n");
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   public void dimXX(int dim)
   {
	  String hexstr="";
	  String str = Integer.toString(dim);
	  for(int i=0;i<str.length();i++)
    	  hexstr = hexstr+String.format("%x",(int)str.charAt(i));
      try {
         con.send("char-write-cmd 0x0025 3235352c3235352c3235352c"+hexstr+"2c2c2c2c\n");
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   public void dimX(int dim)
   {
	  String hexstr="";
	  String str = Integer.toString(dim);
	  for(int i=0;i<str.length();i++)
    	  hexstr = hexstr+String.format("%x",(int)str.charAt(i));
      try {
         con.send("char-write-cmd 0x0025 3235352c3235352c3235352c"+hexstr+"2c2c2c2c2c\n");
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
    public void dim100()
   {
      try {
         con.send("char-write-cmd 0x0025 3235352c3235352c3235352c3130302c2c2c\n");
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   */
   public void str2hex(String a_str)
   {
      String hexStr = null;
      for(int i=0; i<a_str.length(); i++)
         hexStr = hexStr + String.format("%x", (int)a_str.charAt(i));
      this.hexStr = hexStr.substring(4,hexStr.length()-1);
   }
 
   public void intcontrol(int red, int green, int blue, int brightness)
   {
	  String redhexstr="";
	  String greenhexstr="";
	  String bluehexstr="";
	  String brightnesshexstr="";
	  
	  String redstr = Integer.toString(red);
	  String greenstr = Integer.toString(green);
	  String bluestr = Integer.toString(blue);
	  String brightnessstr = Integer.toString(brightness);
	  
	  for(int i=0;i<redstr.length();i++)
		  redhexstr = redhexstr+String.format("%x",(int)redstr.charAt(i));
	  for(int i=0;i<greenstr.length();i++)
		  greenhexstr = greenhexstr+String.format("%x",(int)greenstr.charAt(i));
	  for(int i=0;i<bluestr.length();i++)
		  bluehexstr = bluehexstr+String.format("%x",(int)bluestr.charAt(i));
	  for(int i=0;i<brightnessstr.length();i++)
		  brightnesshexstr = brightnesshexstr+String.format("%x",(int)brightnessstr.charAt(i));
		  
      String a_str = redhexstr + "2c" + greenhexstr + "2c" + bluehexstr + "2c" + brightnesshexstr + "2c";
      
      System.out.println("a_str!!!: "+a_str);
   
      for(int i=a_str.length(); a_str.length()<36; i++){
    	  System.out.println("a_str.length(): "+a_str.length());
         a_str = a_str + "2c";
        // System.out.println("a_str: "+a_str);
      }
      //str2hex(a_str);
      //System.out.println(hexStr);
      System.out.println();
      try {
         con.send("char-write-cmd 0x0025 " + a_str+"\n");
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   /*
   public void control(String red, String green, String blue, String brightness)
   {
	   
      String a_str = red + "," + green + "," + blue + "," + brightness + ",";
      System.out.println("a_str: "+a_str);
   
      for(int i=a_str.length(); i<18; i++){
         a_str = a_str + ",";
         System.out.println("a_str: "+a_str);
      }
      str2hex(a_str);
      System.out.println(hexStr);
      System.out.println();
      try {
         con.send("char-write-cmd 0x0025 " + hexStr+"\n");
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }*/
   public void disconnect(){
      try{
         con.send("disconnect");
         con.send("exit");
      }catch(IOException e){
         
      }
   }
   public void delayON(int time, boolean status)
   {
      String a_str = String.format("%d,%b,");
      for(int i=a_str.length(); i<18; i++)
         a_str = a_str + ",";
      str2hex(a_str);
      System.out.println(hexStr);
      try {
         con.send("char-write-cmd 0x0025 " + hexStr);
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   public void delayOnStatusQuery()
   {
      try {
         con.send("char-write-cmd 0x00__ RT");
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}