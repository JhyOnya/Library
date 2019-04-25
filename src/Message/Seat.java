package Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Seat {
	// 教室和座位从0开始，显示从1开始，UI方进行更正即可；教室数量目前设为5；座位数量目前设为5
	public static int roomNum = 5;// 教室数量
	public static String[] rooms = { "101", "102", "201", "202", "301" };
	public static int seatNum = 30;// 座位数量
	public static List<String> vipNames;// vip列表
	public static Map<Integer, Map<Integer, String>> seats;// 当前座位信息<教室，<座位，姓名>>
	public static Map<Integer, Map<String, Integer>> vipSeats;// <1,<3,lih>>,教室1座位3是lih的vip座位

	// 选择座位。参数：教室+座位号
	public static void selectSeat(int room, int seat, String name) {
		if (seats.get(room) == null) {
			seats.put(room, new TreeMap<Integer, String>());
		}
		seats.get(room).put(seat, name);
	}

	// 新建VIP。参数：教室+座位号+姓名。
	public static void addVIP(int room, int seat, String name) {
		vipSeats.put(room, new TreeMap<String, Integer>() {
			{
				put(name, seat);
			}
		});
		vipNames.add(name);
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
	public static Map<Integer, String> getroomSeat(int room) {
		return seats.get(room);
	}

	// 待完善，计算给定时间距离某同学下课时间，这里应该只会查询到上课中的同学，如果有没课的同学被查询到，则是座位添加时出现问题
	public static String getSeatTime(String name, String askTime) {
		return (askTime.substring(askTime.indexOf(" ")));
	}

	public static Map<String, Integer> getVIPSeats(int room) {
		return (vipSeats.get(room));
	}

	// 测试用
	public static void main(String[] args) {
		init("2019年04月25日 10:31:28");
	}

	// 待完善，根据时间初始化所有信息
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
