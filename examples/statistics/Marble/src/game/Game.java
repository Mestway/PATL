package game;

import jFrame.UI;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import chat.RoomList;

public class Game {

	public static Ground[] ground = new Ground[26];

	public static void init(){
		for(int i=0; i<26; i++)
			ground[i] = new Ground();

		ground[0].fee=0;
		ground[0].owner=-1;
		ground[0].price=0;

		ground[1].fee=20000;
		ground[1].owner=-1;
		ground[1].price=10000;

		ground[2].fee=25000;
		ground[2].owner=-1;
		ground[2].price=15000;

		ground[3].fee=50000;
		ground[3].owner=-1;
		ground[3].price=20000;

		ground[4].fee=30000;
		ground[4].owner=-1;
		ground[4].price=20000;

		ground[5].fee=0;
		ground[5].owner=-1;
		ground[5].price=0;

		ground[6].fee=40000;
		ground[6].owner=-1;
		ground[6].price=25000;

		ground[7].fee=0;
		ground[7].owner=-1;
		ground[7].price=0;

		ground[8].fee=60000;
		ground[8].owner=-1;
		ground[8].price=30000;

		ground[9].fee=80000;
		ground[9].owner=-1;
		ground[9].price=45000;

		ground[10].fee=70000;
		ground[10].owner=-1;
		ground[10].price=35000;

		ground[11].fee=0;
		ground[11].owner=-1;
		ground[11].price=0;

		ground[12].fee=80000;
		ground[12].owner=-1;
		ground[12].price=48000;

		ground[13].fee=0;
		ground[13].owner=-1;
		ground[13].price=0;

		ground[14].fee=100000;
		ground[14].owner=-1;
		ground[14].price=50000;

		ground[15].fee=150000;
		ground[15].owner=-1;
		ground[15].price=80000;

		ground[16].fee=130000;
		ground[16].owner=-1;
		ground[16].price=70000;

		ground[17].fee=0;
		ground[17].owner=-1;
		ground[17].price=0;

		ground[18].fee=160000;
		ground[18].owner=-1;
		ground[18].price=75000;

		ground[19].fee=190000;
		ground[19].owner=-1;
		ground[19].price=90000;

		ground[20].fee=0;
		ground[20].owner=-1;
		ground[20].price=0;

		ground[21].fee=230000;
		ground[21].owner=-1;
		ground[21].price=100000;

		ground[22].fee=190000;
		ground[22].owner=-1;
		ground[22].price=90000;

		ground[23].fee=260000;
		ground[23].owner=-1;
		ground[23].price=120000;

		ground[24].fee=0;
		ground[24].owner=-1;
		ground[24].price=0;

		ground[25].fee=350000;
		ground[25].owner=-1;
		ground[25].price=150000;
	}

	public static int dice(){
		int num =(int)(Math.random()*6+1);
		return num;
	}
	public static Point moving(int num, Point p){
		if(0<=num && num<6)
			p.x -= 80;
		else if(num == 6)
			p.x -= 100;
		else if(num == 7)
			p.y -= 100;
		else if(8<=num && num<=12)
			p.y -= 80;
		else if(num == 13)
			p.x += 100;
		else if(14<=num && num<=19)
			p.x += 80;
		else if(20<=num && num<25)
			p.y += 80;
		else if(num == 25)
			p.y += 100;
		else
			System.out.println("Game.moving() ERROR!");
		return p;
	}
	public static Point movingBack(int num, Point p){
		if(0<num && num<=6)
			p.x += 80;
		else if(num == 7)
			p.x += 100;
		else if(num == 8)
			p.y += 100;
		else if(8<num && num<=13)
			p.y += 80;
		else if(num == 14)
			p.x -= 100;
		else if(14<num && num<=20)
			p.x -= 80;
		else if(21<=num && num<=25)
			p.y -= 80;
		else if(num == 0)
			p.y -= 100;
		else
			System.out.println("Game.movingBack() ERROR!");
		return p;
	}
	public static int positionFormat(int num){
		if(26<=num){
			num -= 26;
		}
		else if(num<0)
			num += 26;
		return num;
	}
	public static void goldmission(User[] user, int index, int num, JLabel[] horse, JTextField[] moneyTextField, RoomList rl){
		switch(num){
		case 1: // �ڷ� 3ĭ
			rl.sendMsg("move/"+rl.roomtitle+"/"+rl.id+"/-3");
			break;

		case 2: // �������
			int tmp = Game.positionFormat(7-user[index].position);
			rl.sendMsg("move/"+rl.roomtitle+"/"+rl.id+"/"+tmp);

			break;

		case 5: // ������ 4ĭ
			rl.sendMsg("move/"+rl.roomtitle+"/"+rl.id+"/4");
			break;
		case 6: // ������ 6ĭ
			rl.sendMsg("move/"+rl.roomtitle+"/"+rl.id+"/6");
			break;
		case 7: // ������ 2ĭ
			rl.sendMsg("move/"+rl.roomtitle+"/"+rl.id+"/2");
			break;
		case 8: //������� ������ ����
		case 9: //����� ����
			break;
		case 10: //��ϱ� ����
			if(user[index].money < 1000000)
				JOptionPane.showMessageDialog(null, "���� �����ϴ�!");
			else{
				user[index].money -= 1000000;
				rl.sendMsg("money/"+rl.roomtitle+"/"+index+"/"+user[index].money);
			}
			break;

		case 3: // ���������� ����
		case 11: 
			tmp = 26-user[index].position;
			rl.sendMsg("move/"+rl.roomtitle+"/"+rl.id+"/"+tmp);
			break;

		case 4: // ���б� �ޱ�
		case 12: // ������Ʈ���
			user[index].money += 1000000;
			rl.sendMsg("money/"+rl.roomtitle+"/"+index+"/"+user[index].money);
			break;

		case 13: //�Ἦ ����
			if(user[index].money < 500000)
				JOptionPane.showMessageDialog(null, "���� �����ϴ�!");
			else{
				user[index].money -= 500000;
				rl.sendMsg("money/"+rl.roomtitle+"/"+index+"/"+user[index].money);
			}
			break;			

		case 14: //�������������
		case 19: //���� ����
			if(user[index].money < 300000)
				JOptionPane.showMessageDialog(null, "���� �����ϴ�!");
			else{
				user[index].money -= 300000;
				rl.sendMsg("money/"+rl.roomtitle+"/"+index+"/"+user[index].money);
			}
			break;	

		case 15: //������
		case 17: // ���б�
			user[index].money += 500000;
			rl.sendMsg("money/"+rl.roomtitle+"/"+index+"/"+user[index].money);
			break;

		case 16:  //��������� ����
			tmp = Game.positionFormat(20-user[index].position);
			rl.sendMsg("move/"+rl.roomtitle+"/"+rl.id+"/"+tmp);
			break;

		case 18: //���Ұ�
			tmp = Game.positionFormat(25-user[index].position);
			rl.sendMsg("move/"+rl.roomtitle+"/"+rl.id+"/"+tmp);
			break;
		}
	}
	public static void mission(User[] user, int index, JLabel[] horse, JTextField[] moneyTextField, RoomList rl){
		// ����.
		switch(user[index].position){
		case 0:
			break;
		case 7:
			break;
		case 13:
			break;
		case 20:
			break;
		case 5: // Ȳ�ݿ���
		case 11:
		case 17:
		case 24:
			int num =(int)(Math.random()*19+1);

			UI.lblGoldkey.setIcon(new ImageIcon(UI.class.getResource("/image/gold_"+num+".jpg")));//Y
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			UI.lblGoldkey.setIcon(new ImageIcon(UI.class.getResource("/image/gold.jpg")));//Y

			goldmission(user,index,num,horse,moneyTextField,rl);
			break;
		case 1:
		case 2:
		case 3:
		case 4:
		case 6:
		case 8:
		case 9:
		case 10:
		case 12:
		case 14:
		case 15:
		case 16:
		case 18:
		case 19:
		case 21:
		case 22:
		case 23:
		case 25:
			System.out.println("��ġ: "+user[index].position);
			System.out.println("����: "+ground[user[index].position].owner);
			////////////////////////////// ��ε�ĳ��Ʈ ������� ���� �����ߴٴ� ����� ��ο��� �˷�����.
			//�����ϱ�
			
			if(ground[user[index].position].owner != index){ // �ڽ��� ���� �ƴҰ��
				if(ground[user[index].position].owner == -1){ // ���� ����������
					int result = 0;
					result = JOptionPane.showConfirmDialog(null, "�� ���԰����� "+ground[user[index].position].price+"�Դϴ�. �����Ͻðڽ��ϱ�?");
					if(result == JOptionPane.YES_OPTION){
						if(ground[user[index].position].price > user[index].money){
							JOptionPane.showMessageDialog(null, "������ ���� �����ϴ�!");
						} else{
							user[index].money -= ground[user[index].position].price;
							ground[user[index].position].owner = index;
							rl.sendMsg("money/"+rl.roomtitle+"/"+index+"/"+user[index].money);
						}
					}
					else if(result == JOptionPane.NO_OPTION){
					}
					// (result ���ϰ�)
					// JOptionPane.YES_OPTION, JOptionPane.NO_OPTION,
					// JOptionPane.CANCEL_OPTION ��

				} 
				else{ // ���� ������ �����Ǿ� ���� ���
					if(ground[user[index].position].fee > user[index].money){
						JOptionPane.showMessageDialog(null, "������ ���� �����ϴ�! ����� �Ļ공���ϴ�^^");
					}
					else{
						JOptionPane.showMessageDialog(null, "���� ������ "+ ground[user[index].position].fee+" �Դϴ�");
						user[index].money -= ground[user[index].position].fee;
						user[ground[user[index].position].owner].money +=ground[user[index].position].fee;
						rl.sendMsg("money/"+rl.roomtitle+"/"+index+"/"+user[index].money);
						rl.sendMsg("money/"+rl.roomtitle+"/"+ground[user[index].position].owner+"/"+user[ground[user[index].position].owner].money);

					}
				}
			}
			break;
		}
	}


}
