package Users;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Setting.Bean;
import Setting.DBmgr;

public class Basket extends JFrame implements ActionListener {
   
   private ImageIcon originIcon;
   private Image originImg, changedImg;
   
   private int hap = 0; //포인트 계산 변수

   private JLabel lb1; //~님의 장바구니

   private ImageIcon i1, i2, i3; //구매, 삭제, 닫기 버튼에 들어가는 아이콘
   private JButton bt1, bt2, bt3;

   private JLabel img = new JLabel(); //영화 포스터 틀

   private JLabel tf1, tf2, tf3, tf4, tf5; //선택한 영화 정보 담을 라벨

   private String col[] = {"No.","영화명","장르","관람연령","가격"}; //테이블 컬럼

   private JTable table = new JTable();
   private JScrollPane jsp = new JScrollPane();

   private DBmgr mgr = new DBmgr(); //원하는 값 가져올 함수가 담겨있는 클래스
   private ArrayList<Bean> list =new ArrayList<Bean>(); //sql문으로 가져온 결과값 리스트 저장
   private ArrayList<Bean> list2 =new ArrayList<Bean>();
   private ArrayList<Bean> list3 =new ArrayList<Bean>();
   private Bean bean, bean2, bean3, bean4; //sql문 처리, list로 가져온 값 각각 나눠서 저장


   public Basket() {
      
      // ImageIcon 객체를  생성한다.
      originIcon = new ImageIcon("resource/icon/pop.png");
      // ImageIcon에서 Image를 추출한다.
      originImg = originIcon.getImage();
      // 추출된 Image의 크기를 조절해서 새로운 Image객체를 생성한다.
      changedImg = originImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
      setIconImage(changedImg);

      lb1 = new JLabel(Login.name + "님의 장바구니",JLabel.CENTER);
      lb1.setFont(new Font("나눔바른고딕",Font.BOLD,20));
      lb1.setBackground(Color.WHITE);

      i1 = new ImageIcon("resource\\icon\\credit-card.png"); //구매아이콘
      i2 = new ImageIcon("resource\\icon\\bin.png"); //삭제아이콘
      i3 = new ImageIcon("resource\\icon\\exit.png"); //닫기아이콘
      bt1 = new JButton("구매",i1);
      bt2 = new JButton("삭제",i2);
      bt3 = new JButton("닫기",i3);
      bt1.setBackground(Color.WHITE);
      bt2.setBackground(Color.WHITE);
      bt3.setBackground(Color.WHITE);
      bt1.addActionListener(this);
      bt2.addActionListener(this);
      bt3.addActionListener(this);

      //늘어나는 부분 셋팅
      img.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //영화 포스터 외곽선

      JLabel lbl1 = new JLabel("영화명:");
      JLabel lbl2 = new JLabel("관람연령:");
      JLabel lbl3 = new JLabel("가       격:");
      JLabel lbl4 = new JLabel("개봉날짜:");
      JLabel lbl5 = new JLabel("상영시간:");

      tf1 = new JLabel(); //선택한 영화 정보 담을 라벨
      tf2 = new JLabel();
      tf3 = new JLabel();
      tf4 = new JLabel();
      tf5 = new JLabel();

      //위치 설정
      JPanel p1 = new JPanel(); //구매, 삭제, 닫기 버튼

      p1.add(bt1);
      p1.add(bt2);
      p1.add(bt3);
      p1.setBackground(Color.WHITE);

      JPanel p2 = new JPanel(new GridLayout(5,2,0,15)); //영화리스트 클릭하면 나오는 정보들
      p2.setBackground(Color.WHITE);

      p2.add(lbl1);   p2.add(tf1); 
      p2.add(lbl2);   p2.add(tf2);
      p2.add(lbl3);   p2.add(tf3);
      p2.add(lbl4);   p2.add(tf4);
      p2.add(lbl5);   p2.add(tf5);

      JPanel p3 = new JPanel(); //전체
      p3.setLayout(null);
      p3.setBackground(Color.WHITE);

      p3.add(lb1); //~님의 장바구니
      p3.add(jsp); //장바구니테이블 담은 스크롤패널 
      p3.add(p1); //구매, 삭제, 닫기 버튼
      p3.add(p2); //영화리스트 클릭하면 나오는 정보들
      p3.add(img); //영화 포스터 외곽선

      p1.setBounds(0,300,650,400);
      p2.setBounds(920,50,250,250);
      img.setBounds(700,50,190,250);
      lb1.setBounds(0,0,650,50);
      jsp.setBounds(0,50,650,250);

      add(p3);

      table();

      setTitle("장바구니");
      setSize(650,400);
      setResizable(false); //창 크기 고정
      setVisible(true);

      this.addWindowListener(new WindowAdapter() {@Override
         public void windowClosing(WindowEvent arg0) {
         dispose(); //X 누를 때의 이벤트
         new Users_Main();
      }
      });
   }

   void table() {

      //장바구니에 있는 영화의 번호로 테이블에 나타낼 값들을 영화DB테이블에서 가져온 후 리스트에 저장.  
      DBmgr.sql = "select mlist.m_name, mlist.m_genre, mlist.m_age, mlist.m_price from shoplist inner join mlist on mlist.m_no = shoplist.m_no where u_no = '"+Login.no+"' order by shoplist.s_no";
      list = mgr.basketjoin();

      Object record[][] = new Object[list.size()][col.length]; //테이블에 넣을 데이터 저장할 2차원 배열

      for(int i= 0 ; i < list.size() ; i++) {

         bean = list.get(i); //select로 가져온 리스트를 각각 저장

         record[i][0] = Integer.toString(i+1);
         record[i][1] = bean.getMname()+"";
         record[i][2] = bean.getMgenre()+"";
         record[i][3] = bean.getMage()+"";
         record[i][4] = Integer.toString(bean.getMprice());
      }

      DefaultTableModel model = new DefaultTableModel(record, col) { //record = 테이블의 데이터가 담긴 2차원 배열, col = 테이블 컬럼로 테이블을 만든다.
         public boolean isCellEditable(int r, int c) {
            return false; //테이블의 각 셀을 건들지 못하도록 함
         }
      };

      table = new JTable(model); //테이블 생성
      jsp.setViewportView(table); //스크롤페널에 테이블 담기
      jsp.getViewport().setBackground(Color.WHITE);

      DefaultTableCellRenderer al = new DefaultTableCellRenderer();
      al.setHorizontalAlignment(JLabel.CENTER); //각 셀의 데이터 가운데정렬

      for(int i =0 ; i < col.length ; i ++) {
         table.getColumnModel().getColumn(i).setCellRenderer(al);
      } //가운데정렬 렌더링(테이블에 맞게 설정)

      if(list.size()==0) { //장바구니에 아무것도 없으면 구매, 삭제 버튼 비활성화

         bt1.setEnabled(false);
         bt2.setEnabled(false); 
      }

      table.addMouseListener(new MouseAdapter() { //테이블에서 마우스 이벤트 발생했을 때 실행

         @Override
         public void mouseClicked(MouseEvent e) {

            //마우스가 선택한 행의 1번째 값(영화이름)의 내용들을 영화DB에서 가져와서 리스트로 저장 후 bean에서 각각 저장
            DBmgr.sql = "select * from mlist where m_name = '" + table.getValueAt(table.getSelectedRow(),1) + "'";
            list = mgr.movie();
            bean = list.get(0);

            if(e.getSource().equals(table)) {

               setSize(1200, 400); //오른쪽으로 창이 커지고 마우스로 클릭한 해당 영화의 정보가 나타남.

               //아이콘을 이미지로 가져와 크기조절, 품질 유지 설정
               img.setIcon(new ImageIcon(new ImageIcon("DBFiles\\image\\" + bean.getMname() + ".jpg").getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH))); 

               tf1.setText(bean.getMname()); //마우스로 클릭한 영화 정보 저장
               tf2.setText(bean.getMage());
               tf3.setText(bean.getMprice()+"");
               tf4.setText(bean.getMdate());
               tf5.setText(bean.getMtime());

            }
         }
      });
   }

   public static void main(String[] args) {
      new Basket();
   }

   void gg() {//구매

      int point = 0;
      point = (int) (hap * 0.05);

      //로그인한 유저의 이름으로 유저DB에서 검색하여 해당 유저의 정보를 리스트에 저장하고 bean에서 각각 저장
      DBmgr.sql = "select * from ulist where u_name = '" +Login.name+ "'";
      list3 = mgr.user();
      bean3 = list3.get(0);

      for(int i = 0 ; i < table.getRowCount() ; i++ ) {

         //구매한 영화가격 * 0.05 한 값 포인트 상승
         point = (int) (Integer.parseInt(table.getValueAt(i, 4)+"") * 0.05); 
         DBmgr.sql = "update ulist set u_point = u_point + '" + point +"' where u_id = '" + Login.id + "'";
         list = mgr.update();

         DBmgr.sql ="select * from mlist where m_name = '" + table.getValueAt(i, 1) +"'"; //행 마다의 영화 이름으로 영화DB에서 해당 영화 정보 가져옴
         list2 = mgr.movie();

         try {
            bean2 = list2.get(0);

            //구매내역테이블에 유저가 구매한 영화 정보 저장
            DBmgr.sql = "insert into olist(o_no, o_date, u_no, m_no, o_genre, o_price) values(so_no.nextval, to_char(sysdate,'yyyy-mm-dd'), '"+bean3.getUno()+ "', '"+bean2.getMno()+ "', '"+ bean2.getMgenre() + "', '"+bean2.getMprice()+"')";         
            mgr.update();
         }catch(Exception e1) {
         }
      }

      JOptionPane.showMessageDialog(null, "구매되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
      new Users_Main();

      //로그인한 유저의 구매내역 가져오기
      DBmgr.sql = "select * from olist where u_no = '" + Login.no + "'";
      list = mgr.orderlist();

      int sum = 0;
      for(int i= 0;i < list.size() ; i ++) { //해당 유저가 구매한 영화 가격 합산
         bean = list.get(i);
         sum = sum + bean.getOprice(); //누적금액
      }

      //해당 유저 정보 가져오기
      DBmgr.sql = "select * from ulist where u_id = '" + Login.id + "'";
      list = mgr.user();
      bean = list.get(0);

      String grade = bean.getUgrade(); //금액 따라 등급 저장
      if(sum >= 500000) {   // SVIP 300,000 <= sum
         grade = "SVIP";
      }else if(sum >= 200000) {   // VVIP(200,000 <= sum)
         grade = "VVIP";
      }else if(sum >= 100000) {   // VIP(100,000 <= sum)
         grade = "VIP";
      }

      if(bean.getUgrade().equals(grade)) {//grade가 원래 등급과 같을때 

      } else {//grade가 원래 등급과 달라졌을때 등급 변경
         JOptionPane.showMessageDialog(null, "축하합니다!\n회원님 등급이"+ grade +"로 승급하셨습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
      }

      DBmgr.sql = "update ulist set u_grade ='" + grade + "' where u_id = '" + Login.id + "'";
      list = mgr.update();

      //구매하면 장바구니 삭제
      DBmgr.sql = "delete from shoplist";
      list = mgr.update();

      dispose();
   }

   @Override
   public void actionPerformed(ActionEvent e) {

      if(e.getSource().equals(bt1)) {//구매버튼

         //해당 유저 정보 가져오기
         DBmgr.sql = "select * from ulist where u_id = '" + Login.id + "'";
         list = mgr.user();
         bean = list.get(0);

         for(int i = 0 ; i < table.getRowCount() ; i++ ) {

            hap = hap + Integer.parseInt(table.getValueAt(i, 4)+""); //장바구니의 영화 금액 합산
         }

         if(bean.getUpoint() >= hap) { //구매할 영화들의 금액보다 유저의 포인트가 더 많으면

            int ok = JOptionPane.showConfirmDialog(null, "회원님의 총 포인트 : " + bean.getUpoint() + "\n포인트로 결제하시겠습니까?\n(아니오를 클릭 시 현금결제가 됩니다.)","결제수단",JOptionPane.YES_NO_CANCEL_OPTION);
            if(ok==0) {

               for(int i = 0 ; i < table.getRowCount() ; i++ ) {

                  //사용한 포인트 만큼 유저의 포인트에서 차감
                  DBmgr.sql = "update ulist set u_point = u_point - '" + hap +"' where u_id = '" + Login.id + "'";
                  list = mgr.update();

                  //update 한 값 가져와서 다이얼로그 메시지 띄우기
                  DBmgr.sql = "select * from ulist where u_id = '" + Login.id + "'";
                  list = mgr.user();
                  bean = list.get(0);

                  JOptionPane.showMessageDialog(null, "포인트로 결제 완료되었습니다.\n남은 포인트 : " + bean.getUpoint(), "메시지", JOptionPane.INFORMATION_MESSAGE);


                  DBmgr.sql ="select * from mlist where m_name = '" + table.getValueAt(i, 1) +"'"; //행 마다의 영화 이름으로 영화DB에서 해당 영화 정보 가져옴
                  list2 = mgr.movie();

                  try {
                     bean2 = list2.get(i);

                     //구매내역테이블에 유저가 구매한 영화 정보 저장
                     DBmgr.sql = "insert into olist(o_no, o_date, u_no, m_no, o_genre, o_price) values(so_no.nextval, to_char(sysdate,'yyyy-mm-dd'), '"+bean.getUno()+ "', '"+bean2.getMno()+ "', '"+ bean2.getMgenre() + "', '"+bean2.getMprice()+"')";         
                     mgr.update();
                     //System.out.println(DBmgr.sql);
                  }catch(Exception e1) {
                  }

                  DBmgr.sql = "delete from shoplist";
                  list = mgr.update();

                  table();
               }
            }else if(ok==JOptionPane.NO_OPTION){ //아니오 클릭 시 일반 구매
               gg(); 
            }else if(ok == JOptionPane.CANCEL_OPTION) {
               JOptionPane.showMessageDialog(null, "구매 취소 되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
               dispose();
              //new Main();
            }
         }else { //구매할 영화들의 금액보다 유저의 포인트가 더 적으면
            gg();
         }

      }else if(e.getSource().equals(bt2)) {//삭제버튼

         if(table.getSelectedRowCount()==0) {//선택한 영화가 없을 때

            JOptionPane.showMessageDialog(null, "삭제할 메뉴를 선택해주세요.", "메시지", JOptionPane.ERROR_MESSAGE);

         }else {//선택한 영화가 있을 때

            String movie = (String) table.getValueAt(table.getSelectedRow(),1);

            //선택한 영화의 정보 가져오기
            DBmgr.sql = "select * from mlist where m_name = '"+movie+"'";
            list = mgr.movie();
            bean4 = list.get(0);

            //선택한 영화를 해당 유저의 장바구니에서 삭제
            DBmgr.sql = "delete from shoplist where m_no = '"+bean4.getMno()+"' and u_no = '"+Login.no+"'";
            list = mgr.update();

            table();

            setSize(650, 400); //영화 정보 사라짐
         }

      }else if(e.getSource().equals(bt3)) { //닫기버튼

         dispose();
         //new Main();
      }
   }
}