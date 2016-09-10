package org.iotlabs.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

//import javax.servlet.http.HttpServletRequest;

/**
 * extends HashMap 을 해서 HashMap 과 비슷한 구조체 클래스 이다.
 *   - StringMap( HttpServletRequest req ) 을 통해 생성 할 수 있다.
 *   - get(String key) 를 통해 항상 String Type 만 취급한다.
 * @author JongKwang
 *
 */
public class StringMap extends HashMap {

    private static final long serialVersionUID = 1L;
    private List list;
    private int listSize;

//	public StringMap( HttpServletRequest req ) {
//		Enumeration e = req.getParameterNames(); 
//		String key = null; 
//		String value = null;
//		while (e.hasMoreElements()){ 
//			key = e.nextElement().toString();
//			value = req.getParameter(key);
//			put(key,value);
//		} 
//	}

    public StringMap( List list ) {
        this.list = list;
        this.listSize = list.size();
    }

    public StringMap(HashMap map) {
        super.putAll(map);
    }

    public String get(String key) {
        String returnValue = null;

        try {
            returnValue = super.get(key).toString().trim();
        } catch (NullPointerException npe) {
            //npe.printStackTrace();
            returnValue = "";
        }

        return returnValue;
    }

    public int getInt(String key) {
        int returnValue = 0;

        try {
            returnValue = Integer.parseInt( this.get(key) );
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            returnValue = 0;
        }

        return returnValue;
    }

    public void print() {
        Iterator iterator = null;
        String key = null;
        StringBuffer returnValue = new StringBuffer();

        iterator = keySet().iterator();
        while (iterator.hasNext()) {
            key = (String) iterator.next();
            returnValue.append(key+":"+get(key)+", ");
        }
        System.out.println( returnValue.toString() );
    }

    // List 처리
    public StringMap get(int i) {
        return new StringMap((HashMap)this.list.get( i ));
    }

    public void set(int i, StringMap map) {
        this.list.set(i, map);
    }

    // List 처리
    public int size() {
        return listSize;
    }

    /**
     * 쉬운 디버깅을 위해   List의 값을 모두 출력한다.
     */
    public void printList() {
        Iterator iterator = null;
        String key = null;

        for( int i = 0; i < listSize; i++) {
            iterator = get(i).keySet().iterator();
            while (iterator.hasNext()) {
                key = (String) iterator.next();
                System.out.print(key+":");
                System.out.print(get(i).get(key)+", ");
            }
        }
    }
}