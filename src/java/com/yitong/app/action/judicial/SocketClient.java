package com.yitong.app.action.judicial;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.yitong.commons.model.Consts;

/**
 * @author cailiang 2010-11-25 ����02:24:50
 * @SocketClient.java
 */

/**
 * socket��������
 * 
 * 
 * 
 * 
 */
public class SocketClient {

	private boolean connected = false;

//	public static void main(String args[]) throws IOException,
//			InterruptedException {

//		SocketClient client = new SocketClient();
//		 boolean flag = client.add();
		// Thread.sleep(5 * 1000);
//		 System.out.println(flag);
//		 boolean b = client.minus();
//		 System.out.println(b + "====");
		// client.disConnect();
		// client.reset();

//	}

	public boolean add() throws IOException {
		byte[] _rst = new byte[20];
		try {
			if (connect()) {
				send("0001");
				in.read(_rst);
			}
		} catch (Exception e) {
			return false;
		} finally {
			connected = false;
			close();
		}
		String rst = new String(_rst).trim();
		if ("success".equals(rst)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean minus() throws IOException {
		byte[] _rst = new byte[20];
		try {
			if (connect()) {
				send("0002");
				in.read(_rst);
			}
		} catch (Exception e) {
			return false;
		} finally {
			connected = false;
			close();
		}
		String rst = new String(_rst).trim();
		if ("success".equals(rst)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean reset() throws IOException {

		byte[] _rst = new byte[20];
		try {
			if (connect()) {
				send("0003");
				in.read(_rst);
			}
		} catch (Exception e) {
			return false;
		} finally {
			connected = false;
			close();
		}
		String rst = new String(_rst).trim();
		if ("success".equals(rst)) {
			return true;
		} else {
			return false;
		}

	}

	protected boolean connect() throws IOException {
		if (!connected) {
			open();
			return true;
		}
		return false;
	}

	// public void disConnect() throws IOException {
	// send("close");
	// connected = false;
	// close();
	// }

	protected Socket socket;
	protected DataOutputStream out;
	protected DataInputStream in;

	private void open() throws IOException {
		closeNotException();
		socket = new Socket(Consts.SOCKETIP,Integer.parseInt(Consts.SOCKETPORT));
		
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		connected = true;
	}

	protected void send(String msg) throws IOException {
		if (checkLink()) {
			open();
		}
		out.write(msg.getBytes());
		out.flush();
	}

	private boolean checkLink() {
		return !socket.isConnected() && !socket.isClosed();
	}

	private void closeNotException() {
		try {
			close();
		} catch (Exception exception) {
		}
	}

	private void close() throws IOException {
		try {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
			if (socket != null) {
				socket.close();
				socket = null;
			}
		} finally {
			socket = null;
		}
	}

}
