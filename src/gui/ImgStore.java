package gui;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.*;

public class ImgStore {

    private static ImgStore instance = null;

	private List<ImageIcon> t_rail = new ArrayList<>(); 
    private List<ImageIcon> rail = new ArrayList<>();
    private HashMap<String, ImageIcon> img = new HashMap<>();

    private ImgStore() {
        // rail 그림을 button 크기에 맞도록 수정  ( rail[0~1] = 직선레일, rail[2~5] = 곡선 레일 )
		// 경로가 지금 내 컴퓨터에 맞춰져 있어서 여기 경로만 수정해서 디버깅하면 될듯!
		// 버튼들은 class내에 선언안 rail이라는 이미지 배열에저장 

		// image path
		String PATH = "/Users/USER/Desktop/img/";
		
		ImageIcon originIcon = new ImageIcon(PATH + "t_rail0.png");  
		Image originImg = originIcon.getImage(); 
		Image changedImg= originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH );
		t_rail.add(new ImageIcon(changedImg));
		//TList[0] = new LeftRight(); 
		
		originIcon = new ImageIcon(PATH + "t_rail1.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH );
		t_rail.add(new ImageIcon(changedImg));
		//TList[1] = new UpDown();
		
		originIcon = new ImageIcon(PATH + "t_rail2.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH );
		t_rail.add(new ImageIcon(changedImg));
		//TList[2] = new UpRight();
		
		originIcon = new ImageIcon(PATH + "t_rail3.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH );
		t_rail.add(new ImageIcon(changedImg));
		//TList[3] = new UpLeft(); 
		
		originIcon = new ImageIcon(PATH + "t_rail4.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH );
		t_rail.add(new ImageIcon(changedImg));
		//TList[4] = new DownLeft(); 
		
		originIcon = new ImageIcon(PATH + "t_rail5.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH );
		t_rail.add(new ImageIcon(changedImg));
		//TList[5] = new DownRight();
		
		originIcon = new ImageIcon(PATH + "rail0.png"); //이 경로들 자신에 맞게 수정 
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH );
		rail.add(new ImageIcon(changedImg));
		
		originIcon = new ImageIcon(PATH + "rail1.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH );
		rail.add(new ImageIcon(changedImg));
		
		originIcon = new ImageIcon(PATH + "rail2.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH );
		rail.add(new ImageIcon(changedImg));
		
		originIcon = new ImageIcon(PATH + "rail3.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH );
		rail.add(new ImageIcon(changedImg));
		
		originIcon = new ImageIcon(PATH + "rail4.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH );
		rail.add(new ImageIcon(changedImg));
		
		originIcon = new ImageIcon(PATH + "rail5.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH );
		rail.add(new ImageIcon(changedImg));
		
		originIcon = new ImageIcon(PATH + "ground.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH );
		img.put("Ground", new ImageIcon(changedImg));
		
		originIcon = new ImageIcon(PATH + "rail_input.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH );
		img.put("RailInputImg", new ImageIcon(changedImg));
		
		originIcon = new ImageIcon(PATH + "impossible.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH );
		img.put("ImpossibleImg", new ImageIcon(changedImg));
		
		originIcon = new ImageIcon(PATH + "complete.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH );
		img.put("CompleteImg", new ImageIcon(changedImg));
		
		originIcon = new ImageIcon(PATH + "1P.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH );
		img.put("P1Img", new ImageIcon(changedImg));
		
		originIcon = new ImageIcon(PATH + "2P.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH );
		img.put("P2Img", new ImageIcon(changedImg));
		
		originIcon = new ImageIcon(PATH + "imp_rail_input.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH );
		img.put("ImpRailInputImg", new ImageIcon(changedImg));
		
		originIcon = new ImageIcon(PATH + "imp_complete.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH );
		img.put("ImpCompleteImg", new ImageIcon(changedImg));
		
		originIcon = new ImageIcon(PATH + "imp_impossible.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH );
		img.put("ImpImpossibleImg", new ImageIcon(changedImg));
		// 이미지 생성 끝. 앞으로 이 이미지로 모두 사용할 예정
    }

	public static ImgStore getInstance() {
		if (instance == null) {
			instance = new  ImgStore();
		}
		return instance;
	}

    public ImageIcon getTempRail(int key) {
        return t_rail.get(key);
    }

    public ImageIcon getRail(int key) {
        return rail.get(key);
    }

    public ImageIcon getImg(String key) {
        return img.get(key);
    }
}