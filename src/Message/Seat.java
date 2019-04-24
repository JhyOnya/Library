package Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Seat {
	// ���Һ���λ��0��ʼ����ʾ��1��ʼ��UI�����и������ɣ���������Ŀǰ��Ϊ5����λ����Ŀǰ��Ϊ5
	public static int roomNum = 5;// ��������
	public static int seatNum = 5;// ��λ����
	public static Map<Integer, Map<Integer, String>> vip;// <1,<3,lih>>,����1��λ3��lih��vip��λ
	public static Map<Integer, List<Integer>> seats;// <1,[2,3]>������1��,��λ2��3��ռ��

	// ѡ����λ������������+��λ��
	public static void selectSeat(int room, int seat) {
		if (seats == null) {
			seats = new TreeMap<Integer, List<Integer>>();
		}
		if (seats.get(room) == null) {
			seats.put(room, new ArrayList<>());
		}
		seats.get(room).add(seat);
	}

	// �½�VIP������������+��λ��+����
	public static void addVIP(int room, int seatNum, String name) {
		if (vip == null) {
			vip = new TreeMap<Integer, Map<Integer, String>>();
		}
		vip.put(room, new TreeMap<Integer, String>() {
			{
				put(seatNum, name);
			}
		});
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
	public static List<Integer> getroomSeat(int room) {
		return seats.get(room);
	}

	public static String getSeatTime(String name) {
		return ("10:20:34");
	}

	// ������
	public static void main(String[] args) {
		init();
	}
	// ������
	public static void init() {
		selectSeat(1, 2);
		selectSeat(1, 3);
		selectSeat(2, 3);
		System.out.println(seats);

		addVIP(1, 2, "a");
		addVIP(13, 3, "b");
		addVIP(2, 3, "c");
		System.out.println(vip);
	}
}
