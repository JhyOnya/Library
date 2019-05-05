package frameOperate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import timeOperate.getTime;

public class Seat {
	// ���Һ���λ��0��ʼ����ʾ��1��ʼ��UI�����и������ɣ���������Ŀǰ��Ϊ5����λ����Ŀǰ��Ϊ5
	public static int roomNum = 5;// ��������
	public static String[] rooms = { "101", "102", "201", "202", "301" };
	public static int seatNum = 30;// ��λ����
	public static Map<String, int[]> vipNames;// vip�б�
	public static Map<Integer, Map<Integer, String>> vipUsingSeats;// <1,<3,lih>>,����1��λ3��lih����ʹ�õ�vip��λ
	public static Map<Integer, Map<Integer, String>> vipEmptySeats;// <1,<3,lih>>,����1��λ3��lih��ʱ���õĵ�vip��λ
	public static Map<Integer, Map<Integer, String>> seats;// ��ǰ��λ��Ϣ<���ң�<��λ������>>�����޵�ǰʹ��
	public static Map<String, String> nameToClass = new TreeMap<String, String>();// ��ʾ�����Լ���Ӧ�İ༶

	// �����ƣ�����ʱ���ʼ��������Ϣ
	public static void init(String time) {
		// ��ʼ��
		vipNames = new TreeMap<String, int[]>();
		vipUsingSeats = new TreeMap<Integer, Map<Integer, String>>();
		vipEmptySeats = new TreeMap<Integer, Map<Integer, String>>();

		// ��ͨ�û���ѡ����init���ƺ��ɾ��
		selectSeat(0, 15, "aaa");
		selectSeat(1, 2, "d");
		selectSeat(1, 3, "e");
		selectSeat(2, 3, "f");

		// ���timeʱû�пε�VIP
		addUsingVIP(1, 6, "va");
		addUsingVIP(0, 3, "vb");
		addUsingVIP(2, 3, "vc");

		// ���timeʱ�пε�VIP
		addEmptyVIP(1, 5, "vd");
		addEmptyVIP(0, 4, "ve");
		addEmptyVIP(2, 4, "vf");

		System.out.println(seats);
		System.out.println(vipUsingSeats);
		System.out.println(vipEmptySeats);
	}

	// �����ƣ��������ʱ�����ĳͬѧ�¿�ʱ�䣬����Ӧ��ֻ���ѯ���Ͽ��е�ͬѧ�������û�ε�ͬѧ����ѯ����������λ���ʱ��������
	public static String getSeatTime(String name, String askTime) {
		return askTime;
//		return getTime.calRemainTime(name, askTime);

//		return (askTime.substring(askTime.indexOf(":") + 1));
	}

	// ѡ����λ������������+��λ��
	public static void selectSeat(int room, int seat, String name) {
		if (seats == null)
			seats = new TreeMap<Integer, Map<Integer, String>>();
		if (seats.get(room) == null)
			seats.put(room, new TreeMap<Integer, String>());
		seats.get(room).put(seat, name);
	}

	// �½�VIP������������+��λ��+������
	public static void addUsingVIP(int room, int seat, String name) {
		vipUsingSeats.put(room, new TreeMap<Integer, String>() {
			{
				put(seat, name);
			}
		});
		vipNames.put(name, new int[] { room, seat });
	}

	// �½�VIP������������+��λ��+������
	public static void addEmptyVIP(int room, int seat, String name) {
		vipEmptySeats.put(room, new TreeMap<Integer, String>() {
			{
				put(seat, name);
			}
		});
		vipNames.put(name, new int[] { room, seat });
	}

	// �Ƿ���VIP��Ŀǰû���õ�������������+��λ��+����
	public static int[] isVIP(String name) {
		int[] seat = new int[2];
		if (vipNames.containsKey(name)) {
			// ��vip����vip�Ļ����ؽ���+��λ
			seat = vipNames.get(name);
		} else {
			seat[0] = -1;
			seat[1] = -1;
		}
		return seat;
	}

	// ��ȡ��ǰ������λ�������list�е��ǲ���ʹ�õģ���ǰ���ˣ�vip���㣩
	public static Map<Integer, String> getroomSeats(int room) {
		return seats.get(room);
	}

	// ��ȡ��ǰ������λ�������ǰʱ���vipδʹ�õ���λ����vip�Ͽ�ʱ�䣩
	public static Map<Integer, String> getvipEmptySeats(int room) {
		return (vipEmptySeats.get(room));
	}

	// ��ȡ��ǰ������λ�������ǰʱ���vipʹ���е���λ��
	public static Map<Integer, String> getvipUsingSeats(int room) {
		return (vipUsingSeats.get(room));
	}

	// ������
	public static void main(String[] args) {
		init("2019��04��25�� 10:31:28");
	}

}
