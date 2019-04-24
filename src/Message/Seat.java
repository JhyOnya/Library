package Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Seat {
	// 教室和座位从0开始，显示从1开始，UI方进行更正即可；教室数量目前设为5；座位数量目前设为5
	public static int roomNum = 5;// 教室数量
	public static int seatNum = 5;// 座位数量
	public static Map<Integer, Map<Integer, String>> vip;// <1,<3,lih>>,教室1座位3是lih的vip座位
	public static Map<Integer, List<Integer>> seats;// <1,[2,3]>，教室1中,座位2和3被占用

	// 选择座位。参数：教室+座位号
	public static void selectSeat(int room, int seat) {
		if (seats == null) {
			seats = new TreeMap<Integer, List<Integer>>();
		}
		if (seats.get(room) == null) {
			seats.put(room, new ArrayList<>());
		}
		seats.get(room).add(seat);
	}

	// 新建VIP。参数：教室+座位号+姓名
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

	// 是否是VIP。参数：教室+座位号+姓名
	public static int[] isVIP(String name) {
		int[] seat = new int[2];
		if (name.equals("vip")) {
			// 是vip？是vip的话返回教室+座位
			seat[0] = 1;
			seat[1] = 1;
		} else {
			seat[0] = -1;
			seat[1] = -1;
		}
		return seat;
	}

	// 获取当前房间座位情况，在list中的是不可使用的（vip+有人）
	public static List<Integer> getroomSeat(int room) {
		return seats.get(room);
	}

	public static String getSeatTime(String name) {
		return ("10:20:34");
	}

	// 测试用
	public static void main(String[] args) {
		init();
	}
	// 测试用
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
