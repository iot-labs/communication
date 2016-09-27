// step1_1 is control LED by digital
// In digital output, there are 0V and 5V

#define LED 2 // LED is matching arduino port D2

// setup() function runs once when arduino start
void setup() {
  // put your setup code here, to run once:
  pinMode(LED, OUTPUT);
}

// loop() function runs repeatedly forever
void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite(LED, HIGH);  // OUTPUT LED(D2) 5V
  delay(1000);              // wait for 1000ms
  digitalWrite(LED, LOW);   // OUTPUT LED(D2) 0V
  delay(1000);              // wait for 1000ms
}
