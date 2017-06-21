package wifiudp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class UdpSender {
	public static void main(String[] args) throws Exception {

		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(System.in));

		if (args.length != 4) {
			System.err
					.println("Usage: java -jar udpSender.jar <intervalTime> <packageNum> <ip> <port>\n"
							+"       java  -jar udpSender.jar 1000 30 localhost 6666");
			System.exit(1);
		}
		// 间隔时间设置
		int intervalTime = Integer.parseInt(args[0]);
		// 发送次数
		int packageNum = Integer.parseInt(args[1]);
		// 发送IP
		String ip = args[2];
		// 发送端口
		int port = Integer.parseInt(args[3]);
		// Scanner sc = new Scanner(System.in);
		// System.out.println("发送次数");
		//Date date = new Date();
		long ts1 = System.currentTimeMillis() ;//date.getTime();
		
		DatagramSocket datagramSocket = new DatagramSocket();
		InetAddress address = InetAddress.getByName(ip);// "localhost"
		// String msg="";
		// String dataStream =
		// "4131 3033 7c41 3838 3033 3830 3030 3232 367c 4441 4131 3139 3941 3939 3331 7c2d 3832 7c31 3439 3733 3431 3934 3230 3030";
		// String dataStream =
		// "4131 3033 7c41 3838 3033 3830 3030 3232 367c 4441 4131 3139 3941 3939 3331 7c2d 3832";
		String dataStream = "4131 3033 7c41 3838 3033 3830 3030 3232 367c";
		// 间隔时间设置
		// int intervalTime = 1000;
		// 每次发包数量设置
		// int packageNum = 30;
		while (true) {
			for (int sendNum = packageNum; sendNum > 0; sendNum--) {
				String uMac = GenerateMac.randomMac4Qemu();
				String st = "|" + String.valueOf( System.currentTimeMillis() /*date.getTime()*/);
				// 发送数据1
				List<Byte> listByte = dataToBytes(dataStream);
				for (byte mac : uMac.getBytes()) {
					listByte.add(mac);
				}
				for (byte db : "|-82".getBytes()) {
					listByte.add(db);
				}
				for (byte sst : st.getBytes()) {
					listByte.add(sst);
				}
				Object[] obs = listByte.toArray();
				byte[] buffer = new byte[listByte.size()];
				for (int i = 0; i < obs.length; i++) {
					buffer[i] = (byte) obs[i];
				}
				// 6666 port
				DatagramPacket packet = new DatagramPacket(buffer,
						buffer.length, address, port);
				datagramSocket.send(packet);
			}
			long ts2 = System.currentTimeMillis() ; // date.getTime();
			long ts = (ts2 - ts1);
			System.out.println("发送时间：" + ts + "ms");
			Thread.sleep(intervalTime);
			// 接收数据
			// DatagramPacket inputPacket = new DatagramPacket(new byte[512],
			// 512);
			// datagramSocket.receive(inputPacket);
			// System.out.println(new String(inputPacket.getData(), 0 ,
			// inputPacket.getLength()));
			// datagramSocket.close();
		}
	}

	public static List<Byte> dataToBytes(String dataStream) {
		// String dataStream =
		// "4131 3033 7c41 3838 3033 3830 3030 3232 367c 4441 4131 3139 3941 3939 3331 7c2d 3832 7c31 3439 3733 3431 3934 3230 3030";
		// String dataStream = "4131 3033";
		String[] strs = dataStream.split(" ");
		List<Byte> bytelist = new ArrayList<>();
		for (String str : strs) {
			String str1 = str.substring(0, 2);
			String str2 = str.substring(2, 4);
			int num1 = Integer.valueOf(str1, 16);
			// System.out.println(num1);
			int num2 = Integer.valueOf(str2, 16);
			// System.out.println(num2);
			byte byt1 = (byte) num1;
			byte byt2 = (byte) num2;
			bytelist.add(byt1);
			bytelist.add(byt2);
		}

		return bytelist;

	}
}
