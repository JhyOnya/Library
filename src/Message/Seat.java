package Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Seat
{
	// ���Һ���λ��0��ʼ����ʾ��1��ʼ��UI�����и������ɣ���������Ŀǰ��Ϊ5����λ����Ŀǰ��Ϊ5
	public static int roomNum = 5;// ��������
	public static int seatNum = 30;// ��λ����
	public static List<String> vipNames;// vip�б�
	public static Map<Integer, Map<Integer, String>> seats;// ��ǰ��λ��Ϣ<���ң�<��λ������>>
	public static Map<Integer, Map<String, Integer>> vipSeats;// <1,<3,lih>>,����1��λ3��lih��vip��λ

	// ѡ����λ������������+��λ��
	public static void selectSeat(int room, int seat, String name)
	{
		if (seats == null)
		{
			seats = new TreeMap<Integer, Map<Integer, String>>();
		}
		if (seats.get(room) == null)
		{
			seats.put(room, new TreeMap<Integer, String>());
		}
		seats.get(room).put(seat, name);
	}

	// �½�VIP������������+��λ��+����
	public static void addVIP(int room, int seat, String name)
	{
		if (vipSeats == null)
		{
			vipNames = new LinkedList<String>();
			vipSeats = new TreeMap<Integer, Map<String, Integer>>();
		}
		vipSeats.put(room, new TreeMap<String, Integer>()
		{
			{
				put(name, seat);
			}
		});
		vipNames.add(name);
	}

	// �Ƿ���VIP������������+��λ��+����
	public static int[] isVIP(String name)
	{
		int[] seat = new int[2];
		if (name.equals("vip"))
		{
			// ��vip����vip�Ļ����ؽ���+��λ
			seat[0] = 1;
			seat[1] = 1;
		}
		else
		{
			seat[0] = -1;
			seat[1] = -1;
		}
		return seat;
	}

	// ��ȡ��ǰ������λ�������list�е��ǲ���ʹ�õģ�vip+���ˣ�
	public static Map<Integer, String> getroomSeat(int room)
	{
		return seats.get(room);
	}

	// ����ɣ�����ĳͬѧ�����¿�ʱ��
	public static String getSeatTime(String name)
	{
		return ("10:20:34");
	}

	public static Map<String,Integer> getVIPSeats(int room)
	{
		return (vipSeats.get(room));
	}

	// ������
	public static void main(String[] args)
	{
		init();
	}

	// ������
	public static void init()
	{
		selectSeat(1, 2, "d");
		selectSeat(1, 3, "e");
		selectSeat(2, 3, "f");
		System.out.println(seats);

		addVIP(1, 2, "va");
		addVIP(13, 3, "vb");
		addVIP(2, 3, "vc");
		System.out.println(vipSeats);
	}
}
