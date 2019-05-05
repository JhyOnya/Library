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
	// 教室和座位从0开始，显示从1开始，UI方进行更正即可；教室数量目前设为5；座位数量目前设为5
	public static int roomNum = 5;// 教室数量
	public static String[] rooms = { "101", "102", "201", "202", "301" };
	public static int seatNum = 30;// 座位数量
	public static Map<String, int[]> vipNames;// vip列表
	public static Map<Integer, Map<Integer, String>> vipUsingSeats;// <1,<3,lih>>,教室1座位3是lih正在使用的vip座位
	public static Map<Integer, Map<Integer, String>> vipEmptySeats;// <1,<3,lih>>,教室1座位3是lih暂时不用的的vip座位
	public static Map<Integer, Map<Integer, String>> seats;// 当前座位信息<教室，<座位，姓名>>，仅限当前使用
	public static Map<String, String> nameToClass = new TreeMap<String, String>();// 表示姓名以及对应的班级

	// 待完善，根据时间初始化所有信息
	public static void init(String time) {
		// 初始化
		vipNames = new TreeMap<String, int[]>();
		vipUsingSeats = new TreeMap<Integer, Map<Integer, String>>();
		vipEmptySeats = new TreeMap<Integer, Map<Integer, String>>();

		// 普通用户的选座，init完善后可删除
		selectSeat(0, 15, "aaa");
		selectSeat(1, 2, "d");
		selectSeat(1, 3, "e");
		selectSeat(2, 3, "f");

		// 添加time时没有课的VIP
		addUsingVIP(1, 6, "va");
		addUsingVIP(0, 3, "vb");
		addUsingVIP(2, 3, "vc");

		// 添加time时有课的VIP
		addEmptyVIP(1, 5, "vd");
		addEmptyVIP(0, 4, "ve");
		addEmptyVIP(2, 4, "vf");

		System.out.println(seats);
		System.out.println(vipUsingSeats);
		System.out.println(vipEmptySeats);
	}

	// 待完善，计算给定时间距离某同学下课时间，这里应该只会查询到上课中的同学，如果有没课的同学被查询到，则是座位添加时出现问题
	public static String getSeatTime(String name, String askTime) {
		return askTime;
//		return getTime.calRemainTime(name, askTime);

//		return (askTime.substring(askTime.indexOf(":") + 1));
	}

	// 选择座位。参数：教室+座位号
	public static void selectSeat(int room, int seat, String name) {
		if (seats == null)
			seats = new TreeMap<Integer, Map<Integer, String>>();
		if (seats.get(room) == null)
			seats.put(room, new TreeMap<Integer, String>());
		seats.get(room).put(seat, name);
	}

	// 新建VIP。参数：教室+座位号+姓名。
	public static void addUsingVIP(int room, int seat, String name) {
		vipUsingSeats.put(room, new TreeMap<Integer, String>() {
			{
				put(seat, name);
			}
		});
		vipNames.put(name, new int[] { room, seat });
	}

	// 新建VIP。参数：教室+座位号+姓名。
	public static void addEmptyVIP(int room, int seat, String name) {
		vipEmptySeats.put(room, new TreeMap<Integer, String>() {
			{
				put(seat, name);
			}
		});
		vipNames.put(name, new int[] { room, seat });
	}

	// 是否是VIP。目前没有用到。参数：教室+座位号+姓名
	public static int[] isVIP(String name) {
		int[] seat = new int[2];
		if (vipNames.containsKey(name)) {
			// 是vip？是vip的话返回教室+座位
			seat = vipNames.get(name);
		} else {
			seat[0] = -1;
			seat[1] = -1;
		}
		return seat;
	}

	// 获取当前房间座位情况，在list中的是不可使用的（当前有人，vip不算）
	public static Map<Integer, String> getroomSeats(int room) {
		return seats.get(room);
	}

	// 获取当前房间座位情况（当前时间的vip未使用的座位，即vip上课时间）
	public static Map<Integer, String> getvipEmptySeats(int room) {
		return (vipEmptySeats.get(room));
	}

	// 获取当前房间座位情况（当前时间的vip使用中的座位）
	public static Map<Integer, String> getvipUsingSeats(int room) {
		return (vipUsingSeats.get(room));
	}

	// 测试用
	public static void main(String[] args) {
		init("2019年04月25日 10:31:28");
	}

}
