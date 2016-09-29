
#ifndef __JSN260_H__
#define __JSN260_H__

#include <Arduino.h>
#include <Stream.h>

#define DEFAULT_WAIT_RESPONSE_TIME      15000         
#define DEFAULT_BAUDRATE                9600
#define MAX_CMD_LEN                     256
#define MAX_TRY_JOIN                    3

class JSN270 : public Stream
{
public:
    JSN270(Stream *);
    JSN270(Stream &);

    size_t write(uint8_t);
    virtual size_t write(const uint8_t *, size_t);
    virtual int available();
    virtual int read();
    virtual int peek();
    virtual void flush();
    void reset();
    void prompt();
   	
    boolean leave();   
    boolean join(const char *ssid, const char *phrase, const char *auth);
    boolean staticIP(const char *ip, const char *mask, const char *gateway);
    boolean dynamicIP();
	
	boolean mqtt_set(const char *host, uint16_t port, const char *id, const char *pw, const char *sub_topic, const char *pub_topic);
	
	boolean mqtt_sub(void);
	
	boolean mqtt_pub(const char *message);

    boolean client(const char *host, uint16_t port, const char *protocol);
    boolean server(uint16_t localport, const char *protocol);
    boolean connected();
    boolean disconnect();
 
    int send(const char *data, int timeout = DEFAULT_WAIT_RESPONSE_TIME);
    int send(const uint8_t *data, int len, int timeout = DEFAULT_WAIT_RESPONSE_TIME);
    int receive(uint8_t *buf, int len, int timeout = DEFAULT_WAIT_RESPONSE_TIME);

    boolean ask(const char *q, const char *a, int timeout = DEFAULT_WAIT_RESPONSE_TIME);
    boolean sendCommand(const char *cmd, const char *ack = NULL, int timeout = DEFAULT_WAIT_RESPONSE_TIME);
 
    void clear();

private:
    static JSN270  *instance;

    Stream *serial;

    boolean command_mode;
    boolean associated;
    uint8_t dhcp;
    uint8_t error_count;

};

#endif // __JSN260_H__

