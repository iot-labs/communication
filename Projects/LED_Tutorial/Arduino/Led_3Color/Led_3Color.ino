// 긴 PIN 옆의 1개의 핀이 Red 
// 긴 PIN = GND
// 긴 Pin 옆으로 순서대로 Blue, Green
int RED_PIN = 9;
int GREEN_PIN = 10;
int BLUE_PIN = 11;

void setup() {
  pinMode(RED_PIN, OUTPUT);
  pinMode(GREEN_PIN, OUTPUT);
  pinMode(BLUE_PIN, OUTPUT);
}

void loop() {
  //setColor(0, 255, 255); // red
  //setColor(255, 0, 255); // Green
  //setColor(255, 255, 0); // Blue
}

void setColor(int red, int green, int blue)
{
  analogWrite(RED_PIN, red);
  analogWrite(GREEN_PIN, green);
  analogWrite(BLUE_PIN, blue); 
}
