package Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Seat {
	// ���Һ���λ��0��ʼ����ʾ��1��ʼ��UI�����и������ɣ���������Ŀǰ��Ϊ5����λ����Ŀǰ��Ϊ5
	public static int roomNum = 5;// ��������
	public static String[] rooms = { "101", "102", "201", "202", "301" };
	public static int seatNum = 30;// ��λ����
	public static List<String> vipNames;// vip�б�
	public static Map<Integer, Map<Integer, String>> seats;// ��ǰ��λ��Ϣ<���ң�<��λ������>>
	public static Map<Integer, Map<String, Integer>> vipSeats;// <1,<3,lih>>,����1��λ3��lih��vip��λ

	// ѡ����λ������������+��λ��
	public static void selectSeat(int room, int seat, String name) {
		if (seats.get(room) == null) {
			seats.put(room, new TreeMap<Integer, String>());
		}
		seats.get(room).put(seat, name);
	}

	// �½�VIP������������+��λ��+������
	public static void addVIP(int room, int seat, String name) {
		vipSeats.put(room, new TreeMap<String, Integer>() {
			{
				put(name, seat);
			}
		});
		vipNames.add(name);
	}

	// �Ƿ���VIP������������+��λ��+����
	public static int[] isVIP(String name) {
		int[] seat = new int[2];
		if (name.equals("vip")) {
			// ��vip����vip�Ļ����ؽ���+��λ
			seat[0] = 1;
			seat[1] = 1;
		} else {
			seat[0] = -1;
			seat[1] = -1;
		}
		return seat;
	}

	// ��ȡ��ǰ������λ�������list�е��ǲ���ʹ�õģ�vip+���ˣ�
	public static Map<Integer, String> getroomSeat(int room) {
		return seats.get(room);
	}

	// �����ƣ��������ʱ�����ĳͬѧ�¿�ʱ�䣬����Ӧ��ֻ���ѯ���Ͽ��е�ͬѧ�������û�ε�ͬѧ����ѯ����������λ���ʱ��������
	public static String getSeatTime(String name, String askTime) {
		return (askTime.substring(askTime.indexOf(" ")));
	}

	public static Map<String, Integer> getVIPSeats(int room) {
		return (vipSeats.get(room));
	}

	// ������
	public static void main(String[] args) {
		init("2019��04��25�� 10:31:28");
	}

	// �����ƣ�����ʱ���ʼ��������Ϣ
	public static void init(String time) {

		vipNames = new LinkedList<String>();
		vipSeats = new TreeMap<Integer, Map<String, Integer>>();
		seats = new TreeMap<Integer, Map<Integer, String>>();
		selectSeat(0, 15, "aaa");
		selectSeat(1, 2, "d");
		selectSeat(1, 3, "e");
		selectSeat(2, 3, "f");
		System.out.println(seats);

		addVIP(1, 6, "va");
		addVIP(0, 3, "vb");
		addVIP(2, 3, "vc");
		System.out.println(vipSeats);
	}
}
