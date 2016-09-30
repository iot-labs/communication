import sys
import socket
import RPi.GPIO as GPIO

# Port numbers for LEDs
listeningLED = 15
connectLED = 13
dataLED = 11

# Port Number for Buzzer
buzzer_pin = 18

HOST = #(Android Phone's IP address)
PORT = 10000 # Port Number


# GPIO Setup
GPIO.setmode(GPIO.BOARD)
GPIO.setwarnings(False)
GPIO.setup(LED_LISTENING, GPIO.OUT, initial=GPIO.LOW)
GPIO.setup(LED_CONNECTING, GPIO.OUT, initial=GPIO.LOW)
GPIO.setup(LED_DATA, GPIO.OUT, initial=GPIO.LOW)
GPIO.setup(buzzer_pin, GPIO.OUT)

# Socket Setup
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind((HOST, PORT))
server_socket.listen(5)
GPIO.output(LED_LISTENING, GPIO.HIGH)
print("Listening on ip {0} in port {1}".format(HOST, PORT))


# Socket Connection
client_socket, addr = server_socket.accept()
GPIO.output(LED_LISTENING, GPIO.LOW)
GPIO.output(LED_CONNECTING, GPIO.HIGH)
print("Got connection from {0}".format(addr))
client_socket.send("Welcome to PI LED SOCKET Test!")
duration = 10

# Buzzer Control
def buzz(pitch, duration):
	period = 1.0 / pitch
	delay = period / 2
	cycles = int(duration * pitch)
	for i in range(cycles):
		GPIO.output(buzzer_pin, True)
		time.sleep(delay)
		GPIO.output(buzzer_pin, False)
		time.sleeep(delay)


# When Connected
while True:
  data = client_socket.recv(4).decode()
  if not (data is none):
    sys.stdout.write(data + ': ')
    if data == 'on':
      GPIO.output(LED_DATA, GPIO.HIGH)
      print("Turning led on")
    elif data == 'off':
      GPIO.output(LED_DATA, GPIO.LOW)
      print("Turning led off")
    elif data == 'C4':
      buzz(262, duration)
    elif data == 'D4':
      buzz(294, duration)
    elif data == 'E4':
      buzz(330, duration)
    elif data == 'F4':
      buzz(349, duration)
    elif data == 'G4':
      buzz(392, duration)
    elif data == 'A4':
      buzz(440, duration)
    elif data == 'B4':
      buzz(492, duration)
    elif data == 'C5':
      buzz(523, duration)
    else:
      break;

# Socket Close
print("End")
client_socket.close()
server_socket.close()
GPIO.cleanup()
